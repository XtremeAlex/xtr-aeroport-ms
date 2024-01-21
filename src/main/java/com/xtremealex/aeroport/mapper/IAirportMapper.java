package com.xtremealex.aeroport.mapper;

import com.xtremealex.aeroport.common.models.json.AirportJson;
import com.xtremealex.aeroport.common.models.response.airports.AirportDTO;
import com.xtremealex.aeroport.entity.AirportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IAirportMapper {
    IAirportMapper INSTANCE = Mappers.getMapper(IAirportMapper.class);

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

    @Mapping(target = "id", source = "id", qualifiedByName = "longToStringBase64")
    AirportDTO entityToDto(AirportEntity entity);

    @Mapping(target = "id", source = "id", qualifiedByName = "stringBase64ToLong")
    AirportEntity dtoToEntity(AirportDTO dto);

    @Named("longToStringBase64")
    default String convertLongToStringBase64(Long value) {
        return value != null ? Base64.getEncoder().encodeToString(value.toString().getBytes()) : null;
    }

    @Named("stringBase64ToLong")
    default Long convertStringBase64ToLong(String source) {
        if (source == null) {
            return null;
        }
        try {
            return Long.parseLong(new String(Base64.getDecoder().decode(source)));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("L'ID stringa non è un valido.");
        }
    }

}
