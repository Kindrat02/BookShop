package com.serve.mentorship.service;

import com.serve.mentorship.dto.AuthorDTO;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorDTO> getAllAuthors(Pageable pageable);
    Optional<AuthorDTO> getAuthorById(Integer id);
    AuthorDTO addAuthor(AuthorDTO author);
    Optional<AuthorDTO> updateAuthor(AuthorDTO author);
    boolean deleteAuthor(Integer id);
}
