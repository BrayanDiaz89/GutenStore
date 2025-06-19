package com.practice.GutenStore.model.dto.api;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBookDTO(
        String title,
        List<AuthorDTO> authors,
        List<LanguagesDTO> languages,
        FormatsDTO formats,
        @JsonAlias("download_count") Integer numberDownloads
) {
}
