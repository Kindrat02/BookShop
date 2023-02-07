package com.serve.mentorship.mapper;

import com.serve.mentorship.dto.BookDTO;
import com.serve.mentorship.entity.Book;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDTO(Book model);
    Book toModel(BookDTO dto);
}
