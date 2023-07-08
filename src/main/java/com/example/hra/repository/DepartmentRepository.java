package com.example.hra.repository;
import com.example.hra.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.Optional;
@Repository
public interface DepartmentRepository extends JpaRepository<Department,BigDecimal>
{
    void deleteByDepartmentId(BigDecimal departmentId);
    Optional<Department> findByDepartmentId(BigDecimal departmentId);
}