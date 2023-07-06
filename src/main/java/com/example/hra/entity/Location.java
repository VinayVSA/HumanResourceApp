package com.example.hra.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "locations")

public class Location {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id")
    private BigDecimal locationId;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "city")
    private String city;

    @Column(name = "state_province")
    private String stateProvince;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "country_id")
    private Country country;


}

