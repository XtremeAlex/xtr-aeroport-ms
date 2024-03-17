package com.xtremealex.aeroport.service;

import com.xtremealex.aeroport.models.web.response.airports.AirportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface IAirportService {

    Page<AirportDTO> getAllByLikeName(String name, Pageable pageable);

    Page<AirportDTO> getAllByAirportTypeIdIn(Set<String> airportType, Pageable pageable);

    Page<AirportDTO> getAllByIsoCountry(String isoCountry, Pageable pageable);


    long getTotalElementsAllByNameContainingIgnoreCaseAndAirportTypeIdIn(String name, Set<String> airportTypeIds);

    long getTotalElementsAllByIsoCountryAndNameContainingIgnoreCase(String isoCountry, String name);

    long getTotalElementsAllByIsoCountryAndNameContainingIgnoreCaseAndAirportTypeIdIn(String isoCountry, String name, Set<String> airportTypeIds);


    Page<AirportDTO> getAllByIsoCountryAndAirportTypeIdIn(String isoCountry, Set<String> airportType, Pageable pageable);

    Page<AirportDTO> getAllByLikeNameAndAirportTypeIdIn(String name, Set<String> airportTypeIds, Pageable pageable);

    Page<AirportDTO> getAllByIsoCountryAndLikeName(String isoCountry, String name, Pageable pageable);

    Page<AirportDTO> getAllByIsoCountryAndLikeNameAndAirportTypeIdIn(String isoCountry, String name, Set<String> airportTypeIds, Pageable pageable);

}
