package com.xtremealex.aeroport.controller;

import com.xtremealex.aeroport.feign.SearchAirportTypeFeignClient;
import com.xtremealex.aeroport.models.web.response.ResponseWrapper;
import com.xtremealex.aeroport.models.web.response.airports.AirportTypeDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Espone le tipologie di aeroporto delegando al servizio "typological"
 * tramite il relativo Feign client. Funge da proxy di sola lettura.
 */
@RestController
@Tag(name = "Search Airport Type", description = "Tipologie di aeroporti")
public class SearchAirportType {

    @Autowired
    private SearchAirportTypeFeignClient searchAirportTypeFeignClient;

    /**
     * Restituisce tutte le tipologie di aeroporto (prima pagina, dimensione di default).
     */
    @GetMapping("/getAllAirportTypes")
    public ResponseEntity<ResponseWrapper<Page<AirportTypeDTO>>> getAirportsZero() {
        return searchAirportTypeFeignClient.getAirportsZero();
    }

    /**
     * Restituisce le tipologie di aeroporto con paginazione e ordinamento via path variable.
     */
    @GetMapping("/getAllAirportType/{pageNumber}/{pageSize}/{sortField}/{sortDir}")
    public ResponseEntity<ResponseWrapper<Page<AirportTypeDTO>>> getAirportsByPathVariable(@PathVariable Integer pageNumber,
                                         @PathVariable Integer pageSize,
                                         @PathVariable String sortField,
                                         @PathVariable String sortDir) {

        return searchAirportTypeFeignClient.getAirportsByPathVariable(pageNumber, pageSize, sortField, sortDir);
    }

    /**
     * Restituisce le tipologie di aeroporto con paginazione e ordinamento via request param.
     */
    @GetMapping("/getAllAirportType")
    public ResponseEntity<ResponseWrapper<Page<AirportTypeDTO>>> getAirportsByReqyestParam(@RequestParam(defaultValue = "0") Integer pageNumber,
                                          @RequestParam(defaultValue = "12") Integer pageSize,
                                          @RequestParam(required = false) String sortField,
                                          @RequestParam(defaultValue = "ASC") String sortDir) {

        return searchAirportTypeFeignClient.getAirportsByReqyestParam(pageNumber, pageSize, sortField, sortDir);
    }
}
