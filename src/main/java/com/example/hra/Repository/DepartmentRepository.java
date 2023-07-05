package com.example.hra.Repository;

import com.example.hra.Entity.Department;
import com.example.hra.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.EmptyStackException;
import java.util.List;


@Repository
public interface DepartmentRepository extends JpaRepository<Department,BigDecimal>
{


    //List<Employee> findByEmployeeDepartmentId(BigDecimal department_id);
    void deleteByDepartmentId(BigDecimal departmentId);

    Department findByDepartmentId(BigDecimal departmentId);

  //  List<Department> findByEmployeeId(BigDecimal employeeId);

}
