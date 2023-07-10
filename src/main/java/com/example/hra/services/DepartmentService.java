package com.example.hra.services;
import com.example.hra.entities.Department;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
public interface DepartmentService {
    String addDepartment(Department department);
    String modifyDepartment(Department department);
    Map<String, BigDecimal> findMaxSalaryByDepartmentId(BigDecimal departmentId);
    Map<String, BigDecimal> findMinSalaryByDepartmentId(BigDecimal departmentId);
    void deleteDepartmentById(BigDecimal departmentId);
    List<Department> getAllDepartments();
    List<Department> getDepartmentsByEmployeeId(BigDecimal employeeId);
}

