package com.ethan.normalizer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Global web configuration.
 * <p>
 * Configures CORS to allow cross-origin access for all endpoints.
 * </p>
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Allow all origins for GET/POST/OPTIONS on all paths.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);

        registry.addMapping("/api/normalize")
                .allowedOrigins("*")
                .allowedMethods("GET", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);

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

        registry.addMapping("/webjars/**")
                .allowedOrigins("*")
                .allowedMethods("GET","OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
