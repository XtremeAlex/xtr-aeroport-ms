package com.xtremealex.aeroport.controller;

import com.xtremealex.aeroport.common.models.ErrorCode;
import com.xtremealex.aeroport.common.models.request.AirportSearchRequest;
import com.xtremealex.aeroport.common.models.response.ResponseWrapper;
import com.xtremealex.aeroport.common.models.response.ResponseWrapperBuilder;
import com.xtremealex.aeroport.common.models.response.airports.AirportDTO;
import com.xtremealex.aeroport.service.IAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class SearchAirport {

    @Autowired
    private IAirportService airportService;

    @Autowired
    private ResponseWrapperBuilder responseWrapperBuilder;


    @Value("${xtr-aeroport.db.pagination.maxPageSize}")
    private Integer MAX_PAGE_SIZE;


    //http://localhost:8080/xtr-aeroport/getAirport?types=1,2,3,4,5,6,7,8&isoCountry=IT&pageNumber=4&pageSize=1000&sortField=name
    @GetMapping("/getAirportsBy")
    public ResponseEntity<ResponseWrapper<Page<AirportDTO>>> getAirportsBy(@RequestParam(required = false) Set<String> types,
                                                                           @RequestParam(required = false) String isoCountry,
                                                                           @RequestParam(required = false) String name,
                                                                           @RequestParam(defaultValue = "0") int pageNumber,
                                                                           @RequestParam(defaultValue = "12") int pageSize,
                                                                           @RequestParam(required = false) String sortField,
                                                                           @RequestParam(defaultValue = "ASC") String sortDir) {

        // Questa è una chicca, serve per avere a video un evvidenza dei filtri in entrata usati per la ricerca, in piu lo uso per snellire il codices
        AirportSearchRequest filtriInput = new AirportSearchRequest(types, isoCountry, name, pageNumber, pageSize, sortField, sortDir);

        return genericSearchAirports(filtriInput);
    }

    //TODO: Da testare non testato :(
    @PostMapping("/searchAirports")
    public ResponseEntity<ResponseWrapper<Page<AirportDTO>>> searchAirports(@RequestBody AirportSearchRequest searchRequest) {
        return genericSearchAirports(searchRequest);
    }

    public ResponseEntity genericSearchAirports(AirportSearchRequest searchRequest) {
        try {

            //Se sei furbo ti sego le gambe cosi ;)
            searchRequest.setPageSize(Math.min(searchRequest.getPageSize(), MAX_PAGE_SIZE));

            //TODO: Controllo superfluo
            //searchRequest.setPageNumber(searchRequest.getName() != null && !searchRequest.getName().isBlank() && searchRequest.getPageNumber() == 1 ? 0 : searchRequest.getPageNumber());

            if (searchRequest.getName() != null && searchRequest.getName().length() < 3) {
                throw new IllegalArgumentException("La ricerca richiede almeno 3 caratteri nella ricerca del nome. ");
            }

            // Inserisco un max size prima che qualcuno tiri giù il ms oppure il db,
            // se cerchi 9999 perche si furbo ti metto 48 come indicato sulle props ;)
            searchRequest.setPageSize(Math.min(searchRequest.getPageSize(), MAX_PAGE_SIZE));

            Pageable pageable = creaPaginazione(searchRequest.getPageNumber(), searchRequest.getPageSize(), searchRequest.getSortField(), searchRequest.getSortDir());

            Page<AirportDTO> airports = search(searchRequest.getTypes(), searchRequest.getIsoCountry(), searchRequest.getName(), pageable);
            if (airports == null || airports.isEmpty()) {
                return new ResponseEntity(responseWrapperBuilder.buildResponse(ErrorCode.E1, searchRequest, "Nessun aeroporto trovato"), null, HttpStatus.NOT_FOUND);
            }

            return returnResults(airports, searchRequest);

        } catch (Exception e) {
            return returnError(e, searchRequest);
        }
    }

    //Ottimizzazione del codice, altrimenti Sonar sta a sirene spiegate,
    //Per far vedere tutti i casi d'uso ho estremizzato l'esempio
    private Page<AirportDTO> search(Set<String> types, String isoCountry, String name, Pageable pageable) {
        if (name != null && !name.isBlank()) {
            if (types != null && isoCountry != null) {
                return airportService.getAllByIsoCountryAndLikeNameAndAirportTypeIdIn(isoCountry, name, types, pageable);
            } else if (types != null) {
                return airportService.getAllByLikeNameAndAirportTypeIdIn(name, types, pageable);
            } else if (isoCountry != null) {
                return airportService.getAllByIsoCountryAndLikeName(isoCountry, name, pageable);
            } else {
                return airportService.getAllByLikeName(name, pageable);
            }
        } else if (types != null && isoCountry != null) {
            return airportService.getAllByIsoCountryAndAirportTypeIdIn(isoCountry, types, pageable);
        } else if (types != null) {
            return airportService.getAllByAirportTypeIdIn(types, pageable);
        } else if (isoCountry != null) {
            return airportService.getAllByIsoCountry(isoCountry, pageable);
        } else {
            throw new IllegalArgumentException("Parametri di ricerca mancanti");
        }
    }

    private Pageable creaPaginazione(int pageNumber, int pageSize, String sortField, String sortDir) {
        Pageable pageable;
        if (sortField != null && !sortField.isEmpty()) {
            Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
            pageable = PageRequest.of(pageNumber, pageSize, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        return pageable;
    }

    private ResponseEntity returnError(Exception e, Object searchParams) {
        return new ResponseEntity<>(responseWrapperBuilder.buildResponse(ErrorCode.E0, searchParams, e.getMessage()), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity returnResults(Page<AirportDTO> airports, AirportSearchRequest searchParams) {
        if (airports == null || airports.isEmpty()) {
            return new ResponseEntity(responseWrapperBuilder.buildResponse(ErrorCode.E1, searchParams, "Nessun aeroporto trovato"), null, HttpStatus.OK);
        }
        return new ResponseEntity(responseWrapperBuilder.buildResponse(ErrorCode.E1, searchParams, airports), null, HttpStatus.OK);
    }

}
