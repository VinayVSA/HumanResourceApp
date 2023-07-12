package com.example.hra.services;
import com.example.hra.dtos.DepartmentEmployeeCountDTO;
import com.example.hra.dtos.TotalCommissionDTO;
import com.example.hra.entities.Department;
import com.example.hra.entities.Employee;
import com.example.hra.entities.Job;
import com.example.hra.exceptions.DepartmentNotFoundException;
import com.example.hra.exceptions.EmployeeNotFoundException;
import com.example.hra.exceptions.JobNotFoundException;
import com.example.hra.repositories.DepartmentRepository;
import com.example.hra.repositories.EmployeeRepository;
import com.example.hra.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class EmployeeServiceImplement implements EmployeeService {
        private String recordModifiedSuccessfully = "Record Modified Successfully";
        private String departmentNotFound = "Department Not Found";
        private String employeeNotFound="Employee Not Found";

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
        return employeeRepository.save(employee);}
    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);}
    @Override
    public Employee assignJobToEmployee(String jobId,BigDecimal employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(()->new EmployeeNotFoundException(employeeNotFound));
        Job job = jobRepository.findByJobId(jobId).orElseThrow(()->new JobNotFoundException("Job Not Found"));
        employee.setJob(job);
        return employeeRepository.save(employee);}
        @Override
        public Employee assignDepartmentAndSalesPercentage(BigDecimal employeeId, BigDecimal departmentId, BigDecimal salesPercentage) {
            Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new EmployeeNotFoundException(employeeNotFound));
            Department department = departmentRepository.findById(departmentId).orElseThrow(()->new DepartmentNotFoundException(departmentNotFound));
            employee.setDepartment(department);
            employee.setCommissionPct(salesPercentage);
            return employeeRepository.save(employee);}
        @Override
        public List<Employee> findByFirstName(String firstName) {
            List<Employee> employees = new ArrayList<>();
            for(Employee employee:employees)
            {
                if(employee.getFirstName().equalsIgnoreCase(firstName))
                    employees.add(employee);
            }
           return employeeRepository.findByFirstName(firstName).orElseThrow(()->new EmployeeNotFoundException("Employees Not Found"));}
        @Override
        public Employee findByEmail(String email) {
            return employeeRepository.findByEmail(email).orElseThrow(()->new EmployeeNotFoundException(employeeNotFound));}
        @Override
        public Employee findByPhoneNumber(String phoneNumber) {
            return employeeRepository.findByPhoneNumber(phoneNumber).orElseThrow(()->new EmployeeNotFoundException(employeeNotFound));}
    @Override
    public Map<String,BigDecimal> findMaxSalaryOfJobByEmployeeId(BigDecimal employeeId) {
            Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(()->new EmployeeNotFoundException(employeeNotFound));
            Map<String,BigDecimal> mp = new HashMap<>();
            Job job = employee.getJob();
            BigDecimal maxSalary = job.getMaxSalary();
            String title = job.getJobTitle();
            mp.put(title,maxSalary);
            return mp;}
    public List<Employee> findAllEmployeesByDepartmentId(BigDecimal departmentId) {
        Department department = departmentRepository.findByDepartmentId(departmentId).orElseThrow(()->new DepartmentNotFoundException(departmentNotFound));
        List<Employee> employees = department.getEmployees();
        if(employees.isEmpty())
            throw new EmployeeNotFoundException("Employees Not Found in Department");
        return employees;}
    public List<DepartmentEmployeeCountDTO> countAllEmployeesGroupByDepartment() {
        List<Object[]> results = employeeRepository.countEmployeesGroupByDepartment();
        return results.stream()
                .map(result -> new DepartmentEmployeeCountDTO((String) result[0], (Long) result[1]))
                .collect(Collectors.toList());}
    @Override
    public List<Employee> findAllEmployeesWithNoCommission() {
        return employeeRepository.findByCommissionPctIsNull().orElseThrow(()->new EmployeeNotFoundException(employeeNotFound));}
        @Override
        public String updateEmployeeEmailByEmployeeId(String email,BigDecimal employeeId) {
            Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(()->new EmployeeNotFoundException(employeeNotFound));
            employee.setEmail(email);
            employeeRepository.save(employee);
            return recordModifiedSuccessfully;}
        @Override
        public String updateEmployeePhoneNumberByEmployeeId( String phoneNumber,BigDecimal employeeId) {
            Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(()->new EmployeeNotFoundException(employeeNotFound));
            employee.setPhoneNumber(phoneNumber);
            employeeRepository.save(employee);
            return recordModifiedSuccessfully;}
        @Override
        public String deleteEmployee(BigDecimal employeeId) {
            if(employeeRepository.findByEmployeeId(employeeId).isPresent()) {
                try {
                    employeeRepository.deleteById(employeeId);
                } catch (RuntimeException re) {
                    throw new EmployeeNotFoundException("Employee Can't be Deleted");
                }
                return "Record Deleted Successfully";
            }else{
                throw new EmployeeNotFoundException(employeeNotFound);
            }
        }
    @Override
    public List<Employee> listAllManagerDetails() {
        List<Employee> employees = employeeRepository.findAll();
        List<Employee> managers = new ArrayList<>();
        for (Employee employee:employees) {
            if(employee.getManager()!=null)
                managers.add(employee.getManager());}
        
        return managers.stream()
                .distinct()
                .collect(Collectors.toList());}
    @Override
    public List<Job> findAllOpenPositions() {
        int count = 0;
        List<Job> allJobs = jobRepository.findAll();
        List<Job> openPositions = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();
        for (Job job : allJobs) {
            for (Employee e : employees) {
                if (job.getJobId().equals(e.getJob().getJobId()))
                    count++;}
            if (count == 0)
                openPositions.add(job);
            count = 0;}
        if(openPositions.isEmpty())
            throw new EmployeeNotFoundException("No OpenPositions");
        return openPositions;}
    @Override
    public List<Employee> findAllEmployeesGroupByDepartment(BigDecimal departmentId) {
        List<Employee> employees = employeeRepository.findAllEmployeesGroupByDepartment(departmentId);
       if(employees.isEmpty())
           throw new EmployeeNotFoundException("Employees Not Found");
       return employees;}
    @Override
    public List<Job> findAllOpenPositions(BigDecimal departmentId) {
        int count = 0;
        List<Job> allJobs = jobRepository.findAll();
        List<Job> openPositions = new ArrayList<>();
        Department department = departmentRepository.findByDepartmentId(departmentId).orElseThrow(()->new DepartmentNotFoundException(departmentNotFound));
        List<Employee> employees = department.getEmployees();
        for (Job job : allJobs) {
            for (Employee e : employees) {
                if (job.getJobId().equals(e.getJob().getJobId()))
                    count++;}
            if (count == 0)
                openPositions.add(job);
            count = 0;}
        if(openPositions.isEmpty())
            throw new EmployeeNotFoundException("No OpenPositions");
        return openPositions;}
    @Override
    public List<Object[]> countAllEmployeesGroupByLocation() {
        return employeeRepository.countEmployeesGroupByLocation();}
    public List<Employee> listAllEmployeeByHireDateRange(Date fromHireDate, Date toHireDate) {
            List<Employee> employees = employeeRepository.findAllByHireDateBetween(fromHireDate, toHireDate);
            if(employees.isEmpty())
                throw new EmployeeNotFoundException("Employees Not Found");
            return employees;}
    public TotalCommissionDTO findTotalCommissionIssuedToEmployeeByDepartment(BigDecimal departmentId) {
        if(departmentRepository.findByDepartmentId(departmentId).isPresent()) {
            BigDecimal totalCommission = employeeRepository.calculateTotalCommissionByDepartment(departmentId);
            
            return new TotalCommissionDTO(departmentId, totalCommission);
        }else{
            throw new DepartmentNotFoundException(departmentNotFound);
        }
    }
    public String assignDepartmentAndUpdateSalesPercentage(BigDecimal departmentId, BigDecimal salesPercentage) {
        Department department=departmentRepository.findByDepartmentId(departmentId).orElseThrow(()->new DepartmentNotFoundException(departmentNotFound));
        if (!department.getDepartmentName().equalsIgnoreCase("Sales"))
            throw new DepartmentNotFoundException("Only employees from the Sales department can have their sales percentage updated.");
        List<Employee> salesEmployees = department.getEmployees();
        if (salesEmployees.isEmpty())
            throw new EmployeeNotFoundException("No employees found in the Sales department.");
        if (salesPercentage.compareTo(BigDecimal.ZERO) < 0 || salesPercentage.compareTo(BigDecimal.valueOf(100)) > 0)
            throw new EmployeeNotFoundException("Sales percentage must be between 0 and 100.");
        for (Employee employee : salesEmployees) {
            employee.setDepartment(department);
            employee.setCommissionPct(salesPercentage);}
        employeeRepository.saveAll(salesEmployees);
        return recordModifiedSuccessfully;}
    @Override
    public String assignManager(BigDecimal employeeId, BigDecimal managerId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(()->new EmployeeNotFoundException(employeeNotFound));
        Employee manager = employeeRepository.findByEmployeeId(managerId).orElseThrow(()->new EmployeeNotFoundException("Manager Not Found"));
        if (employee.getEmployeeId().equals(manager.getEmployeeId()))
            throw new EmployeeNotFoundException("Cannot assign employee as their own manager.");
        employee.setManager(manager);
        employeeRepository.save(employee);
        return recordModifiedSuccessfully;}
    @Override
    public String assignDepartment(BigDecimal employeeId, BigDecimal departmentId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(()->new EmployeeNotFoundException(employeeNotFound));
        Department department = departmentRepository.findByDepartmentId(departmentId).orElseThrow(()->new DepartmentNotFoundException(departmentNotFound));
        employee.setDepartment(department);
        employeeRepository.save(employee);
        return recordModifiedSuccessfully;}
    @Override
    public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();}}