package com.practice.GutenStore.model.entities.service;

import com.practice.GutenStore.model.dto.businessLogic.AuthorDTO;
import com.practice.GutenStore.model.dto.businessLogic.AuthorsWithBooks;
import com.practice.GutenStore.model.dto.businessLogic.BookSummaryDTO;
import com.practice.GutenStore.model.repository.AuthorRepository;
import com.practice.GutenStore.service.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Page<AuthorDTO> getAllAuthors(Pageable pageable) {
        return authorRepository.findDistinctAuthors(pageable)
                .map(author -> new AuthorMapper().toDataAuthor(author));
    }

    public Page<AuthorsWithBooks> getAllAuthorsWithBooks(Pageable pageable) {
            return authorRepository.findDistinctAuthors(pageable)
                    .map(author -> new AuthorsWithBooks(
                            author.getIdAuthor(),
                            author.getNameAuthor(),
                            author.getBirthYear(),
                            author.getDeathYear(),
                            author.getBooks().stream()
                                    .map(book -> new BookSummaryDTO(book.getId_book(),
                                            book.getTitle()))
                                    .toList()
                    ));
        }
    public Page<AuthorsWithBooks> getAllAuthorsWithBooksByNameAuthor(String nameAuthor, Pageable pageable) {
        return authorRepository.findDistinctAuthorsByName(nameAuthor, pageable)
                .map(author -> new AuthorsWithBooks(
                        author.getIdAuthor(),
                        author.getNameAuthor(),
                        author.getBirthYear(),
                        author.getDeathYear(),
                        author.getBooks().stream()
                                .map(book -> new BookSummaryDTO(book.getId_book(),
                                        book.getTitle()))
                                .toList()
                ));
    }

    //Ver autores entre una fecha de nacimiento inicio y fin
    public Page<AuthorDTO> getAuthorsBetweenDatesBirthYear(
                                                Integer birthStart,
                                                Integer birthEnd,
                                                Pageable pageable) {
        return authorRepository.findByBirthYearBetween(birthStart, birthEnd, pageable)
                .map(author -> new AuthorMapper().toDataAuthor(author));
    }

    public Page<AuthorDTO> getAuthorsBetweenDatesDeathYear(
                                                Integer deathStart,
                                                Integer deathEnd,
                                                Pageable pageable) {
    return authorRepository.findByDeathYearBetween(deathStart, deathEnd, pageable)
            .map(author -> new AuthorMapper().toDataAuthor(author));
    }

    public Page<AuthorDTO> getAuthorsByName(String nameAuthor, Pageable pageable) {
        return authorRepository.findByNameAuthor(nameAuthor, pageable)
                .map(author -> new AuthorMapper().toDataAuthor(author));
    }
}
