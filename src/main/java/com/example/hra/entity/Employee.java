package com.example.hra.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@ToString
@Table(name = "employees")
@Entity

public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private BigDecimal employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "hire_date")
    private Date hireDate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "commission_pct")
    private BigDecimal commissionPct;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;

    public Employee()
    {
        super();
    }

    @OneToMany (mappedBy = "manager",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees;
}

