package com.example.hra.repository;

import com.example.hra.entity.Department;
import com.example.hra.entity.Employee;
import com.example.hra.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
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

    BigDecimal findTotalCommissionIssuedToEmployeeByDepartment(BigDecimal departmentId);
    List<Employee> findAllEmployeesGroupByDepartment(BigDecimal departmentId);

    BigDecimal findMaxSalaryOfJobByEmployeeId(BigDecimal employeeId);

    
    Employee findByEmployeeId(BigDecimal employeeId);
    Employee getEmployeeByEmployeeId(BigDecimal employeeId);
    List<Employee> findByCommissionPctIsNull();
    @Query("SELECT e.department.departmentName, COUNT(e) FROM Employee e GROUP BY e.department.departmentName")
    List<Object[]> countEmployeesGroupByDepartment();
    @Query("SELECT e.job FROM Employee e WHERE e.department.departmentId = :departmentId AND e.employeeId NOT IN (SELECT m.manager FROM Employee m)")
    List<Job> findOpenPositionsByDepartmentId(@Param("departmentId") Long departmentId);

    @Query("SELECT e.department.location.locationId, COUNT(e) FROM Employee e GROUP BY e.department.location.locationId")
    List<Object[]> countEmployeesGroupByLocation();

    List<Employee> findAllByHireDateBetween(Date fromHireDate, Date toHireDate);

    @Query("SELECT SUM(e.commissionPct) FROM Employee e WHERE e.department.departmentId = :departmentId")
    BigDecimal calculateTotalCommissionByDepartment(@Param("departmentId") BigDecimal departmentId);

}
