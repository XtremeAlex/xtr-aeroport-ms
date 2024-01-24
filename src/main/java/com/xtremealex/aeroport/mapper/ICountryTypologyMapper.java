package com.xtremealex.aeroport.mapper;

import com.xtremealex.aeroport.common.models.json.CountryJson;
import com.xtremealex.aeroport.common.models.response.countries.CountryDTO;
import com.xtremealex.aeroport.entity.typological.CountryTypology;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICountryTypologyMapper {
    //ICountryTypologyMapper INSTANCE = Mappers.getMapper(ICountryTypologyMapper.class);

    List<CountryTypology> countryJsonListToEntityList(List<CountryJson> countryJsonList);

    List<CountryDTO> entityListToDtoList(List<CountryTypology> entityPage);

    CountryDTO entityToDto(CountryTypology entity);

    CountryTypology dtoToEntity(CountryDTO dto);
}
