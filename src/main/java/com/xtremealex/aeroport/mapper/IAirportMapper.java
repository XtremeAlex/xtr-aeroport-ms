package com.xtremealex.aeroport.mapper;

import com.xtremealex.aeroport.common.models.json.AirportJson;
import com.xtremealex.aeroport.common.models.response.airports.AirportDTO;
import com.xtremealex.aeroport.entity.AirportEntity;
import com.xtremealex.aeroport.utility.IStringConverter;
import com.xtremealex.aeroport.utility.StringConverter;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring" , uses = IStringConverter.class)
public interface IAirportMapper {

    //Grazie Luca Rurio :)

    //IAirportMapper INSTANCE = Mappers.getMapper(IAirportMapper.class);

    List<AirportEntity> countryJsonListToEntityList(List<AirportJson> airportJsonList);


    //MapStruct non supporta direttamente la mappatura di tipi parametrizzati come Page<T>
    //Page<AirportDTO> entityPageToDtoPage(Page<AirportEntity> entityPage);

    //Risolviamo cosi:
    default Page<AirportDTO> entityPageToDtoPage(Page<AirportEntity> entityPage, Pageable pageable) {
        List<AirportDTO> dtoList = entityPage.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    AirportDTO entityToDto(AirportEntity entity);

    //@Mapping(target = "id", source = "id", qualifiedByName = "convertStringBase64ToLong")
    AirportEntity dtoToEntity(AirportDTO dto);

}
