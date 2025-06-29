package com.practice.GutenStore.model.repository;

import com.practice.GutenStore.model.entities.Book;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
}
