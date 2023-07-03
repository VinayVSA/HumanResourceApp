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
        Department department = departmentRepository.findByDepartmentId(departmentId);
        return department;
    }

    @Override
    public Map<String, BigDecimal> findMaxSalaryByDepartmentId(BigDecimal departmentId) {

        Department department = departmentRepository.findByDepartmentId(departmentId);
        Map<String, BigDecimal> result = new HashMap<>();
        BigDecimal maxSalary = employeeRepository.findMaxSalaryByDepartment(department);
        if (maxSalary != null)
        {
            result.put(department.getDepartmentName(),maxSalary);
        }
        return result;
    }

    @Override
    public Map<String, BigDecimal> findMinSalaryByDepartmentId(BigDecimal departmentId) {
        Department department = departmentRepository.findByDepartmentId(departmentId);
        Map<String, BigDecimal> result = new HashMap<>();
        BigDecimal minSalary = employeeRepository.findMinSalaryByDepartment(department);
        if (minSalary != null)
        {
            result.put(department.getDepartmentName(),minSalary);
        }
        return result;
    }
    @Override
    public List<Department> getDepartmentsByEmployee(BigDecimal employeeId) {
        return departmentRepository.findByEmployeeId(employeeId);
    }

    @Override
    public void deleteDepartment(BigDecimal departmentId) {
        departmentRepository.deleteByDepartmentId(departmentId);
    }
}

