package com.serve.mentorship.service;

import com.serve.mentorship.dto.ReviewDTO;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getAllReviews();
    ReviewDTO getReviewById(Integer id) throws NotFoundException;
}
