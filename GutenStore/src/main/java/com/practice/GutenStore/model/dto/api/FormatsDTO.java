package com.practice.GutenStore.model.dto.api;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FormatsDTO(
        @JsonAlias("image/jpeg") String poster,
        @JsonAlias("application/octet-stream") String e_book
) {
}
