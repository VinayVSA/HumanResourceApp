package com.example.hra.service;

import com.example.hra.Entity.Employee;
import com.example.hra.Entity.Job;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

public interface EmployeeService {
    Employee addEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    Employee assignJobToEmployee(BigDecimal employeeId, String jobId);
    Employee assignManagerToEmployee(BigDecimal employeeId, BigDecimal managerId);
    Employee assignDepartmentToEmployee(BigDecimal employeeId, BigDecimal departmentId);
    Employee assignDepartmentAndSalesPercentage(BigDecimal employeeId, BigDecimal departmentId, BigDecimal salesPercentage);

    Employee findByFirstName(String firstName);

    Employee findByEmail(String email);
    Employee findByPhoneNumber(String phoneNumber);
    //List<Employee> findAllEmployeesWithNoCommission();
    BigDecimal findTotalCommissionIssuedToEmployeeByDepartment(BigDecimal departmentId);
    // List<Employee> findAllEmployeesByDepartment(BigDecimal departmentId);
   // List<Employee> findAllEmployeesGroupByDepartment();
    //List<Employee> listAllManagerDetails();
    //List<Employee> countAllEmployeesGroupByLocation();
    BigDecimal findMaxSalaryOfJobByEmployeeId(BigDecimal employeeId);
    Employee updateEmployeeEmail(BigDecimal employeeId, String email);
    Employee updateEmployeePhoneNumber(BigDecimal employeeId, String phoneNumber);
    //List<Job> findAllOpenPositions();
    //List<Job> findAllOpenPositionsByDepartment(BigDecimal departmentId);

    //List<Employee> findAllEmployeesByHireDate(LocalDate fromHireDate, LocalDate toHireDate);

    void deleteEmployee(BigDecimal employeeId);
}

