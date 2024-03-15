package com.zemoso.checkr.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Checkr API design")
                        .description("An application that will help with candidate background verification"))
                .externalDocs(new ExternalDocumentation()
                        .description("Database Design")
                        .url("https://app.sqldbm.com/SQLServer/DatabaseExplorer/p291130/#"));
    }


}