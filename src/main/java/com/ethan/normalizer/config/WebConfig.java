package com.ethan.normalizer.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")              // allow CORS on all paths
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
                registry.addMapping("/api/normalize")
                .allowedOrigins("*")
                .allowedMethods("GET", "OPTIONS");// cache preflight response for 1 hour
    }
}
