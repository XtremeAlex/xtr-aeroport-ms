package com.xtremealex.aeroport.configuration;


import com.xtremealex.aeroport.common.models.response.ResponseWrapperBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public <T> ResponseWrapperBuilder<T> responseWrapperBuilder() {
        return new ResponseWrapperBuilder<>();
    }

}
