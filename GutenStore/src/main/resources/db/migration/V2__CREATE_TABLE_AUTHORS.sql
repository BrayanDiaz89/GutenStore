CREATE TABLE authors (
    id_author SERIAL PRIMARY KEY,
    name_author VARCHAR(255) NOT NULL,
    birth_year INTEGER,
    death_year INTEGER
);