package com.example.hra.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="COUNTRY")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Countries {

    @Id
    private String countryId;
    @Column
    private String countryName;
    @ManyToOne
    private Regions region;

}
