package com.example.hra.controllers;
import com.example.hra.entities.Department;
import com.example.hra.exceptions.ValidationFailedException;
import com.example.hra.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {
    private DepartmentService departmentService;
    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @PostMapping
    public ResponseEntity<String> addDepartment(@RequestBody @Valid Department department, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new ValidationFailedException("Validation Failed");
        departmentService.addDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
    }
    @PutMapping
    public ResponseEntity<String> modifyDepartment(@RequestBody @Valid Department department,BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new ValidationFailedException("Validation Failed");
        departmentService.modifyDepartment(department);
        return ResponseEntity.ok("Record Modified Successfully");
    }
    @GetMapping("/findmaxsalary/{departmentId}")
    public ResponseEntity<Map<String, BigDecimal>> findMaxSalaryInDepartment(@PathVariable("departmentId")BigDecimal departmentId) {
        Map<String, BigDecimal> result = departmentService.findMaxSalaryByDepartmentId(departmentId);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/findminsalary/{departmentId}")
    public ResponseEntity<Map<String, BigDecimal>> findMinSalaryInDepartment(@PathVariable("departmentId") BigDecimal departmentId) {
        Map<String, BigDecimal> result = departmentService.findMinSalaryByDepartmentId(departmentId);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/{employeeId}")
    public ResponseEntity<List<Department>> getDepartmentsByEmployee(@PathVariable("employeeId") BigDecimal employeeId) {
        List<Department> departments = departmentService.getDepartmentsByEmployeeId(employeeId);
        return ResponseEntity.ok(departments);
    }
    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("departmentId") BigDecimal departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("")
    public ResponseEntity<List<Department>> getAllEmployees() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }
}
