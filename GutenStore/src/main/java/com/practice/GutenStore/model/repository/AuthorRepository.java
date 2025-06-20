package com.practice.GutenStore.model.repository;

import com.practice.GutenStore.model.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByNameAuthorAndBirthYearAndDeathYear(String nameAuthor,
                                                              Integer BirthYear,
                                                              Integer DeathYear);
}
