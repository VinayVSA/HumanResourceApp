package com.example.hra.Repository;

import com.example.hra.Entity.Department;
import com.example.hra.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,BigDecimal> {
    BigDecimal findMinSalaryByDepartment(Department dep);

    BigDecimal findMaxSalaryByDepartment(Department dep);

    Employee findByFirstName(String firstName);

    Employee findByEmail(String email);

    Employee findByPhoneNumber(String phoneNumber);
    

    List<Employee> findByDepartmentId(BigDecimal departmentId);


    List<Employee> findByHireDateBetween(LocalDate fromHireDate, LocalDate toHireDate);
    

    List<Employee> findByCommissionPctIsNull();

    BigDecimal findTotalCommissionIssuedToEmployeeByDepartment(BigDecimal departmentId);


    List<Employee> findAllEmployeesGroupByDepartment();

    List<Employee> findByManagerIsNotNull();

    List<Employee> countAllEmployeesGroupByLocation();

    BigDecimal findMaxSalaryOfJobByEmployeeId(BigDecimal employeeId);
}
