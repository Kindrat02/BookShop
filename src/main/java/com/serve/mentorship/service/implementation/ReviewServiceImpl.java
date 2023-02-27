package com.serve.mentorship.service.implementation;

import com.serve.mentorship.dto.ReviewDTO;
import com.serve.mentorship.dto.ReviewGroupDTO;
import com.serve.mentorship.entity.ReviewGroup;
import com.serve.mentorship.mapper.ReviewGroupMapper;
import com.serve.mentorship.mapper.ReviewMapper;
import com.serve.mentorship.repository.ReviewRepository;
import com.serve.mentorship.service.ReviewService;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final ReviewGroupMapper reviewGroupMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper, ReviewGroupMapper reviewGroupMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.reviewGroupMapper = reviewGroupMapper;
    }

    @Override
    public List<ReviewDTO> getAllReviews(Pageable pageable) {
        return reviewRepository
                .findAll(pageable)
                .stream()
                .map(reviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReviewDTO> getReviewById(Integer id) {
        return reviewRepository
                .findById(id)
                .map(reviewMapper::toDTO);
    }

    @Override
    public List<ReviewGroupDTO> getReviewsGroupedByCustomer() {
        List<ReviewGroup> resultList = reviewRepository.finr();
        return resultList.stream()
                .map(reviewGroupMapper::toDTO)
                .collect(Collectors.toList());
    }
}
