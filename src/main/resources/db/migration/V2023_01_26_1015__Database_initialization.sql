-- Description: This script creates initial database structure

create table author(
    id serial,
    name varchar,
    surname varchar,
    email varchar,
    birth_date date,
    created_at timestamp,
    updated_at timestamp,
    constraint pk_author primary key(id)
);

create table book(
    id serial,
    name varchar,
    publish_date date,
    created_at timestamp,
    updated_at timestamp,
    constraint pk_book primary key (id)
);

create table author_book(
    book_id int,
    author_id int,
    constraint pk_author_book primary key (book_id, author_id),
    constraint fk_author_book_author foreign key (author_id) references author(id) on delete cascade,
    constraint fk_author_book_book foreign key (book_id) references book(id) on delete cascade
);

create table customer(
    id serial,
    login varchar,
    password varchar,
    created_at timestamp,
    updated_at timestamp,
    constraint pk_customer primary key (id)
);

create table review(
    id serial,
    text varchar,
    rating int,
    book_id int,
    customer_id int,
    created_at timestamp,
    updated_at timestamp,
    constraint pk_review primary key (id),
    constraint fk_review_book foreign key (book_id) references book(id) on delete cascade,
    constraint fk_review_customer foreign key (customer_id) references customer(id) on delete cascade
);