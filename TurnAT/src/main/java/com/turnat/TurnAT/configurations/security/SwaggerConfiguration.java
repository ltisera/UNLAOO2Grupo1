package com.turnat.TurnAT.configurations.security;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("TurnAT API")
                .version("1.0.0")
                .description("Documentación de la API REST para el sistema TurnAT - Entrega Final")
            );
    }
}