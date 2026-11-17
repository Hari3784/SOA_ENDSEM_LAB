package com.example.tourist.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI touristApi() {
        return new OpenAPI()
            .info(new Info().title("Tourist Records API")
                .description("Fetch all tourist records using bearer-token validation.")
                .version("1.0"))
            .components(new Components().addSecuritySchemes("bearerAuth",
                new SecurityScheme().type(SecurityScheme.Type.HTTP)
                    .scheme("bearer").bearerFormat("Token")));
    }
}
