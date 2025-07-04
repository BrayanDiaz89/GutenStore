package com.practice.GutenStore.service.mapper;

import com.practice.GutenStore.model.dto.api.FormatsDTO;
import com.practice.GutenStore.model.dto.businessLogic.AuthorDTO;
import com.practice.GutenStore.model.dto.businessLogic.BookPageResponse;
import com.practice.GutenStore.model.dto.businessLogic.DataBook;
import com.practice.GutenStore.model.entities.Book;
// Asegúrate de que esta ruta sea correcta
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public DataBook toDataBook(Book book) {
        if (book == null) {
            return null;
        }
        return new DataBook(
                book.getTitle(),
                book.getAuthors().stream()
                        .map(author -> new AuthorDTO(author.getNameAuthor(),
                                author.getBirthYear(), author.getDeathYear())) // Ajusta según tu AuthorDTO
                        .collect(Collectors.toList()),
                book.getLanguages() != null ? List.of(book.getLanguages().split(",\\s*")) : List.of(),
                book.getFormats() != null ?
                        new FormatsDTO(book.getFormats().getPoster(), book.getFormats().getE_book()) :
                        null,
                book.getNumberDownloads()
        );
    }
}
