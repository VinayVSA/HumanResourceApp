package com.example.hra.entity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.*;
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
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Job name should contain only alphabets")
    @NotBlank(message = "Country name is required")
    @Column(name = "job_title")
    private String jobTitle;
    @DecimalMin(value = "0")
    @Column(name = "min_salary")
    private BigDecimal minSalary;
    @DecimalMax(value="99999")
    @Column(name = "max_salary")
    private BigDecimal maxSalary;
}