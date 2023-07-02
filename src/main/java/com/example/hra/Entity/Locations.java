package com.example.hra.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="LOCATION")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Locations {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;
    @Column
    private String streetAddress;
    @Column
    private String postalCode;
    @Column
    private String city;
    @Column
    private String state;
    @ManyToOne
    private Countries country;

}
