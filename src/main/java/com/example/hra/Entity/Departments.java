package com.example.hra.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="DEPARTMENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Departments {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departmentId;
    @Column
    private String departmentName;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Employees employee;

    @ManyToOne
    private Locations locations;

}
