package com.practice.GutenStore.model.repository;

import com.practice.GutenStore.model.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByNameAuthorAndBirthYearAndDeathYear(String nameAuthor,
                                                              Integer BirthYear,
                                                              Integer DeathYear);

    //Obtener a todos los autores en paginación, sin que se repitan
    @Query(value = """
    SELECT DISTINCT ON (a.name_author, a.birth_year, a.death_year) a.*
    FROM authors a
    ORDER BY a.name_author, a.birth_year, a.death_year, a.id_author
    """,
            countQuery = "SELECT COUNT(*) FROM (SELECT DISTINCT ON (name_author, birth_year, death_year) 1 FROM authors) AS count_sub",
            nativeQuery = true)
    Page<Author> findDistinctAuthors(Pageable pageable);

    //Ver autores que nacieron en cierto rango
    @Query("""
           SELECT a FROM Author a
           WHERE a.birthYear BETWEEN :birthStart AND :birthEnd
           ORDER BY a.nameAuthor
           """)
    Page<Author> findByBirthYearBetween(Integer birthStart, Integer birthEnd, Pageable pageable);

    //Ver autores que fallecieron en cierto rango
    @Query("""
           SELECT a FROM Author a
           WHERE a.deathYear BETWEEN :deathStart AND :deathEnd
           ORDER BY a.nameAuthor 
           """)
    Page<Author> findByDeathYearBetween(Integer deathStart, Integer deathEnd, Pageable pageable);

}
