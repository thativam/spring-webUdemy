package com.brainyit.rest.apirest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenApiConfig(){
        return new OpenAPI().info(new Info().title("Rest api do 0 com udemy.")
        .version("v1")
        .description("Api de teste para aprendizado.")
        .termsOfService("")
        .license(new License()));
    }
    
}
