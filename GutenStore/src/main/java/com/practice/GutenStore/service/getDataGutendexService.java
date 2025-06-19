package com.practice.GutenStore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.GutenStore.config.WebClient;
import com.practice.GutenStore.model.dto.api.DataBookDTO;
import com.practice.GutenStore.model.dto.api.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class getDataGutendexService {

    @Autowired
    private WebClient webClient;
    @Autowired
    private ObjectMapper objectMapper;

    public Mono<DataBookDTO> getDataFromGutendex(RequestDTO request){

        return webClient.openGutendexClient().get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("search", request.bookTitle().replace(" ", "+"))
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        Mono.error(new RuntimeException("Libro no encontrado en el servidor."))
                )
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new RuntimeException("Error del servidor. Intenta más tarde."))
                )
                .bodyToMono(DataBookDTO.class)
                .map(apiResponse -> {
                    try{
                        return new DataBookDTO(
                                apiResponse.title(),
                                apiResponse.authors(),
                                apiResponse.languages(),
                                apiResponse.formats(),
                                apiResponse.numberDownloads()
                        );
                    }catch (Exception e){
                        throw new RuntimeException("Error parsing JSON: " + e.getMessage());
                    }
                }).onErrorMap(e -> {
                    if(e instanceof RuntimeException){
                        return e;
                    }
                    return new RuntimeException("Error inesperado. " + e.getMessage());
                });
    }

}
