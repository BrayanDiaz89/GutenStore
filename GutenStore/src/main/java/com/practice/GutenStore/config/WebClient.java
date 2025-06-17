package com.practice.GutenStore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClient {

    @Bean
    public org.springframework.web.reactive.function.client.WebClient openGutendexClient(){
        return org.springframework.web.reactive.function.client.WebClient.builder()
                .baseUrl("https://gutendex.com/books")
                .build();
    }
}
