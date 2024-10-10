package org.example.school_project.service;

import org.example.school_project.dto.*;

import java.util.List;

public interface ClassTeacherService {
    AssignmentDto createAssignment(AssignmentDtoRequest assignmentDtoRequest);
    AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest);

    AssignmentDto deleteAssignment(Long id);

    AssignmentDto restoreAssignment(Long id);

    List<AssignmentDto> getAllAssignmentByAuthor();

    AssignmentDto markAsDone(Long id);
    List<AssignmentDto> getAllAssignment();
    List<AssignmentDto> getAllUndoneAssignment();

    List<AssignmentDto> getAllUndoneAssigment();

    List<AssignmentDto> getAllDoneAssFromClassRepresent();

    StudentDto chooseClassRepresentative(Long id);
    List<StudentDto> studentsFromClass();

    List<ReviewDto> getReviewByStudentId(Long id);
    List<ReviewDto> getReviewByAuthorId(Long id);
    List<ReviewDto> getReviewByClassRepresent();
    ReviewDto createReview(ReviewDtoRequest reviewDtoRequest);
    ReviewDto updateReview(ReviewDtoRequest reviewDtoRequest);
    ReviewDto deleteReview(Long id);
    ReviewDto restoreReview(Long id);

    MarkDto createMark(MarkDtoRequest markDtoRequest);

    MarkDto updateMark(MarkDtoRequest markDtoRequest);

    AttendanceDto createAttendance(AttendanceDtoRequest attendanceDtoRequest);

    AttendanceDto updateAttendance(AttendanceDtoRequest attendanceDtoRequest);
}
