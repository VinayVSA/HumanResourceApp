package com.example.hra.entities;
import com.example.hra.exceptions.EmployeeNotFoundException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@ToString
@Table(name = "employees")
@Entity
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private BigDecimal employeeId;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name should contain only alphabets")
    @NotBlank(message = "First name is required")
    @Column(name = "first_name")
    private String firstName;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should contain only alphabets")
    @NotBlank(message = "Last name is required")
    @Column(name = "last_name")
    private String lastName;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(name = "email")
    private String email;
    @Pattern(regexp = "[6-9]+[0-9]{9}", message = "Phone number should contain only numbers")
    @NotBlank(message = "Phone Number  is required")
    @Column(name = "phone_number")
    private String phoneNumber;
    @NotNull(message = "Hire date is required")
    @Column(name = "hire_date")
    private Date hireDate;
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
    @Column(name = "salary")
    private BigDecimal salary;
    @Column(name = "commission_pct")
    private BigDecimal commissionPct;
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;
    public Employee()
    {super();}
    @OneToMany (mappedBy = "manager",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees;
    @PrePersist
    @PreUpdate
    private void validateSalary() {
        if (job != null && (salary.compareTo(job.getMinSalary()) < 0 || salary.compareTo(job.getMaxSalary()) > 0)) {
            throw new EmployeeNotFoundException("Employee salary is not within the valid range for the job title.");
        }
    }
}

