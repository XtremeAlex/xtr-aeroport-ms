package com.xtremealex.aeroport.feign;

import com.xtremealex.aeroport.models.web.response.ResponseWrapper;
import com.xtremealex.aeroport.models.web.response.airports.AirportTypeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "searchAirportTypeFeignClient", url= "${XTR_AEROPORT_TYPOLOGICAL_URL:http://localhost:8083/xtr-aeroport-typological}")
public interface SearchAirportTypeFeignClient {

    @GetMapping("/getAllAirportTypes")
    ResponseEntity<ResponseWrapper<Page<AirportTypeDTO>>> getAirportsZero();

    @GetMapping("/getAllAirportType/{pageNumber}/{pageSize}/{sortField}/{sortDir}")
    ResponseEntity<ResponseWrapper<Page<AirportTypeDTO>>> getAirportsByPathVariable(@PathVariable Integer pageNumber,
                                                                             @PathVariable Integer pageSize,
                                                                             @PathVariable String sortField,
                                                                             @PathVariable String sortDir);

    @GetMapping("/getAllAirportType")
    ResponseEntity<ResponseWrapper<Page<AirportTypeDTO>>> getAirportsByReqyestParam(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                                              @RequestParam(defaultValue = "12") Integer pageSize,
                                                                              @RequestParam(required = false) String sortField,
                                                                              @RequestParam(defaultValue = "ASC") String sortDir);

}
