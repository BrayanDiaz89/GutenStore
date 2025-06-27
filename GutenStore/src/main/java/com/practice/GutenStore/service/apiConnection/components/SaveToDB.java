package com.practice.GutenStore.service.apiConnection.components;

import com.practice.GutenStore.model.dto.businessLogic.AuthorDTO;
import com.practice.GutenStore.model.dto.businessLogic.DataBook;
import com.practice.GutenStore.model.dto.api.GutendexAPIResponse;
import com.practice.GutenStore.model.dto.businessLogic.SaveResult;
import com.practice.GutenStore.model.entities.Author;
import com.practice.GutenStore.model.entities.Book;
import com.practice.GutenStore.model.repository.AuthorRepository;
import com.practice.GutenStore.model.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class SaveToDB {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    //Metodo para guardar en base de datos Libro y autor
    public SaveResult saveBookToDB(GutendexAPIResponse data) {

        //Validamos si el libro no fue encontrado en la web gutendex
        if(data.books() == null || data.books().isEmpty()){
            throw new RuntimeException("Libro no encontrado, por favor valida el título.");
        }
        //Obtener el primer libro retornado por gutendex
        DataBook dataBook = data.books().getFirst();
        //Encontramos o no el libro en la base de datos.
        Optional<Book> existingBook = bookRepository.findByTitle(dataBook.title());
        //Si fue encontrado, lo retornamos
        if (existingBook.isPresent()){
            log.info("Success, book in the database.");
            return new SaveResult(true, dataBook); //Libro existe en base de datos.
        }
        //De lo contrario inicia la lógica para guardarlo en base de datos.
        //Creó un objeto libro con la información de gutendex
        var book = new Book(dataBook);

        //Valido si el/los authores, ya existen en la base de datos
        for(AuthorDTO authorDTO : dataBook.authors()){
            Optional<Author> existingAuthor = authorRepository.findByNameAuthorAndBirthYearAndDeathYear(
                    authorDTO.authorName(),
                    authorDTO.birthYear(),
                    authorDTO.deathYear()
                );

            Author author = existingAuthor.orElseGet(() -> {
                Author authorNew = new Author();
                authorNew.setNameAuthor(authorDTO.authorName());
                authorNew.setBirthYear(authorDTO.birthYear());
                authorNew.setDeathYear(authorDTO.deathYear());
                return authorNew;
                });

            book.getAuthors().add(author);

            }
        log.info("Success. Book saved to Data Base");
        bookRepository.save(book);

        return new SaveResult(false, dataBook);
    }
}
