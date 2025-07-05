package com.practice.GutenStore.controller;

import com.practice.GutenStore.model.dto.businessLogic.AuthorsBooksPageResponse;
import com.practice.GutenStore.model.dto.businessLogic.AuthorsPageReponse;
import com.practice.GutenStore.model.dto.businessLogic.AuthorsWithBooks;
import com.practice.GutenStore.model.entities.service.AuthorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/between-dates-birth/search")
    public ResponseEntity<AuthorsPageReponse> getAuthorsBetweenDates(@PageableDefault(size = 5)
                                                                         @RequestParam("birthStart") Integer birthStart,
                                                                         @RequestParam("birthEnd") Integer birthEnd,
                                                                         Pageable pageable) {
        var authorsPage = authorService.getAuthorsBetweenDatesBirthYear(birthStart, birthEnd, pageable);
        return ResponseEntity.ok().body(new AuthorsPageReponse(authorsPage));
    }

    @GetMapping("/between-dates-death/search")
    public ResponseEntity<AuthorsPageReponse> getAuthorsBetweenDatesDeathYear(@PageableDefault(size = 5)
                                                                              @RequestParam("deathStart") Integer deathStart,
                                                                              @RequestParam("deathEnd") Integer deathEnd,
                                                                              Pageable pageable) {
        var authorsPage = authorService.getAuthorsBetweenDatesDeathYear(deathStart, deathEnd, pageable);
        return ResponseEntity.ok().body(new AuthorsPageReponse(authorsPage));
    }

    //Obtener solo información de autores por nombre (Nombre, fecha de nacimiento, fecha de muerte)
    @GetMapping("/search")
    public ResponseEntity<AuthorsPageReponse> getAuthorsByName(@PageableDefault(size = 5)
                                                               @RequestParam("nameAuthor") String nameAuthor,
                                                               Pageable pageable) {
        var authorsPage = authorService.getAuthorsByName(nameAuthor, pageable);
        return ResponseEntity.ok().body(new AuthorsPageReponse(authorsPage));
    }

    //Obtener información de autores con libros por nombre de autor (Nombre, fecha de nacimiento, fecha de muerte, libros)
    @GetMapping("/with-books/search")
    public ResponseEntity<AuthorsBooksPageResponse> getAuthorsWithBooksByName(@PageableDefault(size = 5)
                                                                              @RequestParam("nameAuthor") String nameAuthor,
                                                                              Pageable pageable) {
        var authorsPage = authorService.getAllAuthorsWithBooksByNameAuthor(nameAuthor, pageable);
        return ResponseEntity.ok().body(new AuthorsBooksPageResponse(authorsPage));
    }

}
