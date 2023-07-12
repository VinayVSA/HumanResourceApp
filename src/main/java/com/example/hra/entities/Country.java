package com.example.hra.entities;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "com.example.hra.entities.CountryIdGenerator")
    @Column(name = "country_id", length = 2,columnDefinition = "char")
    private String countryId;
    @Pattern(regexp = "^[a-zA-Z[\\s]*[a-zA-Z]]+$", message = "Country name should contain only alphabets")
    @NotBlank(message = "Country name is required")
    @Column(name = "country_name")
    private String countryName;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "region_id")
    private Region region;
    @Override
    public int hashCode() {
        return Objects.hash(countryId, countryName, region);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Country other = (Country) obj;
        return Objects.equals(countryId, other.countryId)
                && Objects.equals(countryName, other.countryName)
                && Objects.equals(region, other.region);
    }
}