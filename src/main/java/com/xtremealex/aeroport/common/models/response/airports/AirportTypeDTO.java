package com.xtremealex.aeroport.common.models.response.airports;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AirportTypeDTO implements Serializable {

    private static final long serialVersionUID = -3563351698643713095L;

    private String id;

    private String name;

    private String description;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private String createdBy;

    private String lastModifiedBy;

}
