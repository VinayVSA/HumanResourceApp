package com.example.hra.service;

import com.example.hra.Dto.DepartmentEmployeeCountDTO;
import com.example.hra.Dto.TotalCommissionDTO;
import com.example.hra.Entity.Employee;
import com.example.hra.Entity.Job;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.util.Map;

public interface EmployeeService {
    Employee addEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    Employee assignJobToEmployee(BigDecimal employeeId, String jobId);
    Employee findByFirstName(String firstName);
    Employee findByEmail(String email);
    Employee findByPhoneNumber(String phoneNumber);
    void deleteEmployee(BigDecimal employeeId);
    Map<String,BigDecimal> findMaxSalaryOfJobByEmployeeId(BigDecimal employeeId);
    Employee updateEmployeeEmailByEmployeeId( String email,BigDecimal employeeId);
    Employee updateEmployeePhoneNumberByEmployeeId( String phoneNumber,BigDecimal employeeId);
    TotalCommissionDTO findTotalCommissionIssuedToEmployeeByDepartment(BigDecimal departmentId);
    List<Employee> findAllEmployeesByDepartmentId(BigDecimal departmentId);
    List<Employee> listAllManagerDetails();
    List<Employee> findAllEmployeesWithNoCommission();
    List<Employee> findAllEmployeesGroupByDepartment(BigDecimal departmentId);
    List<Employee> getAllEmployees();
    Employee assignDepartmentAndSalesPercentage(BigDecimal employeeId, BigDecimal departmentId, BigDecimal salesPercentage);
    List<DepartmentEmployeeCountDTO> countAllEmployeesGroupByDepartment();

    List<Job> findAllOpenPositions(BigDecimal departmentId);

    List<Job> findAllOpenPositions();


    List<Object[]> countAllEmployeesGroupByLocation();

    List<Employee> listAllEmployeeByHireDateRange(Date fromHireDate, Date toHireDate);

    String assignDepartmentAndUpdateSalesPercentage(BigDecimal departmentId, BigDecimal salesPercentage);

    String assignManager(BigDecimal employeeId, BigDecimal managerId);

    String assignDepartment(BigDecimal employeeId, BigDecimal departmentId);
}

