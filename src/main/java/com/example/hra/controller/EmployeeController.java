package com.example.hra.controller;

import com.example.hra.dto.DepartmentEmployeeCountDTO;
import com.example.hra.dto.TotalCommissionDTO;
import com.example.hra.entity.Employee;
import com.example.hra.entity.Job;
import com.example.hra.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
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
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee employee1= employeeService.addEmployee(employee);
        return new ResponseEntity<>(employee1,HttpStatus.OK);
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
    public List<Employee> findByFirstName(@PathVariable("firstname") String firstName) {
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

    @GetMapping("/listAllEmployeesByDepartment/{department_id}")
    public List<Employee> findAllEmployeesByDepartment(@PathVariable("department_id") BigDecimal departmentId) {
        return employeeService.findAllEmployeesByDepartmentId(departmentId);
    }
    @GetMapping("/findAllEmployeeWithNoCommission")
    public List<Employee> findAllEmployeesWithNoCommission() {
        return employeeService.findAllEmployeesWithNoCommission();
    }

    @GetMapping("/findAllOpenPositions")
    public List<Job> findAllOpenPositions() {
        return employeeService.findAllOpenPositions();
    }

    @GetMapping("/listAllManagerDetails")
    public List<Employee> listAllManagerDetails() {
        return employeeService.listAllManagerDetails();
    }

    @GetMapping("/findAllOpenPositions/{department_id}")
    public List<Job> findAllOpenPositions(@PathVariable("department_id") BigDecimal departmentId) {
        return employeeService.findAllOpenPositions(departmentId);
    }
    @GetMapping("/employees_departmentwise_count")
    public List<DepartmentEmployeeCountDTO> countAllEmployeesGroupByDepartment() {
        return employeeService.countAllEmployeesGroupByDepartment();
    }

    @GetMapping("/locationwisecountofemployees")
    public List<Object[]> countAllEmployeesGroupByLocation() {
        return employeeService.countAllEmployeesGroupByLocation();
    }

    @GetMapping("/listallemployeebyhiredate/{from_hiredate}/{to_hiredate}")
    public List<Employee> listAllEmployeeByHireDateRange(
            @PathVariable("from_hiredate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromHireDate,
            @PathVariable("to_hiredate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toHireDate) {
        return employeeService.listAllEmployeeByHireDateRange(fromHireDate, toHireDate);
    }

    @GetMapping("/findTotalCommissionIssuedToEmployeeByDepartment/{department_id}")
    public TotalCommissionDTO findTotalCommissionIssuedToEmployeeByDepartment(
            @PathVariable("department_id") BigDecimal departmentId) {
        return employeeService.findTotalCommissionIssuedToEmployeeByDepartment(departmentId);
    }

    @PutMapping("/{department_id}/{sales_percentage}")
    public ResponseEntity<String> assignDepartmentAndUpdateSalesPercentage(
            @PathVariable("department_id") BigDecimal departmentId,
            @PathVariable("sales_percentage") BigDecimal salesPercentage) {

        String result = employeeService.assignDepartmentAndUpdateSalesPercentage(departmentId, salesPercentage);

        if (result.equals("Record Modified Successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }
    @PutMapping("/mg=/{manager_id}")
    public ResponseEntity<String> assignManager(
            @PathVariable("manager_id") BigDecimal managerId,
            @RequestBody BigDecimal employeeId) {

        String result = employeeService.assignManager(employeeId, managerId);
        if (result.equals("Record Modified Successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }
    @PutMapping("/dp/{department_id}")
    public ResponseEntity<String> assignDepartment(
            @PathVariable("department_id") BigDecimal departmentId,
            @RequestBody BigDecimal employeeId) {

        String result = employeeService.assignDepartment(employeeId, departmentId);

        if (result.equals("Record Modified Successfully")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

}

