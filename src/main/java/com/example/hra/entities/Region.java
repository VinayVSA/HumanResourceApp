package com.example.hra.entities;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Objects;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return regionId.equals(region.regionId) &&
                regionName.equals(region.regionName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(regionId, regionName);
    }
}