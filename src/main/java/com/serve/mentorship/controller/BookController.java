package com.serve.mentorship.controller;

import com.serve.mentorship.dto.BookDTO;
import com.serve.mentorship.service.BookService;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookDTO> getBookById(@Positive @PathVariable(name = "id") Integer bookId) throws NotFoundException {
        BookDTO bookDTO = bookService.getBookById(bookId).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(bookDTO, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getAllBooks(@PositiveOrZero @RequestParam(defaultValue = "0") int page,
                                                     @Positive @RequestParam(defaultValue = "3") int size) {
        List<BookDTO> list = bookService.getAllBooks(PageRequest.of(page, size));
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity deleteBook(@Positive @PathVariable(name = "id") Integer bookId) {
        return new ResponseEntity(bookService.deleteBook(bookId) ? HttpStatus.OK : HttpStatus.GONE);
    }

    @PostMapping("/book")
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO book) {
        BookDTO newBook =  bookService.saveBook(book);
        return new ResponseEntity<>(newBook, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/book")
    public ResponseEntity<BookDTO> updateBook(@Valid @RequestBody BookDTO book) throws NotFoundException {
        BookDTO updatedBook = bookService.updateBook(book).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(updatedBook, new HttpHeaders(), HttpStatus.OK);
    }
}
