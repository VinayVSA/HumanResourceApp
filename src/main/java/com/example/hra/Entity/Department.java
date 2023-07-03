package com.example.hra.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments")
public class Department {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_id")
    private BigDecimal departmentId;



    @Column(name = "department_name")
    private String departmentName;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

}

