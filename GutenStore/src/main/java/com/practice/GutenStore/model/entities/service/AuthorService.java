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

}
