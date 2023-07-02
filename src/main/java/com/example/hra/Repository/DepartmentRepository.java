package com.example.hra.Repository;

import com.example.hra.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer>
{

    List<Department> findByEmployees_EmployeeId(String empId);

    Optional<Department> findById(BigDecimal departmentId);

    void deleteById(BigDecimal departmentId);
}
