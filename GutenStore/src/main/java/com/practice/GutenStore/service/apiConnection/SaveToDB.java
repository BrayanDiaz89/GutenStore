package com.practice.GutenStore.service.apiConnection;

import com.practice.GutenStore.model.dto.api.DataBook;
import com.practice.GutenStore.model.dto.api.GutendexAPIResponse;
import com.practice.GutenStore.model.entities.Book;
import com.practice.GutenStore.model.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SaveToDB {

    @Autowired
    private BookRepository repository;

    public DataBook saveBookToDB(GutendexAPIResponse data) {
        if(data.books() == null || data.books().isEmpty()){
            throw new RuntimeException("Libro no encontrado, por favor valida el título.");
        }
        DataBook dataBook = data.books().getFirst();
        Optional<Book> existingBook = repository.findByTitle(dataBook.title());
        if (existingBook.isPresent()){
            return dataBook;
        }else {
            Book book = new Book(dataBook);
        }
    }
}
