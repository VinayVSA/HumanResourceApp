package com.example.hra.controller;
import com.example.hra.dto.DepartmentEmployeeCountDTO;
import com.example.hra.dto.TotalCommissionDTO;
import com.example.hra.entity.Employee;
import com.example.hra.entity.Job;
import com.example.hra.exception.ValidationFailedException;
import com.example.hra.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
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
    @GetMapping("")
    public  List<Employee> allEmployees()
    {
        return employeeService.getAllEmployees();
    }
    @PostMapping("")
    public ResponseEntity<Employee> addEmployee(@RequestBody @Valid Employee employee, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new ValidationFailedException("Validation Failed");
        Employee employee1= employeeService.addEmployee(employee);
        return new ResponseEntity<>(employee1,HttpStatus.OK);
    }
    @PutMapping("")
    public Employee updateEmployee(@RequestBody @Valid Employee employee,BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new ValidationFailedException("Validation Failed");
        return employeeService.updateEmployee(employee);
    }
    @PutMapping("/{job_id}")
    public Employee assignJobToEmployee(@PathVariable String job_id, @RequestParam @Valid BigDecimal employeeId,BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new ValidationFailedException("Validation Failed");
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
    public Employee updateEmployeeEmail(@PathVariable("email") String email, @RequestParam("employee_id")@Valid BigDecimal employee_id,BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new ValidationFailedException("Validation Failed");
        return employeeService.updateEmployeeEmailByEmployeeId(email,employee_id);
    }
    @PutMapping("/ph=/{phonenumber}")
    public Employee updateEmployeePhoneNumber(@PathVariable("phonenumber") String phoneNumber, @RequestParam("employee_id")@Valid BigDecimal employee_id,BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new ValidationFailedException("Validation Failed");
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

        if (result.equals("Record Modified Successfully"))
            return ResponseEntity.ok(result);
        else
            throw new ValidationFailedException("Validation Failed "+result);
    }
    @PutMapping("/mg=/{manager_id}")
    public ResponseEntity<String> assignManager(
            @PathVariable("manager_id") BigDecimal managerId,
            @RequestBody @Valid BigDecimal employeeId,BindingResult bindingResult) {
        String result = employeeService.assignManager(employeeId, managerId);
        if (bindingResult.hasErrors())
            throw new ValidationFailedException("Validation Failed "+result);
        return ResponseEntity.ok(result);
    }
    @PutMapping("/dp/{department_id}")
    public ResponseEntity<String> assignDepartment(
            @PathVariable("department_id") BigDecimal departmentId,
            @RequestBody@Valid BigDecimal employeeId,BindingResult bindingResult) {
        String result = employeeService.assignDepartment(employeeId, departmentId);
        if (bindingResult.hasErrors())
            throw new ValidationFailedException("Validation Failed "+result);
        return ResponseEntity.ok(result);
    }
}