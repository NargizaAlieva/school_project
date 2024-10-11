package org.example.school_project.service;

import org.example.school_project.dto.*;

import java.util.List;

public interface ClassTeacherService {
    AssignmentDto createAssignment(AssignmentDtoRequest assignmentDtoRequest);
    AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest);
    AssignmentDto deleteAssignment(Long id);
    AssignmentDto restoreAssignment(Long id);

    List<AssignmentDto> getAllAssignmentFromClassRepresent();
    List<AssignmentDto> getAllUndoneAssFromClassRepresent();
    List<AssignmentDto> getAllDoneAssFromClassRepresent();

    AssignmentDto markAsDone(Long id);
    List<AssignmentDto> getAllAssFromDean();
    List<AssignmentDto> getAllUndoneAssFromDean();

    StudentDto chooseClassRepresentative(Long id);

    List<StudentDto> getAllStudentsGrade(Long gradeId);

    List<StudentDto> getAllStudentsFromClass();

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

    List<StudentDto> getAllStudentHomeGrade(Long gradeId);

    MessageDto sendToParentByStudentId(Long id, MessageDtoRequest messageDtoRequest);

    void sendMessageForGradeStudentsByGrade(Long gradeId, MessageDtoRequest messageDtoRequest);

    void sendMessageForGradeParentsByGrade(Long gradeId, MessageDtoRequest messageDtoRequest);

    void sendMessageForGradeStudents(MessageDtoRequest messageDtoRequest);

    void sendMessageForGradeParent(MessageDtoRequest messageDtoRequest);
}
