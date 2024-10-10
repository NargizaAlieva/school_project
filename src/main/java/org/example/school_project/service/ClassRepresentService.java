package org.example.school_project.service;

import org.example.school_project.dto.AssignmentDto;
import org.example.school_project.dto.ReviewDto;
import org.example.school_project.dto.ReviewDtoRequest;

import java.util.List;

public interface ClassRepresentService {

    AssignmentDto markAsDone(Long id);

    List<AssignmentDto> getAllAssignment();

    List<AssignmentDto> getAllUndoneAssignment();

    ReviewDto createReview(ReviewDtoRequest reviewDtoRequest);

    ReviewDto updateReview(ReviewDtoRequest reviewDtoRequest);
}
