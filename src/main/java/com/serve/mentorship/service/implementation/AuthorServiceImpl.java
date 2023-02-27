package com.serve.mentorship.service.implementation;

import com.serve.mentorship.dto.AuthorDTO;
import com.serve.mentorship.entity.Author;
import com.serve.mentorship.mapper.AuthorMapper;
import com.serve.mentorship.repository.AuthorRepository;
import com.serve.mentorship.service.AuthorService;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<AuthorDTO> getAllAuthors(Pageable pageable) {
        return authorRepository
                .findAll(pageable)
                .stream()
                .map(authorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorDTO> getAuthorById(Integer id) {
        return authorRepository
                .findById(id)
                .map(authorMapper::toDTO);
    }

    @Transactional
    @Override
    public AuthorDTO addAuthor(AuthorDTO author) {
        Author authorModel = authorMapper.toModel(author);
        authorModel.setCreatedAt(new GregorianCalendar());

        Author savedEntity = authorRepository.save(authorModel);
        return authorMapper.toDTO(savedEntity);
    }

    @Transactional
    @Override
    public Optional<AuthorDTO> updateAuthor(AuthorDTO author) {
        return authorRepository.findById(author.getId()).map(val -> {
            val.setName(author.getName());
            val.setSurname(author.getSurname());
            val.setBirthDate(author.getBirthDate());
            val.setEmail(author.getEmail());
            val.setUpdatedAt(new GregorianCalendar());

            return authorRepository.save(val);
        }).map(authorMapper::toDTO);
    }

    @Transactional
    @Override
    public boolean deleteAuthor(Integer id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
