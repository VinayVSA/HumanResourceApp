package com.example.hra.entities;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
@Entity
@Table(name="regions")
@Setter
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Region {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "region_id")
    private BigDecimal regionId;
    @Pattern(regexp = "^[a-zA-Z[\\s]*[a-zA-Z]]+$", message = "Region name should contain only alphabets")
    @NotBlank(message = "Region name is required")
    @Column(name = "region_name")
    private String regionName;
}