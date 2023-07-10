package com.example.hra.repositories;
import com.example.hra.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,BigDecimal> {
    Optional<List<Employee>> findByFirstName(String firstName);
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByPhoneNumber(String phoneNumber);
    List<Employee> findAllEmployeesGroupByDepartment(BigDecimal departmentId);
    Optional<Employee> findByEmployeeId(BigDecimal employeeId);
    Optional<List<Employee>> findByCommissionPctIsNull();
    @Query("SELECT e.department.departmentName, COUNT(e) FROM Employee e GROUP BY e.department.departmentName")
    List<Object[]> countEmployeesGroupByDepartment();
    @Query("SELECT e.department.location.city, COUNT(e) FROM Employee e GROUP BY e.department.location.locationId")
    List<Object[]> countEmployeesGroupByLocation();
    List<Employee> findAllByHireDateBetween(Date fromHireDate, Date toHireDate);
    @Query("SELECT SUM(e.commissionPct) FROM Employee e WHERE e.department.departmentId = :departmentId")
    BigDecimal calculateTotalCommissionByDepartment(@Param("departmentId") BigDecimal departmentId);
}
