package com.serve.mentorship.service.implementation;

import com.serve.mentorship.dto.AuthorDTO;
import com.serve.mentorship.dto.BookDTO;
import com.serve.mentorship.entity.Author;
import com.serve.mentorship.entity.Book;
import com.serve.mentorship.mapper.AuthorMapper;
import com.serve.mentorship.mapper.BookMapper;
import com.serve.mentorship.repository.AuthorRepository;
import com.serve.mentorship.repository.BookRepository;
import com.serve.mentorship.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           BookMapper bookMapper,
                           AuthorMapper authorMapper,
                           AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Integer id) throws NotFoundException {
        return bookMapper.toDTO(
                bookRepository
                .findById(id)
                .orElseThrow(NotFoundException::new));
    }

    @Override
    public void deleteBook(Integer id) throws NotFoundException {
        bookRepository.delete(
                bookRepository
                        .findById(id)
                        .orElseThrow(NotFoundException::new));
    }

    @Override
    public BookDTO saveBook(BookDTO book) {
        Book bookModel = bookMapper.toModel(book);
        for (Author author: bookModel.getAuthors()) {
            author.getBooks().add(bookModel);
        }
        Book savedEntity = bookRepository.save(bookModel);
        return bookMapper.toDTO(savedEntity);
    }

    @Override
    public BookDTO addAuthorToBook(Integer bookId, AuthorDTO author) throws NotFoundException {
        Author authorEntity = authorMapper.toModel(author);
        authorEntity = authorRepository.findById(authorEntity.getId()).orElseThrow(NotFoundException::new);

        Book book = bookRepository.findById(bookId).orElseThrow(NotFoundException::new);
        book.getAuthors().add(authorEntity);
        authorEntity.getBooks().add(book);
        bookRepository.save(book);

        return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO detachAuthorFromBook(Integer bookId, AuthorDTO author) throws NotFoundException {
        Author authorEntity = authorMapper.toModel(author);
        authorEntity = authorRepository.findById(authorEntity.getId()).orElseThrow(NotFoundException::new);

        Book book = bookRepository.findById(bookId).orElseThrow(NotFoundException::new);
        book.getAuthors().remove(authorEntity);
        authorEntity.getBooks().remove(book);
        bookRepository.save(book);

        return bookMapper.toDTO(book);
    }

    // TODO: Fix update when add an author
    @Override
    public BookDTO updateBook(BookDTO book) throws NotFoundException {
        Book updatedEntity = bookRepository.findById(book.getId()).orElseThrow(NotFoundException::new);

        updatedEntity.setName(book.getName());
        updatedEntity.setPublishDate(book.getPublishDate());
        updatedEntity.setAuthors(book
                .getAuthors()
                .stream()
                .map(authorMapper::toModel)
                .collect(Collectors.toSet()));

        for (Author author: updatedEntity.getAuthors()) {
            author.getBooks().add(updatedEntity);
        }

        updatedEntity = bookRepository.save(updatedEntity);

        return bookMapper.toDTO(updatedEntity);
    }
}
