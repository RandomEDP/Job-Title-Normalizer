package com.ethan.normalizer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Value("${app.api.base-url:}")
    private String baseUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI api = new OpenAPI();
        if (!baseUrl.isBlank()) {
            api.addServersItem(new Server().url(baseUrl));
        }
        return api;
    }
}
