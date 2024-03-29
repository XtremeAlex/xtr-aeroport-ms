package com.xtremealex.aeroport.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MySwaggerConfig {
    @Value("${springdoc.openapi.dev-url}")
    private String devUrl;
    @Value("${springdoc.openapi.prod-url}")
    private String prodUrl;

    @Value("${springdoc.openapi.mail}")
    private String mail;

    @Value("${springdoc.openapi.name}")
    private String name;

    @Value("${springdoc.openapi.url}")
    private String url;
    @Bean
    public OpenAPI gateWayOpenApi() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in DEV");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in PROD");

        Contact contact = new Contact();
        contact.setEmail(mail);
        contact.setName(name);
        contact.setUrl(url);

        License mitLicense = new License().name("Apache 2.0 License").url("http://www.apache.org/licenses/");

        Info info = new Info()
                .title("API Aeroport")
                .version("1.0.0")
                .contact(contact)
                .description("API Airports").termsOfService(url)
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }

}