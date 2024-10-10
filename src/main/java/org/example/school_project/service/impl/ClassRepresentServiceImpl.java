package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.AssignmentDto;
import org.example.school_project.dto.ReviewDto;
import org.example.school_project.dto.ReviewDtoRequest;
import org.example.school_project.entity.User;
import org.example.school_project.service.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassRepresentServiceImpl implements ClassRepresentService {
    private final AssignmentService assignmentService;
    private final ReviewService reviewService;
    private final StudentService studentService;
    private final UserService userService;

    private User getCurrentUser() {
        return userService.getCurrentUser();
    }
    @Override
    public AssignmentDto markAsDone(Long id) {
        return assignmentService.markAsDone(id, getCurrentUser().getId());
    }

    @Override
    public List<AssignmentDto> getAllAssignment() {
        return assignmentService.getAllAssignmentFromReceiver(getCurrentUser().getId());
    }

    @Override
    public List<AssignmentDto> getAllUndoneAssignment() {
        return assignmentService.getAllUndoneAssignment(getAllAssignment());
    }

    @Override
    public ReviewDto createReview(ReviewDtoRequest reviewDtoRequest) {
        return reviewService.createReview(reviewDtoRequest);
    }

    @Override
    public ReviewDto updateReview(ReviewDtoRequest reviewDtoRequest) {
        return reviewService.updateReview(reviewDtoRequest);
    }

    @Override
    public ReviewDto deleteReview(Long id) {
        return reviewService.deleteReview(id);
    }

    @Override
    public ReviewDto restoreReview(Long id) {
        return reviewService.restoreReview(id);
    }
}
