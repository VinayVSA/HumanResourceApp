package com.example.hra.service;

import com.example.hra.Entity.Employee;
import com.example.hra.Entity.Job;

import java.time.LocalDate;
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
    BigDecimal findTotalCommissionIssuedToEmployeeByDepartment(BigDecimal departmentId);

    // List<Employee> findAllEmployeesByDepartment(BigDecimal departmentId);
   // List<Employee> findAllEmployeesGroupByDepartment();
    //List<Employee> listAllManagerDetails();
    //List<Employee> countAllEmployeesGroupByLocation();
    //List<Employee> findAllEmployeesWithNoCommission();

    List<Employee> getAllEmployees();
    //List<Job> findAllOpenPositions();
    //List<Job> findAllOpenPositionsByDepartment(BigDecimal departmentId);

    //List<Employee> findAllEmployeesByHireDate(LocalDate fromHireDate, LocalDate toHireDate);

    Employee assignManagerToEmployee(BigDecimal employeeId, BigDecimal managerId);
    Employee assignDepartmentToEmployee(BigDecimal employeeId, BigDecimal departmentId);
    Employee assignDepartmentAndSalesPercentage(BigDecimal employeeId, BigDecimal departmentId, BigDecimal salesPercentage);



}

