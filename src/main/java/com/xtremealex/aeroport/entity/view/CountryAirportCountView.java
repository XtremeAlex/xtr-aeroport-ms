package com.xtremealex.aeroport.entity.view;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "V_D_COUNTRY_AIRPORT_COUNT")
public class CountryAirportCountView implements Serializable {


    private static final long serialVersionUID = 8294502615278526177L;

    @Id
    private String isoCountry;

    private String name;

    private Integer airportCount;
}
