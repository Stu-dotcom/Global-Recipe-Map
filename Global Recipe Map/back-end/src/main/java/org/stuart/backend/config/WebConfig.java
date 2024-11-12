package org.stuart.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow all endpoints
                        .allowedOrigins(
                                "https://global-recipe-map-1.onrender.com", // Render frontend URL
                                "http://localhost:3000" // local dev
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specified HTTP methods
                        .allowedHeaders("*");
            }
        };
    }
}


