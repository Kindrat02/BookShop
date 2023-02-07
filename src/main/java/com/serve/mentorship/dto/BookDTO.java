package com.serve.mentorship.dto;

import java.sql.Date;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Integer id;
    @Size(max = 100, message = "You have exceeded string max size")
    private String name;
    @NotNull
    private Date publishDate;
    @NotNull
    private Set<AuthorDTO> authors;
}
