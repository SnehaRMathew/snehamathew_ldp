package com.zemoso.checkr.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class SwaggerConfig {
    private  static Logger LOGGER = Logger.getLogger(SwaggerConfig.class.getName());
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