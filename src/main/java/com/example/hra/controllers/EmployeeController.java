package com.example.hra.controllers;
import com.example.hra.dtos.DepartmentEmployeeCountDTO;
import com.example.hra.dtos.TotalCommissionDTO;
import com.example.hra.entities.Employee;
import com.example.hra.entities.Job;
import com.example.hra.exceptions.ValidationFailedException;
import com.example.hra.services.EmployeeService;
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
    private String validationFailed = "Validation Failed";
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
            throw new ValidationFailedException(validationFailed);
        Employee employee1=employeeService.addEmployee(employee);
        return new ResponseEntity<>(employee1,HttpStatus.OK);
    }
    @PutMapping("")
    public ResponseEntity<Employee> updateEmployee(@RequestBody @Valid Employee employee,BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new ValidationFailedException(validationFailed);
        return new ResponseEntity<>(employeeService.updateEmployee(employee),HttpStatus.OK);
    }
    @PutMapping("/{job_id}")
    public ResponseEntity<Employee> assignJobToEmployee(@PathVariable("job_id") String jobId,@RequestParam @Valid BigDecimal employeeId,BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new ValidationFailedException(validationFailed);
        return new ResponseEntity<>(employeeService.assignJobToEmployee( jobId,employeeId),HttpStatus.OK);
    }
    @GetMapping("/findfname/{firstname}")
    public ResponseEntity<List<Employee>> findByFirstName(@PathVariable("firstname") String firstName) {
        return new ResponseEntity<>(employeeService.findByFirstName(firstName),HttpStatus.OK);
    }
    @GetMapping("/findemail/{email}")
    public ResponseEntity<Employee> findByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(employeeService.findByEmail(email),HttpStatus.OK);
    }
    @GetMapping("/findphone/{phone}")
    public ResponseEntity<Employee> findByPhoneNumber(@PathVariable("phone") String phoneNumber) {
        return new ResponseEntity<>(employeeService.findByPhoneNumber(phoneNumber),HttpStatus.OK);
    }
    @DeleteMapping("/{employee_id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employee_id") BigDecimal employeeId) {
        return new ResponseEntity<>(employeeService.deleteEmployee(employeeId),HttpStatus.OK);
    }
    @GetMapping("/{employee_id}/findmaxsalaryofjob")
    public ResponseEntity<Map<String,BigDecimal>> findMaxSalaryOfJobByEmployeeId(@PathVariable("employee_id") BigDecimal employeeId) {
        return new ResponseEntity<>(employeeService.findMaxSalaryOfJobByEmployeeId(employeeId),HttpStatus.OK);
    }
    @GetMapping("/listAllEmployeesByDepartment/{department_id}")
    public ResponseEntity<List<Employee>> findAllEmployeesByDepartment(@PathVariable("department_id") BigDecimal departmentId) {
        return new ResponseEntity<>(employeeService.findAllEmployeesByDepartmentId(departmentId),HttpStatus.OK);
    }
    @GetMapping("/findAllEmployeeWithNoCommission")
    public ResponseEntity<List<Employee>> findAllEmployeesWithNoCommission() {
        return new ResponseEntity<>(employeeService.findAllEmployeesWithNoCommission(),HttpStatus.OK);
    }
    @GetMapping("/findAllOpenPositions")
    public ResponseEntity<List<Job>> findAllOpenPositions() {
        return new ResponseEntity<>(employeeService.findAllOpenPositions(),HttpStatus.OK);
    }
    @GetMapping("/listAllManagerDetails")
    public ResponseEntity<List<Employee>> listAllManagerDetails() {
        return new ResponseEntity<>(employeeService.listAllManagerDetails(),HttpStatus.OK);
    }
    @GetMapping("/findAllOpenPositions/{department_id}")
    public ResponseEntity<List<Job>> findAllOpenPositions(@PathVariable("department_id") BigDecimal departmentId) {
        return new ResponseEntity<>(employeeService.findAllOpenPositions(departmentId),HttpStatus.OK);
    }
    @GetMapping("/employees_departmentwise_count")
    public ResponseEntity<List<DepartmentEmployeeCountDTO>> countAllEmployeesGroupByDepartment() {
        return new ResponseEntity<>(employeeService.countAllEmployeesGroupByDepartment(),HttpStatus.OK);
    }
    @GetMapping("/locationwisecountofemployees")
    public ResponseEntity<List<Object[]>> countAllEmployeesGroupByLocation() {
        return new ResponseEntity<>(employeeService.countAllEmployeesGroupByLocation(),HttpStatus.OK);
    }
    @GetMapping("/listallemployeebyhiredate/{from_hiredate}/{to_hiredate}")
    public ResponseEntity<List<Employee>> listAllEmployeeByHireDateRange(
            @PathVariable("from_hiredate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromHireDate,
            @PathVariable("to_hiredate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toHireDate) {
        return new ResponseEntity<>(employeeService.listAllEmployeeByHireDateRange(fromHireDate, toHireDate),HttpStatus.OK);
    }
    @GetMapping("/findTotalCommissionIssuedToEmployeeByDepartment/{department_id}")
    public ResponseEntity<TotalCommissionDTO> findTotalCommissionIssuedToEmployeeByDepartment(
            @PathVariable("department_id") BigDecimal departmentId) {
        return new ResponseEntity<>(employeeService.findTotalCommissionIssuedToEmployeeByDepartment(departmentId),HttpStatus.OK);
    }
    @PutMapping("/{department_id}/{sales_percentage}")
    public ResponseEntity<String> assignDepartmentAndUpdateSalesPercentage(
            @PathVariable("department_id") BigDecimal departmentId,
            @PathVariable("sales_percentage") BigDecimal salesPercentage) {
        String result = employeeService.assignDepartmentAndUpdateSalesPercentage(departmentId, salesPercentage);
        if (result.equals("Record Modified Successfully"))
            return ResponseEntity.ok(result);
        else
            throw new ValidationFailedException(validationFailed+result);
    }
    @PutMapping("/mg=/{manager_id}")
    public ResponseEntity<String> assignManager(
            @PathVariable("manager_id") BigDecimal managerId,
            @RequestBody @Valid BigDecimal employeeId,BindingResult bindingResult) {
        String result = employeeService.assignManager(employeeId, managerId);
        if (bindingResult.hasErrors())
            throw new ValidationFailedException(validationFailed+result);
        return ResponseEntity.ok(result);
    }
    @PutMapping("/em=/{email}")
    public ResponseEntity<String> updateEmployeeEmail(@PathVariable("email") String email,
                                                      @RequestParam BigDecimal employeeId) {
        return new ResponseEntity<>(employeeService.updateEmployeeEmailByEmployeeId(email,employeeId),HttpStatus.OK);
    }
    @PutMapping("/ph=/{phonenumber}")
    public ResponseEntity<String> updateEmployeePhoneNumber( @PathVariable("phonenumber") String phoneNumber,
                                                              @RequestParam BigDecimal employeeId) {
        return new ResponseEntity<>(employeeService.updateEmployeePhoneNumberByEmployeeId(phoneNumber,employeeId),HttpStatus.OK);
    }
    @PutMapping("/dp/{department_id}")
    public ResponseEntity<String> assignDepartment(
            @PathVariable("department_id") BigDecimal departmentId,
            @RequestBody@Valid BigDecimal employeeId,BindingResult bindingResult) {
        String result = employeeService.assignDepartment(employeeId, departmentId);
        if (bindingResult.hasErrors())
            throw new ValidationFailedException(validationFailed+result);
        return ResponseEntity.ok(result);
    }
}