package com.serve.mentorship.controller;

import com.serve.mentorship.dto.ReviewDTO;
import com.serve.mentorship.service.ReviewService;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import jakarta.validation.constraints.Positive;

@Validated
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/review")
    public ReviewDTO getReviewById(@Positive @RequestParam(name = "id") Integer reviewId) throws NotFoundException {
        return reviewService.getReviewById(reviewId);
    }
}
