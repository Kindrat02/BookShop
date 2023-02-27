package com.serve.mentorship.entity;

import com.serve.mentorship.utils.date.YearMonth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewGroup {
    private Long reviewCount;
    private Long totalRating;
    private Integer customerId;
    private Integer year;
    private Integer month;
}
