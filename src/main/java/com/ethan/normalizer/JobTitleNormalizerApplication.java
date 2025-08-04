package com.ethan.normalizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Job Title Normalizer Spring Boot application.
 * <p>
 * Bootstraps the entire Spring context, scanning for @Components,
 * @Services, @Controllers, etc., and starts the embedded server.
 * </p>
 *
 * @author 
 */
@SpringBootApplication
public class JobTitleNormalizerApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobTitleNormalizerApplication.class, args);
    }
}