package com.practica1.configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi applicationApi() {
        String[] paths = {"/api/customers/**", "/api/invoices/**", "/api/products/**"};
        return GroupedOpenApi.builder().group("application")
                .pathsToMatch(paths)
                .addOpenApiCustomizer(customGlobalOpenApiCustomizer())
                .build();

    }

    @Bean
    public OpenApiCustomizer customGlobalOpenApiCustomizer() {
        return openApi -> openApi.getInfo()
                .title("Practice 1 - API")
                .description("This is an api creation practice")
                .version("1.0.0");
    }
}