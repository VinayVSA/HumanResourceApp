package com.example.hra.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;


@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobHistoryId implements Serializable {

    @Column(name = "employee_id")
    private BigDecimal employeeId;

    @Column(name = "start_date")
    private Date startDate;

    // Equals and hashCode methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobHistoryId)) return false;
        JobHistoryId that = (JobHistoryId) o;
        return Objects.equals(getEmployeeId(), that.getEmployeeId()) &&
                Objects.equals(getStartDate(), that.getStartDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeId(), getStartDate());
    }


}

