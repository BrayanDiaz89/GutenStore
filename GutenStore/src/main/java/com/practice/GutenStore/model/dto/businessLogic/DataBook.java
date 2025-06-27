package com.practice.GutenStore.model.dto.businessLogic;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.practice.GutenStore.model.dto.api.FormatsDTO;
import com.practice.GutenStore.model.entities.Book;

import java.util.List;

public record DataBook(
        String title,
        List<AuthorDTO> authors,
        List<String> languages,
        FormatsDTO formats,
        @JsonAlias("download_count") Integer numberDownloads
) {
    public DataBook(Book book) {
        this(book.getTitle(), book.getAuthors(), book.getLang(),
                book);
    }
}
