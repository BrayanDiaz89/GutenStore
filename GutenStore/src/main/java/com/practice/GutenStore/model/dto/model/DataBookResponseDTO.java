package com.practice.GutenStore.model.dto.model;
import com.practice.GutenStore.model.dto.api.AuthorDTO;
import com.practice.GutenStore.model.dto.api.FormatsDTO;
import com.practice.GutenStore.model.dto.api.LanguagesDTO;

import java.util.List;

public record DataBookResponseDTO(
        String title,
        List<AuthorDTO> authors,
        List<LanguagesDTO> languages,
        FormatsDTO formats,
        Integer numberDownloads
) {
}
