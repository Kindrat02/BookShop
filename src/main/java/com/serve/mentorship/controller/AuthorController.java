package com.serve.mentorship.controller;

import com.serve.mentorship.dto.AuthorDTO;
import com.serve.mentorship.service.AuthorService;

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
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@Positive @PathVariable(name = "id") Integer authorId) throws NotFoundException {
        AuthorDTO authorDTO = authorService.getAuthorById(authorId).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(authorDTO, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDTO>> getAllAuthors(@PositiveOrZero @RequestParam(defaultValue = "0") int page,
                                                         @Positive @RequestParam(defaultValue = "3") int size) {
        List<AuthorDTO> list = authorService.getAllAuthors(PageRequest.of(page, size));
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/author")
    public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody AuthorDTO author) {
        AuthorDTO newAuthor = authorService.addAuthor(author);
        return new ResponseEntity<>(newAuthor, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/author")
    public ResponseEntity<AuthorDTO> updateAuthor(@Valid @RequestBody AuthorDTO author) throws NotFoundException {
        AuthorDTO updatedAuthor = authorService.updateAuthor(author).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(updatedAuthor, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity deleteAuthor(@Positive @PathVariable(name = "id") Integer authorId) {
        return new ResponseEntity<>(authorService.deleteAuthor(authorId)
                ? HttpStatus.OK
                : HttpStatus.GONE);
    }
}
