package com.SupermarketPOS.Backend.Config;

import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GraphqlConfig {
    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer(){
        return wiringBuilder->
                wiringBuilder.scalar(ExtendedScalars.LocalTime);
    }

    //test
    // CORS configuration
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/graphql") // Configure the specific path of your GraphQL endpoint
                        .allowedOrigins("http://localhost:5173") // Add the origin that is allowed to access your GraphQL server
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow specific HTTP methods
                        .allowedHeaders("*"); // Allow specific headers
            }
        };
    }
}


