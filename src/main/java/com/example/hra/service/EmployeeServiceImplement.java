package com.example.hra.service;

import com.example.hra.Entity.Department;
import com.example.hra.Entity.Employee;
import com.example.hra.Entity.Job;
import com.example.hra.Repository.DepartmentRepository;
import com.example.hra.Repository.EmployeeRepository;
import com.example.hra.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        public Employee assignManagerToEmployee(BigDecimal employeeId, BigDecimal managerId) {
            Employee employee = employeeRepository.findById(employeeId).orElse(null);
            Employee manager = employeeRepository.findById(managerId).orElse(null);
            if (employee != null && manager != null) {
                employee.setManager(manager);
                return employeeRepository.save(employee);
            }
            return null;
        }

        @Override
        public Employee assignDepartmentToEmployee(BigDecimal employeeId, BigDecimal departmentId) {
            Employee employee = employeeRepository.findById(employeeId).orElse(null);
            Department department = departmentRepository.findById(departmentId).orElse(null);
            if (employee != null && department != null) {
                employee.setDepartment(department);
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

        @Override
        public BigDecimal findTotalCommissionIssuedToEmployeeByDepartment(BigDecimal departmentId) {
            return employeeRepository.findTotalCommissionIssuedToEmployeeByDepartment(departmentId);
        }

       /* @Override
        public List<Employee> findAllEmployeesByDepartment(BigDecimal departmentId) {
            return employeeRepository.findByDepartmentId(departmentId);
        }
         @Override
        public List<Employee> findAllEmployeesWithNoCommission() {
            return employeeRepository.findByCommissionPctIsNull();
        }

        @Override
        public List<Employee> findAllEmployeesGroupByDepartment() {
            return employeeRepository.findAllEmployeesGroupByDepartment();
        }

        @Override
        public List<Employee> listAllManagerDetails() {
            return employeeRepository.findByManagerIsNotNull();
        }

        @Override
        public List<Employee> countAllEmployeesGroupByLocation() {
            return employeeRepository.countAllEmployeesGroupByLocation();
        }
        @Override
        public List<Job> findAllOpenPositionsByDepartment(BigDecimal departmentId) {
            return jobRepository.findByDepartmentIdAndEmployeeIsNull(departmentId);
        }

        @Override
        public List<Employee> findAllEmployeesByHireDate(LocalDate fromHireDate, LocalDate toHireDate) {
            return employeeRepository.findByHireDateBetween(fromHireDate, toHireDate);
        }
        @Override
        public List<Job> findAllOpenPositions() {
            return jobRepository.findByEmployeeIsNull();
        }
        */



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
    public List<Employee> getAllEmployees() {

       return employeeRepository.findAll();
    }


}