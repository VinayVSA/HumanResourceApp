package com.example.hra.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="REGION")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Regions {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer regionId;
    @Column
    private String regionName;

}
