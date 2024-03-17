package com.xtremealex.aeroport.service;

import com.xtremealex.aeroport.models.web.response.airports.AirportTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAirportTypeService {

    Page<AirportTypeDTO> getAll(Pageable pageable);

}
