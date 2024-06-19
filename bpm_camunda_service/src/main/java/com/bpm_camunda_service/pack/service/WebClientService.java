package com.bpm_camunda_service.pack.service;

import com.bpm_camunda_service.pack.model.response.StartProcessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class WebClientService {

    private final WebClient webClient;

    public StartProcessResponse postCall(String url, Object data){
        Mono<StartProcessResponse> response = this.webClient.post()
                .uri(url)
                .body(Mono.just(data), data.getClass())
                .retrieve()
                .onStatus(HttpStatusCode::isError, result -> {
                    return Mono.error(new Exception("Failed to call an API"));
                })
                .bodyToMono(StartProcessResponse.class);
        return response.block();
    }

}
