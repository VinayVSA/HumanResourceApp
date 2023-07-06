package com.example.hra.entity;

import lombok.*;

import javax.persistence.*;


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

    @Column(name = "region_name")
    private String regionName;

}

