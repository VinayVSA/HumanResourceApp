package com.example.hra.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="EMPLOYEE")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Employees {

    @Id
    @SequenceGenerator(name="EmployeeSequence", sequenceName="EMPLOYEES_SEQ", allocationSize=6)
    @GeneratedValue(generator="EmployeeSequence")
    private Long employeeId;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String phoneNumber;
    @Column
    private Date hireDate;
    @Column
    private BigDecimal salary;
    @Column
    private BigDecimal commissionPercent;

    @ManyToOne
    private Employees manager;

    @ManyToOne(fetch = FetchType.LAZY)
    private Departments department;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn
    private Jobs job;
}
