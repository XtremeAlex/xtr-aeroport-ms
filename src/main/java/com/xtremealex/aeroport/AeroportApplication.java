package com.xtremealex.aeroport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AeroportApplication {

    public static void main(String[] args) {
        SpringApplication.run(AeroportApplication.class, args);
    }

}


