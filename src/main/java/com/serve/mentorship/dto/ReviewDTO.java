package com.serve.mentorship.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Integer id;
    @Size(max = 100, message = "You have exceeded string max size")
    private String text;
    @NotNull
    private CustomerDTO customer;
}
