package com.serve.mentorship.dto;

import com.serve.mentorship.utils.validator.Email;

import java.util.Calendar;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private Integer id;
    @Size(max = 100, message = "You have exceeded string max size")
    private String name;
    @Size(max = 100, message = "You have exceeded string max size")
    private String surname;
    @Email
    private String email;
    @NotNull
    private Calendar birthDate;
}
