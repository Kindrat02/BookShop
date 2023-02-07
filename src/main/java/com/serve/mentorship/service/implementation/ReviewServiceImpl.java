package com.serve.mentorship.service.implementation;

import com.serve.mentorship.dto.ReviewDTO;
import com.serve.mentorship.mapper.ReviewMapper;
import com.serve.mentorship.repository.ReviewRepository;
import com.serve.mentorship.service.ReviewService;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository
                .findAll()
                .stream()
                .map(reviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDTO getReviewById(Integer id) throws NotFoundException {
        return reviewMapper.toDTO(
                reviewRepository
                .findById(id)
                .orElseThrow(NotFoundException::new));
    }
}
