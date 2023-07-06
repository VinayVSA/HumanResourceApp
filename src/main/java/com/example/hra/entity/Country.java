package com.example.hra.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "com.example.hra.entity.RandomStringGenerator")
    @Column(name = "country_id", length = 4,columnDefinition = "char")
    private String countryId;

    @Column(name = "country_name")
    private String countryName;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "region_id")
    private Region region;

}

