package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.ReviewDto;
import org.example.school_project.dto.ReviewDtoRequest;
import org.example.school_project.entity.Review;
import org.example.school_project.repository.ReviewRepository;
import org.example.school_project.service.FileService;
import org.example.school_project.service.ReviewService;
import org.example.school_project.util.exception.AlreadyExistException;
import org.example.school_project.util.exception.DontHaveAccessException;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.ReviewMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final FileService fileService;

    public List<Review> getAllReview() {
        return reviewRepository.findAll();
    }
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review getByIdReviewEntity(Long id) {
        return reviewRepository.findById(id).orElseThrow(() ->  new ObjectNotFoundException("Parent"));
    }

    @Override
    public ReviewDto getByIdReview(Long id) {
        return reviewMapper.entityToDto(getByIdReviewEntity(id));
    }

    @Override
    public List<ReviewDto> getReviewByStudentId(Long id) {
        List<Review> reviewDtoList = new ArrayList<>();
        for (Review r : getAllReview())
            if (r.getStudentReview().getId().equals(id)) reviewDtoList.add(r);
        return reviewMapper.entityToDtoList(reviewDtoList);
    }

    @Override
    public List<ReviewDto> getReviewByAuthorId(Long id) {
        List<Review> reviewDtoList = new ArrayList<>();
        for (Review r : getAllReview())
            if (r.getAuthorReview().getId().equals(id)) reviewDtoList.add(r);
        return reviewMapper.entityToDtoList(reviewDtoList);
    }

    @Override
    public List<ReviewDto> getReviewByAuthorId(List<Long> ids) {
        List<Review> reviewDtoList = new ArrayList<>();
        for (Review r : getAllReview())
            for (Long id : ids)
                if (r.getAuthorReview().getId().equals(id)) reviewDtoList.add(r);
        return reviewMapper.entityToDtoList(reviewDtoList);
    }

    @Override
    public ReviewDto createReview(ReviewDtoRequest reviewDtoRequest, Long authorId) {
        fileService.createReviewToDocx(reviewDtoRequest);
        reviewDtoRequest.setAuthorId(authorId);
        if (reviewRepository.existsById(reviewDtoRequest.getId()))
            throw new AlreadyExistException("Review", "'id'");
        return reviewMapper.entityToDto(save(reviewMapper.dtoToEntity(reviewDtoRequest)));
    }

    @Override
    public ReviewDto updateReview(ReviewDtoRequest reviewDtoRequest, Long authorId) {
        reviewDtoRequest.setAuthorId(authorId);
        Review oldReview = reviewMapper.dtoToEntity(reviewDtoRequest);
        Review newReview = getByIdReviewEntity(reviewDtoRequest.getId());

        if (!oldReview.getAuthorReview().getId().equals(newReview.getAuthorReview().getId()))
            throw new DontHaveAccessException();

        newReview.setId(oldReview.getId());
        newReview.setReview(oldReview.getReview());
        newReview.setCreationDate(oldReview.getCreationDate());
        newReview.setAuthorReview(oldReview.getAuthorReview());
        newReview.setStudentReview(oldReview.getStudentReview());
        return reviewMapper.entityToDto(save(newReview));
    }

    @Override
    public ReviewDto deleteReview(Long id, Long authorId) {
        Review review = getByIdReviewEntity(id);
        if (!review.getAuthorReview().getId().equals(authorId))
            throw new DontHaveAccessException();
        review.setIsActive(false);
        return reviewMapper.entityToDto(save(review));
    }

    @Override
    public ReviewDto restoreReview(Long id, Long authorId) {
        Review review = getByIdReviewEntity(id);
        if (!review.getAuthorReview().getId().equals(authorId))
            throw new DontHaveAccessException();
        review.setIsActive(true);
        return reviewMapper.entityToDto(save(review));
    }
}
