package com.xtremealex.aeroport.configuration;


import com.xtremealex.aeroport.common.models.response.ResponseWrapperBuilder;
import com.xtremealex.aeroport.mapper.IdDecodeConverter;
import com.xtremealex.aeroport.mapper.IdEncodeConverter;
import com.xtremealex.aeroport.mapper.ListIdEncodeConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        //Moduli di Conversione,
        //Non mi piace mai lasciare in chiaro gli ID, percio ho inserito un metodo "sempreMelgioDiNulla" di conversione dell id nel dto
        modelMapper.addConverter(new IdEncodeConverter());
        modelMapper.addConverter(new IdDecodeConverter());
        modelMapper.addConverter(new ListIdEncodeConverter());
        //modelMapper.addConverter(new CoordinatesConverter());

        return modelMapper;
    }

    @Bean
    public <T> ResponseWrapperBuilder<T> responseWrapperBuilder() {
        return new ResponseWrapperBuilder<>();
    }

}
