package com.SupermarketPOS.Backend.Config;

import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphqlConfig {
    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer(){
        return wiringBuilder->
                wiringBuilder.scalar(ExtendedScalars.LocalTime);
    }
}


