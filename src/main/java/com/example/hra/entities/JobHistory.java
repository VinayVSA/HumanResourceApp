package com.example.hra.entities;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "job_history")
public class JobHistory implements Serializable {
    @EmbeddedId
    private JobHistoryId id;
    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id",insertable = false, updatable = false)
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "job_id",insertable = false, updatable = false)
    private Job job;
    @ManyToOne
    @JoinColumn(name = "department_id",insertable = false, updatable = false)
    private Department department;
    @Column(name = "start_date",insertable = false, updatable = false)
    private Date startDate;
    @NotNull
    @Column(name = "end_date")
    private Date endDate;
}