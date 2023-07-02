package com.example.hra.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="JOBHISTORY")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    private Employees employee;

    @Column
    private Date startDate;
    @Column
    private Date endDate;

    @ManyToOne
    private Jobs job;

    @ManyToOne
    private Departments department;
}
