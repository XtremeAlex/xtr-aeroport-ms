package com.xtremealex.aeroport.service;

import com.xtremealex.aeroport.service.impl.AirportService;
import com.xtremealex.aeroport.utility.StringConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test unitari della logica di conversione degli id tipologia in {@link AirportService}.
 * <p>
 * Gli id tipologia in ingresso sono identificatori opachi codificati in Base64
 * (es. "MQ==" rappresenta 1): il converter li decodifica e li interpreta come Long.
 * Il test non avvia il contesto Spring ne il database.
 */
class AirportServiceTest {

    private AirportService airportService;

    @BeforeEach
    void setUp() {
        airportService = new AirportService();
        ReflectionTestUtils.setField(airportService, "stringConverter", new StringConverter());
    }

    @Test
    @DisplayName("Converte id opachi Base64 nei corrispondenti Long")
    void convertsBase64IdsToLong() {
        // "MQ==" -> "1" -> 1L ; "Mg==" -> 2L ; "Mw==" -> 3L
        Set<String> input = Set.of("MQ==", "Mg==", "Mw==");

        Set<Long> result = airportService.convertiSetStringInLong(input);

        assertEquals(Set.of(1L, 2L, 3L), result);
    }

    @Test
    @DisplayName("Rimuove gli spazi attorno all'id prima di decodificarlo")
    void trimsWhitespaceAroundId() {
        Set<Long> result = airportService.convertiSetStringInLong(Set.of("  Nw==  "));

        assertEquals(Set.of(7L), result);
    }

    @Test
    @DisplayName("Un input null produce un insieme vuoto, non un errore")
    void nullInputReturnsEmptySet() {
        Set<Long> result = airportService.convertiSetStringInLong(null);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Un id non valido solleva IllegalArgumentException")
    void invalidIdThrows() {
        // "YWJj" -> "abc": decodifica ok ma non e un numero -> errore
        Set<String> input = Set.of("YWJj");

        assertThrows(IllegalArgumentException.class,
                () -> airportService.convertiSetStringInLong(input));
    }
}
