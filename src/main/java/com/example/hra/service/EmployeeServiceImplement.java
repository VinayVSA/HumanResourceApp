package com.example.hra.service;

import com.example.hra.dto.DepartmentEmployeeCountDTO;
import com.example.hra.dto.TotalCommissionDTO;
import com.example.hra.entity.Department;
import com.example.hra.entity.Employee;
import com.example.hra.entity.Job;
import com.example.hra.repository.DepartmentRepository;
import com.example.hra.repository.EmployeeRepository;
import com.example.hra.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImplement implements EmployeeService {

        private  EmployeeRepository employeeRepository;
        private JobRepository jobRepository;
        private DepartmentRepository departmentRepository;

        private EntityManager entityManager;

        @Autowired
        public void setEntityManager(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

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
        return null;
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
            return null;
        }

        @Override
        public Employee findByFirstName(String firstName) {
            return employeeRepository.findByFirstName(firstName);
        }

        @Override
        public Employee findByEmail(String email) {
            return employeeRepository.findByEmail(email);
        }
        @Override
        public Employee findByPhoneNumber(String phoneNumber) {
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
        return null;
    }

    public List<Employee> findAllEmployeesByDepartmentId(BigDecimal departmentId) {
        Department department = departmentRepository.findByDepartmentId(departmentId);
        List<Employee> employees = department.getEmployees();
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
        return employeeRepository.findByCommissionPctIsNull();
    }

        @Override
        public Employee updateEmployeeEmailByEmployeeId(String email,BigDecimal employeeId) {
            Employee employee = employeeRepository.findByEmployeeId(employeeId);
            if (employee != null) {
                employee.setEmail(email);
                return employeeRepository.save(employee);
            }
            return null;
        }
        @Override
        public Employee updateEmployeePhoneNumberByEmployeeId( String phoneNumber,BigDecimal employeeId) {
            Employee employee = employeeRepository.findByEmployeeId(employeeId);
            if (employee != null) {
                employee.setPhoneNumber(phoneNumber);
                return employeeRepository.save(employee);
            }
            return null;
        }
        @Override
        public void deleteEmployee(BigDecimal employeeId) {
            employeeRepository.deleteById(employeeId);
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

        return openPositions;
    }
    @Override
    public List<Employee> findAllEmployeesGroupByDepartment(BigDecimal departmentId) {
        return employeeRepository.findAllEmployeesGroupByDepartment(departmentId);
    }

    @Override
    public List<Job> findAllOpenPositions(BigDecimal departmentId) {
        int count = 0;
        List<Job> allJobs = jobRepository.findAll();
        List<Job> openPositions = new ArrayList<>();
        Department department = departmentRepository.findByDepartmentId(departmentId);
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
        BigDecimal totalCommission = employeeRepository.calculateTotalCommissionByDepartment(departmentId);
        TotalCommissionDTO commissionDTO = new TotalCommissionDTO(departmentId, totalCommission);
        return commissionDTO;
    }

    public String assignDepartmentAndUpdateSalesPercentage(BigDecimal departmentId, BigDecimal salesPercentage) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);

        if (optionalDepartment.isEmpty()) {
            return "Department not found.";
        }

        Department department = optionalDepartment.get();
        if (!department.getDepartmentName().equalsIgnoreCase("Sales")) {
            return "Only employees from the Sales department can have their sales percentage updated.";
        }
        List<Employee> salesEmployees = department.getEmployees();
        if (salesEmployees.isEmpty()) {
            return "No employees found in the Sales department.";
        }
        if (salesPercentage.compareTo(BigDecimal.ZERO) < 0 || salesPercentage.compareTo(BigDecimal.valueOf(100)) > 0) {
            return "Sales percentage must be between 0 and 100.";
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
            return "Employee or Manager not found.";
        }
        if (employee.getFirstName().equals(manager.getFirstName())){
            return "Cannot assign employee as their own manager.";
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
            return "Employee or Department not found.";
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