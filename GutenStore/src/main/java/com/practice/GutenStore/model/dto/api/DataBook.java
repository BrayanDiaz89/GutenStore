package com.practice.GutenStore.model.dto.api;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record DataBook(
        String title,
        List<AuthorDTO> authors,
        List<String> languages,
        FormatsDTO formats,
        @JsonAlias("download_count") Integer numberDownloads
) {
}
