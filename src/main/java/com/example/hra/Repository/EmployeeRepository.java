package com.example.hra.Repository;

import com.example.hra.Entity.Department;
import com.example.hra.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,BigDecimal> {


    BigDecimal findMinSalaryOfEmployeeByDepartment(Department dep);

    BigDecimal findMaxSalaryOfEmployeeByDepartment(Department dep);

    Employee findByFirstName(String firstName);

    Employee findByEmail(String email);

    Employee findByPhoneNumber(String phoneNumber);


    @Query("SELECT d FROM Department d WHERE d.departmentId = :departmentId")
    List<Employee> findByDepartmentId(@Param("departmentId") BigDecimal departmentId);


    //List<Employee> findByHireDateBetween(LocalDate fromHireDate, LocalDate toHireDate);
    

    //List<Employee> findByCommissionPctIsNull();

    BigDecimal findTotalCommissionIssuedToEmployeeByDepartment(BigDecimal departmentId);


    //List<Employee> findAllEmployeesGroupByDepartment();

  //  List<Employee> findByManagerIsNotNull();

    //List<Employee> countAllEmployeesGroupByLocation();

    BigDecimal findMaxSalaryOfJobByEmployeeId(BigDecimal employeeId);

    
    Employee findByEmployeeId(BigDecimal employeeId);

    Employee getEmployeeByEmployeeId(BigDecimal employeeId);
}
