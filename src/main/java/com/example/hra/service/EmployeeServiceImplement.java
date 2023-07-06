package com.example.hra.service;

import com.example.hra.dto.DepartmentEmployeeCountDTO;
import com.example.hra.dto.TotalCommissionDTO;
import com.example.hra.entity.Department;
import com.example.hra.entity.Employee;
import com.example.hra.entity.Job;
import com.example.hra.exception.EmployeeNotFoundException;
import com.example.hra.repository.DepartmentRepository;
import com.example.hra.repository.EmployeeRepository;
import com.example.hra.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImplement implements EmployeeService {

        private  EmployeeRepository employeeRepository;
        private JobRepository jobRepository;
        private DepartmentRepository departmentRepository;
        @Autowired
        public void setJobRepository(JobRepository jobRepository) {
            this.jobRepository = jobRepository;
        }
        @Autowired
        public void setEmployeeRepository(EmployeeRepository employeeRepository) {
            this.employeeRepository = employeeRepository;
        }
        @Autowired
        public void setDepartmentRepository(DepartmentRepository departmentRepository) {
            this.departmentRepository = departmentRepository;
        }


    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    @Override
    public Employee updateEmployee(Employee employee) {
        Employee employee1 = employeeRepository.findByEmployeeId(employee.getEmployeeId());
        if(employee1==null)
        {
            throw new EmployeeNotFoundException("Employee Not Found");
        }
        return employeeRepository.save(employee);
    }
    @Override
    public Employee assignJobToEmployee(BigDecimal employeeId, String jobId) {
        Employee employee = employeeRepository.getEmployeeByEmployeeId(employeeId);
        Job job = jobRepository.findByJobId(jobId);
        if (employee != null && job != null) {
            employee.setJob(job);
            return employeeRepository.save(employee);
        }
        else {
            throw new EmployeeNotFoundException("Employee or Job Not Found");
        }
    }
        @Override
        public Employee assignDepartmentAndSalesPercentage(BigDecimal employeeId, BigDecimal departmentId, BigDecimal salesPercentage) {
            Employee employee = employeeRepository.findById(employeeId).orElse(null);
            Department department = departmentRepository.findById(departmentId).orElse(null);
            if (employee != null && department != null) {
                employee.setDepartment(department);
                employee.setCommissionPct(salesPercentage);
                return employeeRepository.save(employee);
            }
            else
            {
                throw new EmployeeNotFoundException("Employee or Department Not Found");
            }
        }
        @Override
        public Employee findByFirstName(String firstName) {
            Employee employee =  employeeRepository.findByFirstName(firstName);
            if(employee==null)
            {
                throw new EmployeeNotFoundException("Employee Not Found");
            }
            return employee;
        }
        @Override
        public Employee findByEmail(String email) {
            Employee employee = employeeRepository.findByPhoneNumber(email);
            if(employee==null)
            {
                throw new EmployeeNotFoundException("Employee Not Found");
            }
            return employeeRepository.findByEmail(email);
        }
        @Override
        public Employee findByPhoneNumber(String phoneNumber) {
            Employee employee = employeeRepository.findByPhoneNumber(phoneNumber);
            if(employee==null)
            {
                throw new EmployeeNotFoundException("Employee Not Found");
            }
            return employeeRepository.findByPhoneNumber(phoneNumber);
        }
    @Override
    public Map<String,BigDecimal> findMaxSalaryOfJobByEmployeeId(BigDecimal employeeId) {
            Employee employee = employeeRepository.findByEmployeeId(employeeId);
            Map<String,BigDecimal> mp = new HashMap<>();
            if(employee!=null)
            {
                Job job = employee.getJob();
                BigDecimal maxSalary = job.getMaxSalary();
                String title = job.getJobTitle();
                mp.put(title,maxSalary);
                return mp;
            }
            else
            {
             throw new EmployeeNotFoundException("Employee Not Found");
            }
    }
    public List<Employee> findAllEmployeesByDepartmentId(BigDecimal departmentId) {
        Department department = departmentRepository.findByDepartmentId(departmentId);
        List<Employee> employees = department.getEmployees();
        if(employees.isEmpty())
        {
            throw new EmployeeNotFoundException("Employees Not Found in Department");
        }
        return employees;
    }
    public List<DepartmentEmployeeCountDTO> countAllEmployeesGroupByDepartment() {
        List<Object[]> results = employeeRepository.countEmployeesGroupByDepartment();
        return results.stream()
                .map(result -> new DepartmentEmployeeCountDTO((String) result[0], (Long) result[1]))
                .collect(Collectors.toList());
    }
    @Override
    public List<Employee> findAllEmployeesWithNoCommission() {
        List<Employee> employees = employeeRepository.findByCommissionPctIsNull();
        if(employees.isEmpty())
        {
            throw new EmployeeNotFoundException("Employees Not Found");
        }
        return employees;
    }
        @Override
        public Employee updateEmployeeEmailByEmployeeId(String email,BigDecimal employeeId) {
            Employee employee = employeeRepository.findByEmployeeId(employeeId);
            if (employee != null) {
                employee.setEmail(email);
                return employeeRepository.save(employee);
            }
            else
            {
                throw new EmployeeNotFoundException("Employee Not Found");
            }
        }
        @Override
        public Employee updateEmployeePhoneNumberByEmployeeId( String phoneNumber,BigDecimal employeeId) {
            Employee employee = employeeRepository.findByEmployeeId(employeeId);
            if (employee != null) {
                employee.setPhoneNumber(phoneNumber);
                return employeeRepository.save(employee);
            }
            else
            {
                throw new EmployeeNotFoundException("Employee Not Found");
            }
        }
        @Override
        public void deleteEmployee(BigDecimal employeeId) {
            Employee employee = employeeRepository.findByEmployeeId(employeeId);
            if(employee==null)
            {
                    throw new EmployeeNotFoundException("Employee Not Found");
            }
            try
            {
                employeeRepository.deleteById(employeeId);
            }
            catch (RuntimeException re)
            {
                throw new EmployeeNotFoundException("Employee Can't be Deleted");
            }

        }

    @Override
    public List<Employee> listAllManagerDetails() {
        List<Employee> employees = employeeRepository.findAll();
        List<Employee> managers = new ArrayList<>();
        for (Employee employee:employees) {
            if(employee.getManager()!=null)
            {
                managers.add(employee.getManager());
            }
        }
        List<Employee> employees1 = managers.stream()
                .distinct()
                .collect(Collectors.toList());
        return employees1;
    }

    @Override
    public List<Job> findAllOpenPositions() {
        int count = 0;
        List<Job> allJobs = jobRepository.findAll();
        List<Job> openPositions = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();
        for (Job job : allJobs) {
            for (Employee e : employees) {
                if (job.getJobId() == e.getJob().getJobId()) {
                    count++;
                }
            }
            if (count == 0)
                openPositions.add(job);
            count = 0;
        }
        if(openPositions.isEmpty())
        {
            throw new EmployeeNotFoundException("No OpenPositions");
        }
        return openPositions;
    }
    @Override
    public List<Employee> findAllEmployeesGroupByDepartment(BigDecimal departmentId) {
        List<Employee> employees = employeeRepository.findAllEmployeesGroupByDepartment(departmentId);
       if(employees.isEmpty())
       {
           throw new EmployeeNotFoundException("Employees Not Found");
       }
       return employees;
    }

    @Override
    public List<Job> findAllOpenPositions(BigDecimal departmentId) {
        int count = 0;
        List<Job> allJobs = jobRepository.findAll();
        List<Job> openPositions = new ArrayList<>();
        Department department = departmentRepository.findByDepartmentId(departmentId);
        if(department==null)
        {
            throw new EmployeeNotFoundException("Department Not Found");
        }
        List<Employee> employees = department.getEmployees();
        for (Job job : allJobs) {
            for (Employee e : employees) {
                if (job.getJobId() == e.getJob().getJobId()) {
                    count++;
                }
            }
            if (count == 0)
                openPositions.add(job);
            count = 0;
        }
        if(openPositions.isEmpty())
        {
            throw new EmployeeNotFoundException("No OpenPositions");
        }
        return openPositions;
    }
    @Override
    public List<Object[]> countAllEmployeesGroupByLocation() {
        List<Object[]> results = employeeRepository.countEmployeesGroupByLocation();
        return results;
    }
    public List<Employee> listAllEmployeeByHireDateRange(Date fromHireDate, Date toHireDate) {
        return employeeRepository.findAllByHireDateBetween(fromHireDate, toHireDate);
    }
    public TotalCommissionDTO findTotalCommissionIssuedToEmployeeByDepartment(BigDecimal departmentId) {
            Department department = departmentRepository.findByDepartmentId(departmentId);
            if(department==null)
            {
                throw new EmployeeNotFoundException("Department Not Found");
            }
        BigDecimal totalCommission = employeeRepository.calculateTotalCommissionByDepartment(departmentId);
        TotalCommissionDTO commissionDTO = new TotalCommissionDTO(departmentId, totalCommission);
        return commissionDTO;
    }

    public String assignDepartmentAndUpdateSalesPercentage(BigDecimal departmentId, BigDecimal salesPercentage) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isEmpty()) {
            throw new EmployeeNotFoundException("Department not found.");
        }
        Department department = optionalDepartment.get();
        if (!department.getDepartmentName().equalsIgnoreCase("Sales")) {
            throw new EmployeeNotFoundException("Only employees from the Sales department can have their sales percentage updated.");
        }
        List<Employee> salesEmployees = department.getEmployees();
        if (salesEmployees.isEmpty()) {
            throw new EmployeeNotFoundException("No employees found in the Sales department.");
        }
        if (salesPercentage.compareTo(BigDecimal.ZERO) < 0 || salesPercentage.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new EmployeeNotFoundException("Sales percentage must be between 0 and 100.");
        }
        for (Employee employee : salesEmployees) {
            employee.setDepartment(department);
            employee.setCommissionPct(salesPercentage);
        }
        employeeRepository.saveAll(salesEmployees);
        return "Record Modified Successfully";
    }
    @Override
    public String assignManager(BigDecimal employeeId, BigDecimal managerId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        Employee manager = employeeRepository.findByEmployeeId(managerId);
        System.out.println(employee.getFirstName());
        System.out.println(manager.getFirstName());
        if (employee==null || manager==null) {
            throw new EmployeeNotFoundException("Employee or Manager not found.");
        }
        if (employee.getFirstName().equals(manager.getFirstName())){
            throw new EmployeeNotFoundException("Cannot assign employee as their own manager.");
        }
        employee.setManager(manager);
        employeeRepository.save(employee);
        return "Record Modified Successfully";
    }
    @Override
    public String assignDepartment(BigDecimal employeeId, BigDecimal departmentId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        Department department = departmentRepository.findByDepartmentId(departmentId);
        if (employee==null || department==null) {
            throw new EmployeeNotFoundException("Employee or Department not found.");
        }
        employee.setDepartment(department);
        employeeRepository.save(employee);

        return "Record Modified Successfully";
    }

    @Override
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

}