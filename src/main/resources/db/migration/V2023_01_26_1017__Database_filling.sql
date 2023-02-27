-- Description: This script fills table with required data

INSERT INTO author(name, surname, birth_date)
VALUES ('Taras', 'Shevchenko', '1922-02-02'),
       ('Ivan', 'Franko', '1922-02-02'),
       ('Lesya', 'Ukrainka', '1871-02-25');

INSERT INTO book(name, publish_date)
VALUES ('Kobzar', '1922-02-02'),
       ('Contra Spem Spero', '1922-02-02');

INSERT INTO author_book(book_id, author_id)
VALUES ((SELECT id FROM book WHERE name = 'Kobzar'), (SELECT id FROM author WHERE name || surname = 'TarasShevchenko')),
       (1, 2);

INSERT INTO customer(login, password)
VALUES ('User1', 'password'),
       ('User2', 'password2');

INSERT INTO review(text, rating, book_id, customer_id, created_at)
VALUES ('Amazing book', 5, (SELECT id FROM book WHERE name = 'Contra Spem Spero'), (SELECT id FROM customer WHERE login = 'User1'), '2023-02-22 19:10:25-07'),
       ('Dull book', 3, (SELECT id FROM book WHERE name = 'Kobzar'), (SELECT id FROM customer WHERE login = 'User2'), '2023-02-23 19:10:25-07');