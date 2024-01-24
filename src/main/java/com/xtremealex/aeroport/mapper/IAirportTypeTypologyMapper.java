package com.xtremealex.aeroport.mapper;

import com.xtremealex.aeroport.common.models.json.AirportJson;
import com.xtremealex.aeroport.common.models.response.airports.AirportTypeDTO;
import com.xtremealex.aeroport.entity.typological.AirportTypeTypology;
import com.xtremealex.aeroport.utility.IStringConverter;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring" , uses = IStringConverter.class)
public interface IAirportTypeTypologyMapper {

    //IAirportTypeTypologyMapper INSTANCE = Mappers.getMapper(IAirportTypeTypologyMapper.class);

    List<AirportTypeTypology> countryJsonListToEntityList(List<AirportJson> airportJsonList);

    default Page<AirportTypeDTO> entityPageToDtoPage(Page<AirportTypeTypology> entityPage, Pageable pageable) {
        List<AirportTypeDTO> dtoList = entityPage.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    AirportTypeDTO entityToDto(AirportTypeTypology entity);

    AirportTypeTypology dtoToEntity(AirportTypeDTO dto);

}
