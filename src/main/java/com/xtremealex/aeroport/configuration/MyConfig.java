package com.xtremealex.aeroport.configuration;

import com.xtremealex.aeroport.models.web.response.ResponseWrapperBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configurazione dei bean applicativi condivisi.
 */
@Configuration
public class MyConfig {

    /**
     * Builder generico per le risposte API uniformi ({@link ResponseWrapperBuilder}).
     *
     * @param <T> tipo del payload incapsulato nella risposta
     * @return una nuova istanza del builder
     */
    @Bean
    public <T> ResponseWrapperBuilder<T> responseWrapperBuilder() {
        return new ResponseWrapperBuilder<>();
    }
}
