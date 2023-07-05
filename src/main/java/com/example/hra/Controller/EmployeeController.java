package com.example.hra.Controller;

import com.example.hra.Entity.Employee;
import com.example.hra.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    //Service Methods

    @GetMapping("")
    public  List<Employee> allEmployees()
    {
        return employeeService.getAllEmployees();
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
    public Employee assignJobToEmployee(@PathVariable String job_id, @RequestParam BigDecimal employeeId) {
        return employeeService.assignJobToEmployee(employeeId, job_id);

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

    @DeleteMapping("/{employee_id}")
    public void deleteEmployee(@PathVariable("employee_id") BigDecimal employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @GetMapping("/{employee_id}/findmaxsalaryofjob")
    public Map<String,BigDecimal> findMaxSalaryOfJobByEmployeeId(@PathVariable("employee_id") BigDecimal employeeId) {
        return employeeService.findMaxSalaryOfJobByEmployeeId(employeeId);
    }

    @PutMapping("/em=/{email}")
    public Employee updateEmployeeEmail(@PathVariable("email") String email, @RequestParam("employee_id") BigDecimal employee_id) {
        return employeeService.updateEmployeeEmailByEmployeeId(email,employee_id);
    }

    @PutMapping("/ph=/{phonenumber}")
    public Employee updateEmployeePhoneNumber(@PathVariable("phonenumber") String phoneNumber, @RequestParam("employee_id") BigDecimal employee_id) {
        return employeeService.updateEmployeePhoneNumberByEmployeeId(phoneNumber,employee_id);
    }




    //not working
    @GetMapping("/findTotalCommissionIssuedToEmployeeByDepartment/{department_id}")
    public BigDecimal findTotalCommissionIssuedToEmployeeByDepartment(@PathVariable("department_id") BigDecimal departmentId) {
        return employeeService.findTotalCommissionIssuedToEmployeeByDepartment(departmentId);
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




}

