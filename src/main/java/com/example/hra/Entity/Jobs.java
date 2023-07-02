package com.example.hra.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="JOB")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Jobs {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private String jobId;
    @Column
    private String JobTitle;
    @Column
    private BigDecimal MinSalary;
    @Column
    private BigDecimal MaxSalary;
}
