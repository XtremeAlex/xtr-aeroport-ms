package com.xtremealex.aeroport.service.provvisorioBatchInit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xtremealex.aeroport.entity.typological.CountryTypology;
import com.xtremealex.aeroport.repository.CountryTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryTypeRepository countryRepository;

    private final ObjectMapper objectMapper;


    @Autowired
    public CountryService(CountryTypeRepository countryRepository,
                          ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;
        this.countryRepository = countryRepository;
    }

    //@PostConstruct
    public void initAirportData() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/dataset/country/countries-flag.json");
        assert inputStream != null;
        Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        CountryTypology[] countries = objectMapper.readValue(reader, CountryTypology[].class);


        countryRepository.saveAll(Arrays.stream(countries).toList());

    }

    public CountryTypology findCountryByIso(String iso) {
        return countryRepository.findByIsoCountry(iso)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with ISO: " + iso));
    }

    public Optional<CountryTypology> findCountryByIsoOptional(String iso) {
        return countryRepository.findByIsoCountry(iso);
    }

    public void saveAll(List<CountryTypology> countryEntityList) {
        countryRepository.saveAll(countryEntityList);
    }


    public Page<CountryTypology> findAll(PageRequest pageRequest) {
        return countryRepository.findAll(pageRequest);
    }

    public Optional<CountryTypology> findById(Long id) {
        return countryRepository.findById(id);
    }

}