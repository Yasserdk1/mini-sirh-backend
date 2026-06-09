package com.example.mini_sirh.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI miniSirhOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mini-SIRH API")
                        .description("Documentation des APIs du projet Mini-SIRH : gestion RH, formations, congés, pointage IoT, dashboard et authentification.")
                        .version("1.0.0"));
    }
}