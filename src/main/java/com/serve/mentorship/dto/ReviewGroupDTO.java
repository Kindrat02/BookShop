package com.serve.mentorship.dto;

import com.serve.mentorship.entity.Customer;

import java.time.YearMonth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewGroupDTO {
    private Long reviewCount;
    private Long totalRating;
    private CustomerDTO customer;
    private YearMonth period;
}
