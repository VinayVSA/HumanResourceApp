package com.example.hra.controller;

import com.example.hra.Entity.Department;
import com.example.hra.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> addDepartment(@RequestBody Department department) {
        departmentService.addDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
    }



    @PutMapping
    public ResponseEntity<String> modifyDepartment(@RequestBody Department department) {
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



    @GetMapping("/{empId}")
    public ResponseEntity<List<Department>> getDepartmentsByEmployee(@PathVariable("employeeId") BigDecimal employeeId) {
        List<Department> departments = departmentService.getDepartmentsByEmployee(employeeId);
        return ResponseEntity.ok(departments);
    }



    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("departmentId") BigDecimal departmentId) {
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.noContent().build();
    }
}
