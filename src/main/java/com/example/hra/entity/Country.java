package com.example.hra.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


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
    @GenericGenerator(name = "uuid", strategy = "com.example.hra.entity.CountryIdGenerator")
    @Column(name = "country_id", length = 4,columnDefinition = "char")
    private String countryId;

    @Column(name = "country_name")
    private String countryName;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "region_id")
    private Region region;

}
