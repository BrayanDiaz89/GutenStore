package com.practice.GutenStore.model.repository;

import com.practice.GutenStore.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Long, Book> {
}
