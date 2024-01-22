package com.xtremealex.aeroport.service.provvisorioBatchInit;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xtremealex.aeroport.common.models.json.AirportJson;
import com.xtremealex.aeroport.common.models.json.CountryJson;
import com.xtremealex.aeroport.entity.AirportEntity;
import com.xtremealex.aeroport.entity.typological.AirportTypeTypology;
import com.xtremealex.aeroport.entity.typological.CountryTypology;
import com.xtremealex.aeroport.mapper.IAirportMapper;
import com.xtremealex.aeroport.mapper.ICountryTypologyMapper;
import com.xtremealex.aeroport.repository.AirportRepository;
import com.xtremealex.aeroport.repository.AirportTypeRepository;
import com.xtremealex.aeroport.repository.CountryTypeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
public class AirportDataService {


    @Autowired
    private AirportRepository airportRepository;

    @Autowired

    private AirportTypeRepository airportTypeRepository;

    @Autowired
    private CountryTypeRepository countryTypeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CountryService countryService;


    @PostConstruct
    public void init() throws IOException {
        //loadJsonData();
    }


    //TODO: Questa logica è da spostare su un BATCH -> Futura implementazione Spring Batch
    public void loadJsonData() throws IOException {

        InputStream inputStreamCountry = getClass().getResourceAsStream("/dataset/country/countries-flag.json");
        assert inputStreamCountry != null;
        Reader readerCountry = new InputStreamReader(inputStreamCountry, StandardCharsets.UTF_8);
        List<CountryJson> countriesJson = objectMapper.readValue(readerCountry, new TypeReference<>() {
        });

        InputStream inputStreamAirport = getClass().getResourceAsStream("/dataset/airports/world-airport.json");
        assert inputStreamAirport != null;
        Reader readerAirport = new InputStreamReader(inputStreamAirport, StandardCharsets.UTF_8);
        List<AirportJson> airportsJson = objectMapper.readValue(readerAirport, new TypeReference<>() {
        });

        // Non voglio lavorare direttamente sulle Entity in quanto queste magari li do al FE/REST se cambia la futura stuttura del JSON in input sono costretto a modificare gran parte del codice, preferisco lascialro modulare
        // Con Modelmapper le rimappo da OBJJSON -> ENTITY
        List<CountryTypology> countryEntityList =  ICountryTypologyMapper.INSTANCE.countryJsonListToEntityList(countriesJson);

        List<AirportEntity> airportEntityList = IAirportMapper.INSTANCE.countryJsonListToEntityList(airportsJson);

        // Eseguo un mapping a mano su Airport
        airportEntityList.forEach(aeroporto -> {

            // Correggi l'encoding per nome e municipalità in quanto si v male con l'UTF8
            aeroporto.setName(fixEncoding(aeroporto.getName()));
            aeroporto.setMunicipality(fixEncoding(aeroporto.getMunicipality()));

            // Associo il tipo di aeroporto, la tabella tipologica deve essere già mappata
            //Optional<AirportTypeTypology> airportTypeOpt = airportTypeRepository.findByName(airport.getType());
            //airportTypeOpt.ifPresent(airport::setAirportType);

            //TODO: c'e un problema con i tipi devo risolverlo
            //if(aeroporto.getType() == null && aeroporto.getType().isEmpty()){
            //    aeroporto.setType("not_set");
            //}

            // Cerca il tipo di aeroporto esistente o creane uno nuovo
            AirportTypeTypology airportType = airportTypeRepository.findByName(aeroporto.getType())
                    .orElseGet(() -> {
                        // Salva il nuovo tipo di aeroporto nel db, visto che non esiste
                        AirportTypeTypology newAirportType = new AirportTypeTypology();
                        newAirportType.setName(aeroporto.getType());
                        return airportTypeRepository.save(newAirportType);
                    });

            // Associa il valore inserito all'aeroporto
            aeroporto.setAirportType(airportType);
        });



        // Salva direttamente la lista.
        countryTypeRepository.saveAll(countryEntityList);
        airportRepository.saveAll(airportEntityList);
    }

    public void initCountryData() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/dataset/country/countries-flag.json");
        assert inputStream != null;
        Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        CountryTypology[] countries = objectMapper.readValue(reader, CountryTypology[].class);

        countryService.saveAll(Arrays.stream(countries).toList());

    }

    public String fixEncoding(String value) {
        if (value == null) {
            return null;
        }
        try {
            byte[] bytes = value.getBytes("ISO-8859-1");
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (UnsupportedEncodingException e) {
            // Gestisci l'eccezione: log, gestisci l'errore o rilancia come un'eccezione a runtime
            e.printStackTrace();
            return value;
        }
    }
}
