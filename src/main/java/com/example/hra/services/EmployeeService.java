package com.example.hra.services;

import com.example.hra.dtos.DepartmentEmployeeCountDTO;

import com.example.hra.dtos.TotalCommissionDTO;
import com.example.hra.entities.Employee;
import com.example.hra.entities.Job;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.util.Map;

public interface EmployeeService {
    Employee addEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    Employee assignJobToEmployee(BigDecimal employeeId, String jobId);
    List<Employee> findByFirstName(String firstName);
    Employee findByEmail(String email);
    Employee findByPhoneNumber(String phoneNumber);
    String deleteEmployee(BigDecimal employeeId);
    Map<String,BigDecimal> findMaxSalaryOfJobByEmployeeId(BigDecimal employeeId);
    String updateEmployeeEmailByEmployeeId( String email,BigDecimal employeeId);
    String updateEmployeePhoneNumberByEmployeeId( String phoneNumber,BigDecimal employeeId);
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

