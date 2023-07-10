package com.example.hra;

import com.example.hra.entities.Employee;
import com.example.hra.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

@SpringBootTest
class HumanResourceAppApplicationTests {

    EmployeeRepository employeeRepository;
    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Test
    public void testAddEmployee()
    {
        Employee employee = new Employee();
        employeeRepository.save(employee);
        Employee employee1 = employeeRepository.findByEmployeeId(new BigDecimal(12)).get();
        assertEquals(employee,employee1);
    }
}