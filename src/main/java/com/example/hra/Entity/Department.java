package com.example.hra.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments")
@Entity
public class Department {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_id")
    private BigDecimal departmentId;


    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "manager_id")
    private BigDecimal managerId;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "employee_id",insertable = false,updatable = false)
    private Employee employee;

    @OneToMany(mappedBy = "department")
    @JsonManagedReference
    private List<Employee> employees;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

}

