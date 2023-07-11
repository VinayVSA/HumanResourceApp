package com.example.hra.services;
import com.example.hra.entities.Department;
import com.example.hra.entities.Employee;
import com.example.hra.exceptions.DepartmentNotFoundException;
import com.example.hra.exceptions.EmployeeNotFoundException;
import com.example.hra.repositories.DepartmentRepository;
import com.example.hra.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public void deleteDepartmentById(BigDecimal departmentId) {
        departmentRepository.findByDepartmentId(departmentId).orElseThrow(() -> new DepartmentNotFoundException("Department Not Found"));
        try {
            departmentRepository.deleteByDepartmentId(departmentId);
        } catch (RuntimeException re) {
            throw new DepartmentNotFoundException("Cannot delete or update a parent row: a foreign key constraint fails");
        }
    }

    @Override
    public Map<String, BigDecimal> findMaxSalaryByDepartmentId(BigDecimal departmentId) {
        Map<String, BigDecimal> hashMap = new HashMap<String, BigDecimal>();
        Department department = departmentRepository.findByDepartmentId(departmentId).orElseThrow(() -> new DepartmentNotFoundException("Department Not Found"));
        List<Employee> employees = department.getEmployees();
        BigDecimal max = employees.stream()
                .map(Employee::getSalary)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
        hashMap.put(department.getDepartmentName(), max);
        return hashMap;
    }

    @Override
    public Map<String, BigDecimal> findMinSalaryByDepartmentId(BigDecimal departmentId) {
        Map<String, BigDecimal> hashMap = new HashMap<String, BigDecimal>();
        Department department = departmentRepository.findByDepartmentId(departmentId).orElseThrow(() -> new DepartmentNotFoundException("Department Not Found"));
        List<Employee> employees = department.getEmployees();
        BigDecimal min = employees.stream()
                .map(Employee::getSalary)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
        hashMap.put(department.getDepartmentName(), min);
        return hashMap;
    }

    @Override
    public List<Department> getDepartmentsByEmployeeId(BigDecimal employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found"));
        List<Department> ls = new ArrayList<>();
        ls.add(employee.getDepartment());
        return ls;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}