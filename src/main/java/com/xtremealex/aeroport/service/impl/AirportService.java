package com.xtremealex.aeroport.service.impl;

import com.xtremealex.aeroport.common.models.response.airports.AirportDTO;
import com.xtremealex.aeroport.entity.AirportEntity;
import com.xtremealex.aeroport.mapper.IAirportMapper;
import com.xtremealex.aeroport.repository.AirportRepository;
import com.xtremealex.aeroport.service.IAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AirportService implements IAirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public Page<AirportDTO> getAllByLikeName(String name, Pageable pageable) {
        Page<AirportEntity> page = airportRepository.findByNameContainingIgnoreCase(name,pageable);
        return IAirportMapper.INSTANCE.entityPageToDtoPage(page, page.getPageable());
    }

    @Override
    public Page<AirportDTO> getAllByAirportTypeIdIn(Set<String> airportTypeString, Pageable pageable) {
        Page<AirportEntity> page = airportRepository.findAllByAirportTypeIdIn(convertiSetStringInLong(airportTypeString),pageable);
        return IAirportMapper.INSTANCE.entityPageToDtoPage(page, page.getPageable());
    }

    @Override
    public Page<AirportDTO> getAllByIsoCountry(String isoCountry, Pageable pageable) {
        Page<AirportEntity> page = airportRepository.findAllByIsoCountry(isoCountry, pageable);
        return IAirportMapper.INSTANCE.entityPageToDtoPage(page, page.getPageable());
    }

    @Override
    public long getTotalElementsAllByNameContainingIgnoreCaseAndAirportTypeIdIn(String name, Set<String> airportTypeIds) {
        return airportRepository.countAllByNameContainingIgnoreCaseAndAirportTypeIdIn(name,convertiSetStringInLong(airportTypeIds));
    }

    @Override
    public long getTotalElementsAllByIsoCountryAndNameContainingIgnoreCase(String isoCountry, String name) {
        return airportRepository.countAllByIsoCountryAndNameContainingIgnoreCase(isoCountry,name);
    }

    @Override
    public long getTotalElementsAllByIsoCountryAndNameContainingIgnoreCaseAndAirportTypeIdIn(String isoCountry, String name, Set<String> airportTypeIds) {
        return airportRepository.countAllByIsoCountryAndNameContainingIgnoreCaseAndAirportTypeIdIn(isoCountry,name,convertiSetStringInLong(airportTypeIds));
    }

    @Override
    public Page<AirportDTO> getAllByIsoCountryAndAirportTypeIdIn(String isoCountry, Set<String> airportTypeString, Pageable pageable) {
        Page<AirportEntity> page = airportRepository.findAllByIsoCountryAndAirportTypeIdIn(isoCountry, convertiSetStringInLong(airportTypeString), pageable);
        return IAirportMapper.INSTANCE.entityPageToDtoPage(page, page.getPageable());
    }

    @Override
    public Page<AirportDTO> getAllByLikeNameAndAirportTypeIdIn(String name, Set<String> airportTypeIds, Pageable pageable) {
        Page<AirportEntity> page = airportRepository.findAllByNameContainingIgnoreCaseAndAirportTypeIdIn(name,convertiSetStringInLong(airportTypeIds),pageable);
        return IAirportMapper.INSTANCE.entityPageToDtoPage(page, page.getPageable());
    }

    @Override
    public Page<AirportDTO> getAllByIsoCountryAndLikeName(String isoCountry, String name, Pageable pageable) {
        Page<AirportEntity> page = airportRepository.findAllByIsoCountryAndNameContainingIgnoreCase(isoCountry,name,pageable);
        return IAirportMapper.INSTANCE.entityPageToDtoPage(page, page.getPageable());
    }

    @Override
    public Page<AirportDTO> getAllByIsoCountryAndLikeNameAndAirportTypeIdIn(String isoCountry, String name, Set<String> airportTypeIds, Pageable pageable) {
        Page<AirportEntity> page = airportRepository.findAllByIsoCountryAndNameContainingIgnoreCaseAndAirportTypeIdIn(isoCountry,name,convertiSetStringInLong(airportTypeIds),pageable);
        return IAirportMapper.INSTANCE.entityPageToDtoPage(page, page.getPageable());
    }

    public Set<Long> convertiSetStringInLong(Set<String> airportTypeString){

        Set<Long> airportTypeIds = new HashSet<>();
        if (airportTypeString != null) {
            for (String typeStr : airportTypeString) {
                try {
                    long typeId = Long.parseLong(typeStr.trim());
                    airportTypeIds.add(typeId);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Il tipo di aeroporto fornito non è valido: " + typeStr);
                }
            }
        }

        return airportTypeIds;
    }

}
