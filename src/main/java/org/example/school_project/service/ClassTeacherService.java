package org.example.school_project.service;

import org.example.school_project.dto.*;

import java.util.List;

public interface ClassTeacherService {
    AssignmentDto createAssignment(AssignmentDtoRequest assignmentDtoRequest);
    AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest);
    AssignmentDto markAsDone(Long id);
    List<AssignmentDto> getAllAssignment();
    List<AssignmentDto> getAllUndoneAssignment();
    List<AssignmentDto> getAllDoneAssFromClassRepresent();
    List<ReviewDto> getReviewByClassRepresent();

    StudentDto chooseClassRepresentative(Long id);
    List<StudentDto> studentsFromClass();

    List<ReviewDto> getReviewByStudentId(Long id);
    List<ReviewDto> getReviewByAuthorId(Long id);
    ReviewDto createReview(ReviewDtoRequest reviewDtoRequest);
    ReviewDto updateReview(ReviewDtoRequest reviewDtoRequest);
}
