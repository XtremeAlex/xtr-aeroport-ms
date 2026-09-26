package com.xtremealex.aeroport.common.models.json;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirportJson {

    private String continent;

    private String coordinates;

    @JsonProperty("elevation_ft")
    private String elevationFt;

    @JsonProperty("gps_code")
    private String gpsCode;

    @JsonProperty("iata_code")
    private String iataCode;

    private String ident;

    @JsonProperty("iso_country")
    private String isoCountry;

    @JsonProperty("iso_region")
    private String isoRegion;

    private String municipality;

    private String name;

    private String type;
}
