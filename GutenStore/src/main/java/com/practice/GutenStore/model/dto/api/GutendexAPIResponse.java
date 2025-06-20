package com.practice.GutenStore.model.dto.api;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.practice.GutenStore.model.dto.businessLogic.DataBook;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GutendexAPIResponse(
        @JsonAlias("results") List<DataBook> books
) {
}
