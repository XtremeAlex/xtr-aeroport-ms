package com.xtremealex.aeroport.service.impl;

import com.xtremealex.aeroport.common.models.response.airports.AirportTypeDTO;
import com.xtremealex.aeroport.entity.typological.AirportTypeTypology;
import com.xtremealex.aeroport.repository.AirportTypeRepository;
import com.xtremealex.aeroport.service.IAirportTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AirportTypeService implements IAirportTypeService {

    @Autowired
    private AirportTypeRepository airportTypeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<AirportTypeDTO> getAll(Pageable pageable) {

        Page<AirportTypeTypology> page = airportTypeRepository.findAll(pageable);

        //Type listType = new TypeToken<List<AirportTypeDTO>>() {}.getType();
        //return new PageImpl<>(modelMapper.map(page.getContent(), listType), page.getPageable(), page.getTotalElements());

        return page.map(entity -> modelMapper.map(entity, AirportTypeDTO.class));
    }
}
