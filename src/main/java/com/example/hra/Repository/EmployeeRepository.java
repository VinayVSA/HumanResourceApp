package com.example.hra.Repository;

import com.example.hra.Entity.Department;
import com.example.hra.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,BigDecimal> {
    BigDecimal findMinSalaryByDepartment(Department dep);

    BigDecimal findMaxSalaryByDepartment(Department dep);

    Optional<Employee> findById(BigDecimal employeeId);
}
