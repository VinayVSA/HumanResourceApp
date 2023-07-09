package com.example.hra.entity;
import com.example.hra.exception.EmployeeNotFoundException;
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
    @NotBlank(message = "Hire date is required")
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-([0-2][1-9]|3[01])$", message = "Hire date is not valid")
    @Column(name = "hire_date")
    private Date hireDate;
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
    @Column(name = "salary")
    private BigDecimal salary;
    @DecimalMin(value = "1", message = "Commission percentage must be greater than 0 or null")
    @DecimalMax(value = "100", message = "Commission percentage must be less than or equal to 1")
    @Column(name = "commission_pct")
    private BigDecimal commissionPct;
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "Manager ID should be a valid decimal number")
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

