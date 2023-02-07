package com.serve.mentorship.service;

import com.serve.mentorship.dto.AuthorDTO;
import com.serve.mentorship.dto.BookDTO;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
    BookDTO getBookById(Integer id) throws NotFoundException;
    void deleteBook(Integer id) throws NotFoundException;
    BookDTO saveBook(BookDTO book);
    BookDTO updateBook(BookDTO book) throws NotFoundException;
    BookDTO addAuthorToBook(Integer bookId, AuthorDTO author) throws NotFoundException;
}
