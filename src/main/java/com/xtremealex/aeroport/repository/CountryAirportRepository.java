package com.xtremealex.aeroport.repository;


import com.xtremealex.aeroport.entity.typological.CountryTypology;
import com.xtremealex.aeroport.entity.view.CountryAirportCountView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryAirportRepository extends JpaRepository<CountryAirportCountView, Long> {

    Optional<CountryTypology> findByName(String name);

    Optional<CountryTypology> findByIsoCountry(String iso);

    Page<CountryAirportCountView> findAll(Pageable pageable);

    Optional<CountryAirportCountView> findById(Long id);

}

