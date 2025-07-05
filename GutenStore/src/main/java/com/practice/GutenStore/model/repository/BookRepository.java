package com.practice.GutenStore.model.repository;

import com.practice.GutenStore.model.dto.businessLogic.DataBook;
import com.practice.GutenStore.model.entities.Book;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
    Page<Book> findByIsActiveTrue(Pageable pageable);

    //Obtener todos los libros de un lenguaje
    Page<Book> findByLanguages(String language, Pageable pageable);
    //Obtener todos los libros de un autor específico
    @Query("""
          SELECT b FROM Book b
          JOIN b.authors a
          WHERE LOWER(a.nameAuthor) LIKE LOWER(CONCAT('%', :nameAuthor, '%'))
          ORDER BY a.nameAuthor
          """)
    Page<Book> findAllBooksByAuthorName(String nameAuthor, Pageable pageable);

}
