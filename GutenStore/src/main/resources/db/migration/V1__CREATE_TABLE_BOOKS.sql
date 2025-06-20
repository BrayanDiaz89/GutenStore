CREATE TABLE books (
    id_book SERIAL PRIMARY KEY,
    title VARCHAR(255) UNIQUE NOT NULL,
    lang VARCHAR(20) NOT NULL,
    poster VARCHAR(255),
    number_downloads INTEGER,
    e_book VARCHAR(255)
);