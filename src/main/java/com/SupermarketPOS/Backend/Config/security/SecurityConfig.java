package com.SupermarketPOS.Backend.Config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class

SecurityConfig {
    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
//    authentication
    public UserDetailsService userDetailsService(){
//        UserDetails admin = User.withUsername("amal")
//                .password(passwordEncoder().encode("pw1"))
//                .roles("OWNER")
//                .build();
//        UserDetails user = User.withUsername("john")
//                .password(passwordEncoder().encode("pw2"))
//                .roles("user")
//                .build();
//        System.out.println("in memojjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjry");
//        System.out.println(admin.getAuthorities());
//        return  new InMemoryUserDetailsManager(admin,user);

        return new UserInfoUserDetailsService();
    }



    @Bean
    //authorization
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/getToken","/RegisterOwner","/addEmployee","/getEmployee","/getOwner1").permitAll()
                        .anyRequest().permitAll()
                )

//                .authorizeHttpRequests((auth)-> auth
//                        .requestMatchers("/graphql").authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS ))
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore( jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

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
