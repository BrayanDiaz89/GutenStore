package com.practice.GutenStore.model.dto.api;

import com.practice.GutenStore.model.entities.Author;

import java.util.List;

public record ResultsDTO(
        String title,
        List<Author> authors,
        List<LanguagesDTO> languages,

) {
}
