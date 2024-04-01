package com.xtremealex.aeroport.controller;

import com.xtremealex.aeroport.feign.SearchAirportTypeFeignClient;
import com.xtremealex.aeroport.models.web.response.ResponseWrapper;
import com.xtremealex.aeroport.models.web.response.ResponseWrapperBuilder;
import com.xtremealex.aeroport.models.web.response.airports.AirportTypeDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Tag(name = "Search Airport Type", description = "Tipologie di aeroporti")
public class SearchAirportType {

    // Questa è una forzatura per fini di test
    @Autowired
    private SearchAirportTypeFeignClient searchAirportTypeFeignClient;

    @Autowired
    private ResponseWrapperBuilder responseWrapperBuilder;

    @GetMapping("/getAllAirportTypes")
    public ResponseEntity<ResponseWrapper<Page<AirportTypeDTO>>> getAirportsZero() {
        return searchAirportTypeFeignClient.getAirportsZero();
    }

    @GetMapping("/getAllAirportType/{pageNumber}/{pageSize}/{sortField}/{sortDir}")
    public ResponseEntity<ResponseWrapper<Page<AirportTypeDTO>>> getAirportsByPathVariable(@PathVariable Integer pageNumber,
                                         @PathVariable Integer pageSize,
                                         @PathVariable String sortField,
                                         @PathVariable String sortDir) {

        return searchAirportTypeFeignClient.getAirportsByPathVariable(pageNumber,pageSize,sortField,sortDir);
    }

    @GetMapping("/getAllAirportType")
    public ResponseEntity<ResponseWrapper<Page<AirportTypeDTO>>> getAirportsByReqyestParam(@RequestParam(defaultValue = "0") Integer pageNumber,
                                          @RequestParam(defaultValue = "12") Integer pageSize,
                                          @RequestParam(required = false) String sortField,
                                          @RequestParam(defaultValue = "ASC") String sortDir) {

        return searchAirportTypeFeignClient.getAirportsByReqyestParam(pageNumber,pageSize,sortField,sortDir);
    }
}
