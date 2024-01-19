package com.xtremealex.aeroport.common.models.response.countries;


import com.xtremealex.aeroport.common.models.response.airports.AirportDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO implements Serializable {


    private static final long serialVersionUID = -4967230195811208915L;

    private Long id;

    private String isoCountry;

    private String name;

    private String description;

    private List<AirportDTO> airports;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private String createdBy;

    private String lastModifiedBy;

}
