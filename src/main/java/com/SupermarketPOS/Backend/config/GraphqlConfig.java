package com.SupermarketPOS.Backend.config;

import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GraphqlConfig {
    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder ->
                wiringBuilder.scalar(ExtendedScalars.LocalTime);
    }

    // CORS configuration
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/graphql")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");

                registry.addMapping("/authenticate")
                        .allowedOrigins("*")
                        .allowedMethods("POST")
                        .allowedHeaders("*");
            }
        };
    }
}