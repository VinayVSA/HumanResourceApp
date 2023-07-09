package com.example.hra.entity;
import lombok.*;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
@Embeddable
@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobHistoryId implements Serializable {
    @Column(name = "employee_id")
    private BigDecimal employeeId;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name="job_id")
    private String jobId;
    @Column(name = "department_Id")
    private BigDecimal departmentId;
    // Equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobHistoryId)) return false;
        JobHistoryId that = (JobHistoryId) o;
        return Objects.equals(getEmployeeId(), that.getEmployeeId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeId(), getStartDate());
    }
}

