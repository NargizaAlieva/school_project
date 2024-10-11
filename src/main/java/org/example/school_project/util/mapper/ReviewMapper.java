package org.example.school_project.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.ReviewDto;
import org.example.school_project.dto.ReviewDtoRequest;
import org.example.school_project.entity.Review;
import org.example.school_project.service.StudentService;
import org.example.school_project.service.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReviewMapper {
    private final UserService userService;
    private final StudentService studentService;
    public ReviewDto entityToDto(Review review) {
        String authorFullName = review.getAuthorReview().getFirstName() + " " + review.getAuthorReview().getLastName();
        if(review.getAuthorReview().getMiddleName() != null) authorFullName += " " + review.getAuthorReview().getMiddleName();

        String studentFullName = review.getStudentReview().getUser().getFirstName() + " " + review.getStudentReview().getUser().getLastName();
        if(review.getStudentReview().getUser().getMiddleName() != null) studentFullName += " " + review.getStudentReview().getUser().getMiddleName();

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setReview(review.getReview());
        reviewDto.setCreationDate(review.getCreationDate());
        reviewDto.setAuthorId(review.getAuthorReview().getId());
        reviewDto.setAuthorName(authorFullName);
        reviewDto.setStudentId(review.getStudentReview().getId());
        reviewDto.setStudentName(studentFullName);
        reviewDto.setGradeName(review.getStudentReview().getGrade().getTitle());
        reviewDto.setIsActive(review.getIsActive());
        return reviewDto;
    }

    public List<ReviewDto> entityToDtoList(List<Review> reviews) {
        return reviews.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Review dtoToEntity(ReviewDtoRequest reviewDtoRequest) {
        Review review = new Review();
        review.setId(reviewDtoRequest.getId());
        review.setReview(reviewDtoRequest.getReview());
        review.setAuthorReview(userService.getEntityById(reviewDtoRequest.getAuthorId()));
        review.setStudentReview(studentService.getStudentByIdEntity(reviewDtoRequest.getStudentId()));

        if(reviewDtoRequest.getCreationDate() == null) review.setCreationDate(LocalDateTime.now());
        else review.setCreationDate(reviewDtoRequest.getCreationDate());
        return review;
    }
}
