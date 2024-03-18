package com.xtremealex.aeroport.configuration;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class MySwaggerConfig {

    @Value("${springdoc.openapi.dev-url}")
    private String devUrl;

    @Value("${springdoc.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in DEV");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in PROD");

        Contact contact = new Contact();
        contact.setEmail("alexdabi92@gmail.com");
        contact.setName("Andrei Alexandru Dabija");
        contact.setUrl("https://bubume.it");

        License mitLicense = new License().name("Apache 2.0 License").url("http://www.apache.org/licenses/");

        Info info = new Info()
                .title("API Aeroport")
                .version("1.0")
                .contact(contact)
                .description("API Airports").termsOfService("https://bubume.it")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}