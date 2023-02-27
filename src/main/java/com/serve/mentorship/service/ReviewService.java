package com.serve.mentorship.service;

import com.serve.mentorship.dto.ReviewDTO;
import com.serve.mentorship.dto.ReviewGroupDTO;

import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<ReviewDTO> getAllReviews(Pageable pageable);
    Optional<ReviewDTO> getReviewById(Integer id);
    List<ReviewGroupDTO> getReviewsGroupedByCustomer();
}
