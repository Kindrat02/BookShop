package com.serve.mentorship.controller;

import com.serve.mentorship.dto.AuthorDTO;
import com.serve.mentorship.dto.BookDTO;
import com.serve.mentorship.entity.Book;
import com.serve.mentorship.service.BookService;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/{id}")
    public BookDTO getBookById(@Positive @PathVariable(name = "id") Integer bookId) throws NotFoundException {
        return bookService.getBookById(bookId);
    }

    @GetMapping("/books")
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @DeleteMapping("/book")
    public void deleteBook(@Positive @RequestParam(name = "id") Integer bookId) throws NotFoundException {
        bookService.deleteBook(bookId);
    }

    @PostMapping("/book")
    public BookDTO addBook(@Valid @RequestBody BookDTO book) {
        return bookService.saveBook(book);
    }

    @PutMapping("/book/{id}/author")
    public BookDTO addAuthorToBook(@Valid @RequestBody AuthorDTO author,
                                   @Positive @PathVariable(name = "id") Integer bookId) throws NotFoundException {
        return bookService.addAuthorToBook(bookId, author);
    }

    @DeleteMapping("/book/{id}/author")
    public BookDTO detachAuthorFromBook(@Valid @RequestBody AuthorDTO author,
                                   @Positive @PathVariable(name = "id") Integer bookId) throws NotFoundException {
        return bookService.detachAuthorFromBook(bookId, author);
    }

    @PutMapping("/book")
    public BookDTO updateBook(@Valid @RequestBody BookDTO book) throws NotFoundException {
        return bookService.updateBook(book);
    }
}
