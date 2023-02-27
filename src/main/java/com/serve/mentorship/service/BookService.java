package com.serve.mentorship.service;

import com.serve.mentorship.dto.BookDTO;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDTO> getAllBooks(Pageable pageable);
    Optional<BookDTO> getBookById(Integer id);
    boolean deleteBook(Integer id);
    BookDTO saveBook(BookDTO book);
    Optional<BookDTO> updateBook(BookDTO book);
}
