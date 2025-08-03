package com.ethan.normalizer.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Job Title Normalizer API")
                        .version("1.0")
                        .description("Normalize job titles via REST"))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Repo")
                        .url("https://github.com/your/repo"));
    }
}
