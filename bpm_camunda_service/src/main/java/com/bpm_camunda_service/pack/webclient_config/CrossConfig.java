package com.bpm_camunda_service.pack.webclient_config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrossConfig implements WebMvcConfigurer {



        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/loan/**")  // Adjust the path pattern as needed
                    .allowedOrigins("http://localhost:4200")  // Allow requests from this origin
                    .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow these HTTP methods
                    .allowCredentials(true);  // Allow sending credentials like cookies
        }
    }

