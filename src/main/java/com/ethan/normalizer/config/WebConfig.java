package com.ethan.normalizer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 1) Everything in your app (catch-all)
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);

        // 2) Explicitly cover your normalize API
        registry.addMapping("/api/normalize")
                .allowedOrigins("*")
                .allowedMethods("GET", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);

        // 3) Springdoc endpoints
        registry.addMapping("/v3/api-docs/**")
                .allowedOrigins("*")
                .allowedMethods("GET","OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);

        registry.addMapping("/swagger-ui.html")
                .allowedOrigins("*")
                .allowedMethods("GET","OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);

        // 4) Static assets for Swagger UI
        registry.addMapping("/webjars/**")
                .allowedOrigins("*")
                .allowedMethods("GET","OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
