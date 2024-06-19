package com.bpm_camunda_service.pack.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CamundaMethods {

    private final WebClient webClient;

    public Object postCall(String url, Object data){
        Mono<Object> output= (Mono<Object>) webClient
                    .post()
                    .uri(url)
                    .body(Mono.just(data),data.getClass())
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, result -> {
                    return Mono.error(new Exception("Failed to Call Camunda api"));
                    })
                    .bodyToMono(Object.class)
                    .block();
        return output;
    }

}
