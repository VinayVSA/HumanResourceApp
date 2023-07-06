package com.example.hra.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


import java.math.BigDecimal;

@Entity
@Table(name="regions")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Region {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "region_id")
    private BigDecimal regionId;

    @Column(name = "region_name")
    private String regionName;

}

