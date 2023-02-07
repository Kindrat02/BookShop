-- Description: This script fills table with required data

INSERT INTO author(name, surname, birth_date)
VALUES ('Taras', 'Schevchenko', '1922-02-02'),
       ('Ivan', 'Franko', '1922-02-02'),
       ('Lesia', 'Ukrainka', '1922-02-02');

INSERT INTO book(name, publish_date)
VALUES ('Kobzar', '1922-02-02'),
       ('Contra Spem Spero', '1922-02-02');

INSERT INTO author_book(book_id, author_id)
VALUES ((SELECT id FROM book WHERE name = 'Kobzar'), (SELECT id FROM author WHERE name || surname = 'TarasSchevchenko')),
       (1, 2);

INSERT INTO customer(login, password)
VALUES ('User1', 'password'),
       ('User2', 'password2');

INSERT INTO review(text, book_id, customer_id)
VALUES ('Amazing book', (SELECT id FROM book WHERE name = 'Contra Spem Spero'), (SELECT id FROM customer WHERE login = 'User1')),
       ('Dull book', (SELECT id FROM book WHERE name = 'Kobzar'), (SELECT id FROM customer WHERE login = 'User2'));