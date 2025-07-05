package com.practice.GutenStore.model.dto.businessLogic;

import java.util.List;

public record AuthorsWithBooks(
        Long idAuthor,
        String authorName,
        Integer birthYear,
        Integer deathYear,
        List<BookSummaryDTO> books
) {
}
