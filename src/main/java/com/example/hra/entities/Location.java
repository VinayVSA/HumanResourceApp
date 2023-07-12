package com.example.hra.entities;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Objects;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "locations")
public class Location {
    @Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "location_id")
    private BigDecimal locationId;
    @NotBlank(message = "Street address name is required")
    @Column(name = "street_address")
    private String streetAddress;
    @NotBlank(message = "Postal code is required")
    @Column(name = "postal_code")
    private String postalCode;
    @Pattern(regexp = "^[a-zA-Z[\\s]*[a-zA-Z]]+$", message = "City name should contain only alphabets")
    @NotBlank(message = "City name is required")
    @Column(name = "city")
    private String city;
    @Pattern(regexp = "^[a-zA-Z[\\s]*[a-zA-Z]]+$", message = "State name should contain only alphabets")
    @NotBlank(message = "State name is required")
    @Column(name = "state_province")
    private String stateProvince;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "country_id")
    private Country country;
    @Override
    public int hashCode() {
        return Objects.hash(locationId, streetAddress, postalCode, city, stateProvince, country);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Location other = (Location) obj;
        return Objects.equals(locationId, other.locationId)
                && Objects.equals(streetAddress, other.streetAddress)
                && Objects.equals(postalCode, other.postalCode)
                && Objects.equals(city, other.city)
                && Objects.equals(stateProvince, other.stateProvince)
                && Objects.equals(country, other.country);
    }
}