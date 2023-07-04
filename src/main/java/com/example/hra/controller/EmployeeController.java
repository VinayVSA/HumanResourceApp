package com.example.hra.controller;

import com.example.hra.Entity.Employee;
import com.example.hra.Entity.Job;
import com.example.hra.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @PutMapping("/{job_id}")
    public Employee assignJobToEmployee(@PathVariable("job_id") String jobId, @RequestParam("employee_id") BigDecimal employeeId) {
        return employeeService.assignJobToEmployee(employeeId, jobId);
    }


        @PutMapping("/{manager_id}")
        public Employee assignManagerToEmployee(@PathVariable("manager_id") BigDecimal managerId, @RequestParam("employee_id") BigDecimal employeeId) {
            return employeeService.assignManagerToEmployee(employeeId, managerId);
        }

        @PutMapping("/{department_id}")
        public Employee assignDepartmentToEmployee(@PathVariable("department_id") BigDecimal departmentId, @RequestParam("employee_id") BigDecimal employeeId) {
            return employeeService.assignDepartmentToEmployee(employeeId, departmentId);
        }

        @PutMapping("/{department_id}/{sales_percentage}")
        public Employee assignDepartmentAndSalesPercentage(
                @PathVariable("department_id") BigDecimal departmentId,
                @PathVariable("sales_percentage") BigDecimal salesPercentage,
                @RequestParam("employee_id") BigDecimal employeeId) {
            return employeeService.assignDepartmentAndSalesPercentage(employeeId, departmentId, salesPercentage);
        }

        @GetMapping("/findfname/{firstname}")
        public Employee findByFirstName(@PathVariable("firstname") String firstName) {
            return employeeService.findByFirstName(firstName);
        }

        @GetMapping("/findemail/{email}")
        public Employee findByEmail(@PathVariable("email") String email) {
            return employeeService.findByEmail(email);
        }

        @GetMapping("/findphone/{phone}")
        public Employee findByPhoneNumber(@PathVariable("phone") String phoneNumber) {
            return employeeService.findByPhoneNumber(phoneNumber);
        }
         @GetMapping("/findTotalCommissionIssuedToEmployeeByDepartment/{department_id}")
            public BigDecimal findTotalCommissionIssuedToEmployeeByDepartment(@PathVariable("department_id") BigDecimal departmentId) {
            return employeeService.findTotalCommissionIssuedToEmployeeByDepartment(departmentId);
        }
         @GetMapping("/{empid}/findmaxsalaryofjob")
            public BigDecimal findMaxSalaryOfJobByEmployeeId(@PathVariable("empid") BigDecimal employeeId) {
            return employeeService.findMaxSalaryOfJobByEmployeeId(employeeId);
        }

        /*

        @GetMapping("/findAllEmployeeWithNoCommission")
        public List<Employee> findAllEmployeesWithNoCommission() {
            return employeeService.findAllEmployeesWithNoCommission();
        }


       @GetMapping("/listAllEmployeesByDepartment/{department_id}")
        public List<Employee> findAllEmployeesByDepartment(@PathVariable("department_id") BigDecimal departmentId) {
            return employeeService.findAllEmployeesByDepartment(departmentId);
        }

        @GetMapping("/countAllEmployeesGroupByDepartment")
        public List<Employee> findAllEmployeesGroupByDepartment() {
            return employeeService.findAllEmployeesGroupByDepartment();
        }

        @GetMapping("/listAllManagerDetails")
        public List<Employee> listAllManagerDetails() {
            return employeeService.listAllManagerDetails();
        }

        @GetMapping("/locationwisecountofemployees")
        public List<Employee> countAllEmployeesGroupByLocation() {
            return employeeService.countAllEmployeesGroupByLocation();
        }



        @PutMapping("/{email}")
        public Employee updateEmployeeEmail(@PathVariable("email") String email, @RequestParam("employee_id") BigDecimal employeeId) {
            return employeeService.updateEmployeeEmail(employeeId, email);
        }

        @PutMapping("/{phonenumber}")
        public Employee updateEmployeePhoneNumber(@PathVariable("phonenumber") String phoneNumber, @RequestParam("employee_id") BigDecimal employeeId) {
            return employeeService.updateEmployeePhoneNumber(employeeId, phoneNumber);
        }

        @GetMapping("/findAllOpenPositions")
        public List<Job> findAllOpenPositions() {
            return employeeService.findAllOpenPositions();
        }

        @GetMapping("/findAllOpenPositions/{department_id}")
        public List<Job> findAllOpenPositionsByDepartment(@PathVariable("department_id") BigDecimal departmentId) {
            return employeeService.findAllOpenPositionsByDepartment(departmentId);
        }

        @GetMapping("/listallemployeebyhiredate/{from_hiredate}/{to_hiredate}")
        public List<Employee> findAllEmployeesByHireDate(
                @PathVariable("from_hiredate") LocalDate fromHireDate,
                @PathVariable("to_hiredate") LocalDate toHireDate) {
            return employeeService.findAllEmployeesByHireDate(fromHireDate, toHireDate);
        }
        */

        @DeleteMapping("/{emp_id}")
        public void deleteEmployee(@PathVariable("emp_id") BigDecimal employeeId) {
            employeeService.deleteEmployee(employeeId);
        }


}

