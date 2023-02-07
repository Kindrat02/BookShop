package com.serve.mentorship.repository;

import com.serve.mentorship.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
