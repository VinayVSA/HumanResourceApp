package com.example.hra.service;

import com.example.hra.Entity.Department;
import com.example.hra.Entity.Employee;
import com.example.hra.Repository.DepartmentRepository;
import com.example.hra.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

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
    @Transactional
    public void deleteDepartment(BigDecimal departmentId) {
        Department department = departmentRepository.findByDepartmentId(departmentId);
        if(department!=null)
        {
            departmentRepository.deleteByDepartmentId(departmentId);
        }

    }


    @Override
    public Map<String, BigDecimal> findMaxSalaryByDepartmentId(BigDecimal departmentId) {
        Map<String,BigDecimal> hashMap = new HashMap<String, BigDecimal>() ;
        Department department = departmentRepository.findByDepartmentId(departmentId);
        List<Employee> employees = department.getEmployees();
        BigDecimal max = employees.stream()
                .map(Employee::getSalary)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
        hashMap.put(department.getDepartmentName(),max);
        return hashMap;

    }

    @Override
    public Map<String, BigDecimal> findMinSalaryByDepartmentId(BigDecimal departmentId) {
        Map<String,BigDecimal> hashMap = new HashMap<String, BigDecimal>() ;
        Department department = departmentRepository.findByDepartmentId(departmentId);
        List<Employee> employees = department.getEmployees();
        BigDecimal min = employees.stream()
                .map(Employee::getSalary)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
        hashMap.put(department.getDepartmentName(),min);
        return hashMap;
    }
    @Override
    public List<Department> getDepartmentsByEmployeeId(BigDecimal employeeId) {
        Employee employee=employeeRepository.findByEmployeeId(employeeId);
        List<Department> ls = new ArrayList<>();
        if(employee!=null)
        {
            ls.add(employee.getDepartment());
            return ls;
        }
        return null;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
   }
}

