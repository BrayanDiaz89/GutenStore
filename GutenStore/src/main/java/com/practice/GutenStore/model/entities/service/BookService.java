package com.practice.GutenStore.model.entities.service;

import com.practice.GutenStore.model.dto.businessLogic.DataBook;
import com.practice.GutenStore.model.repository.BookRepository;
import com.practice.GutenStore.service.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Page<DataBook> getAllBooksByLanguage(String language,
                                                Pageable pageable){
        return bookRepository.findByLanguages(language, pageable)
                .map(book -> new BookMapper().toDataBook(book));
    }

    public Page<DataBook> getAllBooksByAuthorName(String authorName,
                                                  Pageable pageable){
        return bookRepository.findAllBooksByAuthorName(authorName, pageable)
                .map(book -> new BookMapper().toDataBook(book));
    }

}
