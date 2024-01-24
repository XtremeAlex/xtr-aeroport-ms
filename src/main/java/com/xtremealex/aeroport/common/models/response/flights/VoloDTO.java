package com.xtremealex.aeroport.common.models.response.flights;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VoloDTO implements Serializable {

    private static final long serialVersionUID = -2038745117339620752L;

    public Long id;
    public String numeroVolo;
    public String destinazione;
    public String orarioPartenza;
    public boolean isInArrivo;
    public String tipoAereo;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private String createdBy;

    private String lastModifiedBy;


}

