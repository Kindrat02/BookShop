package com.serve.mentorship.controller;

import com.serve.mentorship.dto.ReviewDTO;
import com.serve.mentorship.dto.ReviewGroupDTO;
import com.serve.mentorship.service.ReviewService;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDTO>> getAllReviews(@PositiveOrZero @RequestParam(defaultValue = "0") Integer page,
                                                         @Positive @RequestParam(defaultValue = "3") Integer size) {
        List<ReviewDTO> reviewList = reviewService.getAllReviews(PageRequest.of(page, size));
        return new ResponseEntity(reviewList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@Positive @PathVariable(name = "id") Integer reviewId) throws NotFoundException {
        ReviewDTO review = reviewService.getReviewById(reviewId).orElseThrow(NotFoundException::new);
        return new ResponseEntity<>(review, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/grouped-reviews")
    public ResponseEntity<List<ReviewGroupDTO>> getReviewsGroupedByCustomer() {
        List<ReviewGroupDTO> result = reviewService.getReviewsGroupedByCustomer();
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity deleteReview(@Positive @PathVariable(name = "id") Integer reviewId) {
        return new ResponseEntity(reviewService.deleteReview(reviewId) ? HttpStatus.OK : HttpStatus.GONE);
    }

    @PostMapping("/review")
    public ResponseEntity<ReviewDTO> createReview(@Valid @RequestBody ReviewDTO review) {
        ReviewDTO newReview = reviewService.createReview(review);
        return new ResponseEntity<>(newReview, new HttpHeaders(), HttpStatus.OK);
    }
}
