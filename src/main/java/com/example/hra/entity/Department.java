package com.example.hra.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.validation.constraints.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "departments")
@Entity
public class Department {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_id")
    private BigDecimal departmentId;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Department name should contain only alphabets")
    @NotBlank(message = "Department name is required")
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "manager_id")
    private BigDecimal managerId;
    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "employee_id",insertable = false,updatable = false)
    private Employee employee;
    @OneToMany(mappedBy = "department")
    @JsonManagedReference
    private List<Employee> employees;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
}

