package org.stuart.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/h2-console/**").permitAll()  // Allow access to H2 console
                        .requestMatchers("/api/**").permitAll()         // Allow access to all API endpoints
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())  // Disable CSRF for H2 console
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin()) // Allow frames from the same origin
                );

        return http.build();
    }
}
