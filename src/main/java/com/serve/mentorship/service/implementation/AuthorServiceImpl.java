package com.serve.mentorship.service.implementation;

import com.serve.mentorship.dto.AuthorDTO;
import com.serve.mentorship.entity.Author;
import com.serve.mentorship.mapper.AuthorMapper;
import com.serve.mentorship.repository.AuthorRepository;
import com.serve.mentorship.service.AuthorService;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository
                .findAll()
                .stream()
                .map(authorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTO getAuthorById(Integer id) throws NotFoundException {
        return authorMapper.toDTO(
                authorRepository
                .findById(id)
                .orElseThrow(NotFoundException::new));
    }

    @Override
    public AuthorDTO addAuthor(AuthorDTO author) {
        Author savedEntity = authorRepository.save(authorMapper.toModel(author));
        return authorMapper.toDTO(savedEntity);
    }

    @Override
    public AuthorDTO updateAuthor(AuthorDTO author) throws NotFoundException {
        Author savedEntity = authorRepository.findById(author.getId()).orElseThrow(NotFoundException::new);

        savedEntity.setName(author.getName());
        savedEntity.setSurname(author.getSurname());
        savedEntity.setBirthDate(author.getBirthDate());

        return authorMapper.toDTO(savedEntity);
    }

    @Override
    public void deleteAuthor(Integer id) throws NotFoundException {
        authorRepository.delete(
                authorRepository
                        .findById(id)
                        .orElseThrow(NotFoundException::new));
    }
}
