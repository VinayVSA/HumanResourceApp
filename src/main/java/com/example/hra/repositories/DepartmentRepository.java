package com.example.hra.repositories;
import com.example.hra.entities.Department;
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