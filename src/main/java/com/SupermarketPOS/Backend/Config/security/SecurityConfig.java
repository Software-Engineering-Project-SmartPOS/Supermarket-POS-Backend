package com.SupermarketPOS.Backend.Config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    //authentication
    public UserDetailsService userDetailsService(){
//        UserDetails admin = User.withUsername("amal")
//                .password(passwordEncoder().encode("pw1"))
//                .roles("admin")
//                .build();
//        UserDetails user = User.withUsername("john")
//                .password(passwordEncoder().encode("pw2"))
//                .roles("user")
//                .build();
//        return  new InMemoryUserDetailsManager(admin,user);

        return new UserInfoUserDetailsService();
    }



    @Bean
    //authorization
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/getToken","/RegisterOwner","/authenticate","/addEmployee","/getEmployee").permitAll())

                .authorizeHttpRequests((auth)-> auth
                        .requestMatchers("/graphql").authenticated())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();

    }
}
