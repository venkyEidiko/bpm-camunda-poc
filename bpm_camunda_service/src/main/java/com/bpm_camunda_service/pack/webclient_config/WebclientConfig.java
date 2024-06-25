package com.bpm_camunda_service.pack.webclient_config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebclientConfig {

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
//                .baseUrl("http://10.0.0.27:8080/engine-rest")
                .baseUrl("http://localhost:1010/engine-rest")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
