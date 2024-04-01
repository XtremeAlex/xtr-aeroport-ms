package com.xtremealex.aeroport.configuration;


import com.xtremealex.aeroport.models.web.response.ResponseWrapperBuilder;
import feign.Capability;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    @Bean
    public <T> ResponseWrapperBuilder<T> responseWrapperBuilder() {
        return new ResponseWrapperBuilder<>();
    }
}
