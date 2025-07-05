package com.practice.GutenStore.controller;

import com.practice.GutenStore.model.dto.businessLogic.AuthorsBooksPageResponse;
import com.practice.GutenStore.model.dto.businessLogic.AuthorsPageReponse;
import com.practice.GutenStore.model.dto.businessLogic.AuthorsWithBooks;
import com.practice.GutenStore.model.entities.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    //Listar solo a los autores
    @GetMapping
    public ResponseEntity<AuthorsPageReponse> getDistinctAuthors(@PageableDefault(size = 5) Pageable pageable) {
        var authorsPage = authorService.getAllAuthors(pageable);
        return ResponseEntity.ok().body(new AuthorsPageReponse(authorsPage));
    }

    //Listar a todos los autores con sus libros
    @GetMapping("/with-books")
    public ResponseEntity<AuthorsBooksPageResponse> getDistinctAuthorsWithBooks(@PageableDefault(size = 5) Pageable pageable) {
        var pageAuthorWithBooks = authorService.getAllAuthorsWithBooks(pageable);
        return ResponseEntity.ok().body(new AuthorsBooksPageResponse(pageAuthorWithBooks));
    }

    //Listar a todos los autores entre una fecha de nacimiento de inicio y fin, hasta una fecha de muerte de inicio y fin
    @GetMapping("/between-dates/{birthStart}/{birthEnd}")
    public ResponseEntity<AuthorsPageReponse> getAuthorsBetweenDates(@PageableDefault(size = 5)
                                                                         @PathVariable("birthStart") Integer birthStart,
                                                                         @PathVariable("birthEnd") Integer birthEnd,
                                                                         Pageable pageable) {
        var authorsPage = authorService.getAuthorsBetweenDates(birthStart, birthEnd, pageable);
        return ResponseEntity.ok().body(new AuthorsPageReponse(authorsPage));
    }

}
