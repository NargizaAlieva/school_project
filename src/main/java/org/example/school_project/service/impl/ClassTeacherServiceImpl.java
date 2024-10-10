package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.service.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassTeacherServiceImpl implements ClassTeacherService {
    private final AssignmentService assignmentService;
    private final StudentService studentService;
    private final ReviewService reviewService;
    private final EmployeeService employeeService;

    @Override
    public AssignmentDto createAssignment(AssignmentDtoRequest assignmentDtoRequest) {
        return assignmentService.createAssigment(assignmentDtoRequest);
    }

    @Override
    public AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest) {
        return assignmentService.updateAssignment(assignmentDtoRequest);
    }

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
    public List<AssignmentDto> getAllDoneAssFromClassRepresent() {
        return assignmentService.getAllDoneAssignmentFrom(employeeService.getHomeClassesId());
    }

    @Override
    public StudentDto chooseClassRepresentative(Long id) {
        return studentService.chooseClassRepresentative(id);
    }

    @Override
    public List<StudentDto> studentsFromClass() {
        return studentService.getAllStudentByGrades(employeeService.getHomeClassesId());
    }

    @Override
    public List<ReviewDto> getReviewByStudentId(Long id) {
        return reviewService.getReviewByStudentId(id);
    }

    @Override
    public List<ReviewDto> getReviewByAuthorId(Long id) {
        return reviewService.getReviewByAuthorId(id);
    }

    @Override
    public List<ReviewDto> getReviewByClassRepresent() {
        List<StudentDto> gradeRepresent = studentService.getGradeRepresentative(employeeService.getHomeClassesId());
        List<Long> gradeRepresentId = new ArrayList<>();
            for (StudentDto s : gradeRepresent)
                gradeRepresentId.add(s.getUser().getId());
        return reviewService.getReviewByAuthorId(gradeRepresentId);
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
