package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.AssignmentDto;
import org.example.school_project.dto.ReviewDto;
import org.example.school_project.dto.ReviewDtoRequest;
import org.example.school_project.service.AssignmentService;
import org.example.school_project.service.ClassRepresentService;
import org.example.school_project.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassRepresentServiceImpl implements ClassRepresentService {
    private final AssignmentService assignmentService;
    private final ReviewService reviewService;
    @Override
    public AssignmentDto markAsDone(Long id) {
        return assignmentService.markAsDone(id);
    }

    @Override
    public List<AssignmentDto> getAllAssignment() {
        return assignmentService.getAllAssignment();
    }

    @Override
    public List<AssignmentDto> getAllUndoneAssignment() {
        return assignmentService.getAllUndoneAssignment();
    }

    @Override
    public ReviewDto createReview(ReviewDtoRequest reviewDtoRequest) {
        return reviewService.createReview(reviewDtoRequest);
    }

    @Override
    public ReviewDto updateReview(ReviewDtoRequest reviewDtoRequest) {
        return reviewService.updateReview(reviewDtoRequest);
    }
}
