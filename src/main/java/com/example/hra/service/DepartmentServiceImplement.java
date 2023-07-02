package com.example.hra.service;

import com.example.hra.Entity.Department;
import com.example.hra.Repository.DepartmentRepository;
import com.example.hra.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DepartmentServiceImplement implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public String addDepartment(Department department) {
        departmentRepository.save(department);
        return "Record Created Successfully";
    }

    @Override
    public String modifyDepartment(Department department) {
        departmentRepository.save(department);
        return "Record Modified Successfully";
    }

    @Override
    public Department getDepartmentById(BigDecimal departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        return department.orElse(null);
    }

    @Override
    public Map<String, BigDecimal> findMaxSalaryByDepartmentId(BigDecimal departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        Map<String, BigDecimal> result = new HashMap<>();
        department.ifPresent(dep -> {
            BigDecimal maxSalary = employeeRepository.findMaxSalaryByDepartment(dep);
            result.put(dep.getDepartmentName(), maxSalary);
        });
        return result;
    }

    @Override
    public Map<String, BigDecimal> findMinSalaryByDepartmentId(BigDecimal departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        Map<String, BigDecimal> result = new HashMap<>();
        department.ifPresent(dep -> {
            BigDecimal minSalary = employeeRepository.findMinSalaryByDepartment(dep);
            result.put(dep.getDepartmentName(),minSalary);
        });
        return result;
    }

    @Override
    public List<Department> getDepartmentsByEmployeeId(String empId) {
        return departmentRepository.findByEmployees_EmployeeId(empId);
    }

    @Override
    public void deleteDepartment(BigDecimal departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}

