CREATE TABLE title_authors (
    id_book INTEGER NOT NULL,
    id_author INTEGER NOT NULL,
    PRIMARY KEY (id_book, id_author),
    FOREIGN KEY (id_book) REFERENCES books(id_book) ON DELETE CASCADE,
    FOREIGN KEY (id_author) REFERENCES authors(id_author) ON DELETE CASCADE
);