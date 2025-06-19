package com.practice.GutenStore.model.entities;

import com.practice.GutenStore.model.dto.api.DataBook;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "books")
@Entity(name = "Book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_book;
    @Column(unique = true, nullable = false)
    private String title;

    private String lang;
    private String poster;
    private String e_book;
    @Column(name = "number_downloads")
    private Integer numberDownloads;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "title_authors",
            joinColumns = @JoinColumn(name = "id_book"),
            inverseJoinColumns = @JoinColumn(name = "id_author")
    )
    private List<Author> authors = new ArrayList<>();

    @Column(name = "is_active")
    private Boolean isActive;

    public Book(DataBook dataBook) {
         this.title = dataBook.title();
         this.lang = dataBook.languages() != null ? String.join(", ", dataBook.languages()) : "";
         this.poster = dataBook.formats().poster();
         this.e_book = dataBook.formats().e_book();
         this.numberDownloads = dataBook.numberDownloads();
    }
}
