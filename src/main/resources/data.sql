INSERT INTO genre (id, type) VALUES (1, 'Young adult fiction');
INSERT INTO genre (id, type) VALUES (2, 'Science fiction');
INSERT INTO genre (id, type) VALUES (3, 'Dystopian fiction');
INSERT INTO genre (id, type) VALUES (4, 'Romance');
INSERT INTO genre (id, type) VALUES (5, 'Satire');
INSERT INTO genre (id, type) VALUES (6, 'Tragedy');
INSERT INTO genre (id, type) VALUES (7, 'Horror');
INSERT INTO genre (id, type) VALUES (8, 'Fantasy');
INSERT INTO genre (id, type) VALUES (9, 'Adventure');
INSERT INTO genre (id, type) VALUES (10, 'Personal narrative');
INSERT INTO genre (id, type) VALUES (11, 'Modernism');
INSERT INTO genre (id, type) VALUES (12, 'Realism');
INSERT INTO genre (id, type) VALUES (13, 'Comedy');
INSERT INTO genre (id, type) VALUES (14, 'Black comedy');
INSERT INTO genre (id, type) VALUES (15, 'Postmodernism');
INSERT INTO genre (id, type) VALUES (16, 'Bildungsroman');
INSERT INTO genre (id, type) VALUES (17, 'Southern Gothic');
INSERT INTO book (id, isbn, title, author, publication_year, price, quantity) VALUES (1, '9783883890227', 'The Catcher in the Rye', 'Jerome David Salinger', '1994', '19.50', 1);
INSERT INTO book_genre (book_id, genre_id) VALUES (1, 1);
INSERT INTO book_genre (book_id, genre_id) VALUES (1, 10);
INSERT INTO role (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO role (id, name) VALUES (2, 'ROLE_USER');
INSERT INTO user (id, email, password, enabled) VALUES (1, 'admin@epam.com', '$2a$10$BEebJV2WCUPndkLvYiDbB.1MmvsdIvcdQIhD60mj/IXmy9QaUcBWy', true );
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);