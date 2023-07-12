package com.example.hra.entities;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;
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
    @GenericGenerator(name = "randomStringGenerator", strategy = "com.example.hra.entities.RandomStringGenerator")
    @Column(name = "job_id", length=10)
    private String jobId;
    @Pattern(regexp = "^[a-zA-Z[\\s]*[a-zA-Z]]+$", message = "Job Title should contain only alphabets")
    @NotBlank(message = "Job Title is required")
    @Column(name = "job_title")
    private String jobTitle;
    @DecimalMin(value = "0")
    @Column(name = "min_salary")
    private BigDecimal minSalary;
    @DecimalMax(value="99999")
    @Column(name = "max_salary")
    private BigDecimal maxSalary;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return jobId.equals(job.jobId) &&
                jobTitle.equals(job.jobTitle) &&
                minSalary.equals(job.minSalary) &&
                maxSalary.equals(job.maxSalary);
    }
    @Override
    public int hashCode() {
        return Objects.hash(jobId, jobTitle, minSalary, maxSalary);
    }
}