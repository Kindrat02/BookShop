package com.serve.mentorship.mapper;

import com.serve.mentorship.dto.ReviewDTO;
import com.serve.mentorship.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDTO toDTO(Review model);
    Review toModel(ReviewDTO dto);
}
