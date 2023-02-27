package com.serve.mentorship.repository;

import com.serve.mentorship.entity.Review;
import com.serve.mentorship.entity.ReviewGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("SELECT new com.serve.mentorship.entity.ReviewGroup(COUNT(rw), SUM(rw.rating), rw.customer.id, YEAR(rw.createdAt), MONTH(rw.createdAt)) " +
            "FROM Review rw " +
            "GROUP BY rw.customer, MONTH(rw.createdAt), YEAR(rw.createdAt)")
    List<ReviewGroup> getReviewsCountByUserAndDate();
}
