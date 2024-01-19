package com.xtremealex.aeroport.common.models.response.airports;


import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
public class AirportTypeDTO implements Serializable{

    private static final long serialVersionUID = -3563351698643713095L;

    private String id;

    private String name;

    private String description;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private String createdBy;

    private String lastModifiedBy;

    public AirportTypeDTO() {
    }
}
