package org.example.school_project.service;

import org.example.school_project.dto.ReviewDto;
import org.example.school_project.dto.ReviewDtoRequest;
import org.example.school_project.entity.Review;

import java.util.List;

public interface ReviewService {
    Review getByIdReviewEntity(Long id);
    ReviewDto getByIdReview(Long id);

    ReviewDto createReview(ReviewDtoRequest reviewDtoRequest, Long authorId);
    ReviewDto updateReview(ReviewDtoRequest reviewDtoRequest,  Long authorId);

    List<ReviewDto> getReviewByStudentId(Long id);
    List<ReviewDto> getReviewByAuthorId(Long id);
    List<ReviewDto> getReviewByAuthorId(List<Long> id);

    ReviewDto deleteReview(Long id, Long authorId);
    ReviewDto restoreReview(Long id, Long authorId);
}
