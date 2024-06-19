package com.bpm_camunda_service.pack.webclient_config;

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
                .baseUrl("CamundaUrl")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

    }
}
