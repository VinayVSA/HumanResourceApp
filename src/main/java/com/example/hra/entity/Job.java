package com.example.hra.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "jobs")
public class Job  {
    @Id
    @GeneratedValue(generator = "randomStringGenerator")
    @GenericGenerator(name = "randomStringGenerator", strategy = "com.example.hra.entity.RandomStringGenerator")
    @Column(name = "job_id", length=10)
    private String jobId;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "min_salary")
    private BigDecimal minSalary;

    @Column(name = "max_salary")
    private BigDecimal maxSalary;

}

