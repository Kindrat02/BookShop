package com.serve.mentorship.service;

import com.serve.mentorship.dto.AuthorDTO;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> getAllAuthors();
    AuthorDTO getAuthorById(Integer id) throws NotFoundException;
    AuthorDTO addAuthor(AuthorDTO author);
    AuthorDTO updateAuthor(AuthorDTO author) throws NotFoundException;
    void deleteAuthor(Integer id) throws NotFoundException;
}
