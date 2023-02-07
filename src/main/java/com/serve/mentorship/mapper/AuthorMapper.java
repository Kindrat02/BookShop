package com.serve.mentorship.mapper;

import com.serve.mentorship.dto.AuthorDTO;
import com.serve.mentorship.entity.Author;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO toDTO(Author model);
    Author toModel(AuthorDTO dto);
}
