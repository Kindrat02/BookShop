package com.serve.mentorship.service.implementation;

import com.serve.mentorship.dto.BookDTO;
import com.serve.mentorship.entity.Author;
import com.serve.mentorship.entity.Book;
import com.serve.mentorship.mapper.AuthorMapper;
import com.serve.mentorship.mapper.BookMapper;
import com.serve.mentorship.repository.BookRepository;
import com.serve.mentorship.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           BookMapper bookMapper,
                           AuthorMapper authorMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<BookDTO> getAllBooks(Pageable pageable) {
        return bookRepository
                .findAll(pageable)
                .stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> getBookById(Integer id) {
        return bookRepository
                .findById(id)
                .map(bookMapper::toDTO);
    }

    @Override
    public boolean deleteBook(Integer id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public BookDTO saveBook(BookDTO book) {
        Book bookModel = bookMapper.toModel(book);
        bookModel.setCreatedAt(new GregorianCalendar());
        bookModel.getCreatedAt().get(Calendar.YEAR);

        for (Author author: bookModel.getAuthors()) {
            author.getBooks().add(bookModel);
        }
        Book savedEntity = bookRepository.save(bookModel);
        return bookMapper.toDTO(savedEntity);
    }

    // TODO: Fix update when add an author
    @Override
    public Optional<BookDTO> updateBook(BookDTO book) {
        return bookRepository.findById(book.getId()).map(val -> {
            val.setName(book.getName());
            val.setPublishDate(book.getPublishDate());
            val.setAuthors(book
                    .getAuthors()
                    .stream()
                    .map(authorMapper::toModel)
                    .collect(Collectors.toSet()));

            for (Author author: val.getAuthors()) {
                author.getBooks().add(val);
            }

            bookRepository.save(val);

            return val;
        }).map(bookMapper::toDTO);
    }
}
