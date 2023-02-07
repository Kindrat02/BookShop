package com.serve.mentorship.controller;

import com.serve.mentorship.dto.AuthorDTO;
import com.serve.mentorship.service.AuthorService;

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
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author/{id}")
    public AuthorDTO getAuthorById(@Positive @PathVariable(name = "id") Integer authorId) throws NotFoundException {
        return authorService.getAuthorById(authorId);
    }

    @GetMapping("/authors")
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping("/author")
    public AuthorDTO createAuthor(@Valid @RequestBody AuthorDTO author) {
        return authorService.addAuthor(author);
    }

    @PutMapping("/author")
    public AuthorDTO updateAuthor(@Valid @RequestBody AuthorDTO author) {
        return authorService.addAuthor(author);
    }

    @DeleteMapping("/author")
    public void deleteAuthor(@Positive @RequestParam(name = "id") Integer authorId) throws NotFoundException {
        authorService.deleteAuthor(authorId);
    }
}
