package com.xtremealex.aeroport.common.models.response.airports;



import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
public class AirportDTO  implements Serializable{


    private static final long serialVersionUID = 6291265644022065469L;

    private String id;

    private String continent;

    //latitude,longitude
    private String coordinates;

    private String elevationFt;

    private String gpsCode;

    private String iataCode;

    private String ident;

    private String isoCountry;

    private String isoRegion;

    private String municipality;

    private String name;

    private AirportTypeDTO airportType;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private String createdBy;

    private String lastModifiedBy;

    public AirportDTO() {
    }
}
