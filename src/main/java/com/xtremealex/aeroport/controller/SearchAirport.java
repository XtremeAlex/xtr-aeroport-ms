package com.xtremealex.aeroport.controller;

import com.xtremealex.aeroport.models.web.ErrorCode;
import com.xtremealex.aeroport.models.web.request.AirportSearchRequest;
import com.xtremealex.aeroport.models.web.response.ResponseWrapper;
import com.xtremealex.aeroport.models.web.response.ResponseWrapperBuilder;
import com.xtremealex.aeroport.models.web.response.airports.AirportDTO;
import com.xtremealex.aeroport.service.IAirportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Ricerca degli aeroporti con filtri combinabili (tipologie, paese ISO, nome)
 * e paginazione/ordinamento. La dimensione di pagina e limitata lato server per
 * proteggere microservizio e database.
 */
@RestController
@Tag(name = "Search Airport", description = "Cerca il tuo aeroporto preferito")
@Slf4j
public class SearchAirport {

    /** Lunghezza minima del filtro per nome, per evitare ricerche troppo ampie. */
    private static final int MIN_NAME_LENGTH = 3;

    @Autowired
    private IAirportService airportService;

    @Autowired
    private ResponseWrapperBuilder responseWrapperBuilder;

    /** Dimensione massima di pagina consentita (da configurazione). */
    @Value("${xtr-aeroport.db.pagination.maxPageSize}")
    private Integer maxPageSize;

    /**
     * Ricerca via query string. Esempio:
     * {@code /getAirportsBy?types=1,2&isoCountry=IT&pageNumber=0&pageSize=12&sortField=name}
     */
    @GetMapping("/getAirportsBy")
    public ResponseEntity<ResponseWrapper<Page<AirportDTO>>> getAirportsBy(@RequestParam(required = false) Set<String> types,
                                                                           @RequestParam(required = false) String isoCountry,
                                                                           @RequestParam(required = false) String name,
                                                                           @RequestParam(defaultValue = "0") int pageNumber,
                                                                           @RequestParam(defaultValue = "12") int pageSize,
                                                                           @RequestParam(defaultValue = "name", required = false) String sortField,
                                                                           @RequestParam(defaultValue = "ASC") String sortDir) {

        AirportSearchRequest filtriInput = new AirportSearchRequest(types, isoCountry, name, pageNumber, pageSize, sortField, sortDir);
        return genericSearchAirports(filtriInput);
    }

    /**
     * Ricerca via corpo JSON, equivalente a {@link #getAirportsBy}.
     */
    @PostMapping("/searchAirports")
    public ResponseEntity<ResponseWrapper<Page<AirportDTO>>> searchAirports(@RequestBody AirportSearchRequest searchRequest) {
        return genericSearchAirports(searchRequest);
    }

    /**
     * Flusso di ricerca comune: normalizza la dimensione di pagina, valida il
     * filtro per nome, costruisce la paginazione ed esegue la query.
     */
    public ResponseEntity genericSearchAirports(AirportSearchRequest searchRequest) {
        try {
            // Limita la dimensione di pagina al massimo consentito, a protezione di MS e DB.
            searchRequest.setPageSize(Math.min(searchRequest.getPageSize(), maxPageSize));

            if (searchRequest.getName() != null && searchRequest.getName().length() < MIN_NAME_LENGTH) {
                throw new IllegalArgumentException(
                        "La ricerca per nome richiede almeno " + MIN_NAME_LENGTH + " caratteri.");
            }

            Pageable pageable = creaPaginazione(searchRequest.getPageNumber(), searchRequest.getPageSize(),
                    searchRequest.getSortField(), searchRequest.getSortDir());

            Page<AirportDTO> airports = search(searchRequest.getTypes(), searchRequest.getIsoCountry(),
                    searchRequest.getName(), pageable);

            if (airports == null || airports.isEmpty()) {
                log.debug("Nessun aeroporto trovato per i filtri: {}", searchRequest);
                return new ResponseEntity(responseWrapperBuilder.buildResponse(ErrorCode.E1, searchRequest, "Nessun aeroporto trovato"), null, HttpStatus.NOT_FOUND);
            }

            return returnResults(airports, searchRequest);

        } catch (Exception e) {
            log.error("Errore durante la ricerca aeroporti per i filtri {}: {}", searchRequest, e.getMessage(), e);
            return returnError(e, searchRequest);
        }
    }

    /**
     * Seleziona la query di ricerca in base alla combinazione di filtri valorizzati.
     * Ogni ramo copre un caso d'uso distinto (nome, tipologie, paese e loro combinazioni).
     */
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

    /**
     * Costruisce l'oggetto di paginazione, applicando l'ordinamento se richiesto.
     */
    private Pageable creaPaginazione(int pageNumber, int pageSize, String sortField, String sortDir) {
        if (sortField != null && !sortField.isEmpty()) {
            Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
            return PageRequest.of(pageNumber, pageSize, sort);
        }
        return PageRequest.of(pageNumber, pageSize);
    }

    /**
     * Costruisce la risposta di errore (HTTP 500) con codice {@link ErrorCode#E0}.
     */
    private ResponseEntity returnError(Exception e, Object searchParams) {
        return new ResponseEntity<>(responseWrapperBuilder.buildResponse(ErrorCode.E0, searchParams, e.getMessage()), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Costruisce la risposta di successo (HTTP 200) con codice {@link ErrorCode#E1}.
     */
    private ResponseEntity returnResults(Page<AirportDTO> airports, AirportSearchRequest searchParams) {
        if (airports == null || airports.isEmpty()) {
            return new ResponseEntity(responseWrapperBuilder.buildResponse(ErrorCode.E1, searchParams, "Nessun aeroporto trovato"), null, HttpStatus.OK);
        }
        return new ResponseEntity(responseWrapperBuilder.buildResponse(ErrorCode.E1, searchParams, airports), null, HttpStatus.OK);
    }

}
