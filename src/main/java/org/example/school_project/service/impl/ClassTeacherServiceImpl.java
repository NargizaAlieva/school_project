package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.entity.Employee;
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
    private final UserService userService;
    private final MarkService markService;
    private final AttendanceService attendanceService;

    private Employee getCurrentClassTeacher() {
        return employeeService.getByUserId(userService.getCurrentUser().getId());
    }

    @Override
    public AssignmentDto createAssignment(AssignmentDtoRequest assignmentDtoRequest) {
        return assignmentService.createAssigment(assignmentDtoRequest, getCurrentClassTeacher().getId());
    }

    @Override
    public AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest) {
        return assignmentService.updateAssignment(assignmentDtoRequest, getCurrentClassTeacher().getId());
    }

    @Override
    public AssignmentDto deleteAssignment(Long id) {
        return assignmentService.deleteAssignment(id, getCurrentClassTeacher().getId());
    }

    @Override
    public AssignmentDto restoreAssignment(Long id) {
        return assignmentService.restoreAssignment(id, getCurrentClassTeacher().getId());
    }

    @Override
    public List<AssignmentDto> getAllAssignmentByAuthor(){
        return assignmentService.getAllAssignmentByAuthor(getCurrentClassTeacher().getId());
    }

    @Override
    public AssignmentDto markAsDone(Long id) {
        return assignmentService.markAsDone(id, getCurrentClassTeacher().getUser().getId());
    }

    @Override
    public List<AssignmentDto> getAllAssignment() {
        return assignmentService.getAllAssignmentFromReceiver(getCurrentClassTeacher().getUser().getId());
    }

    @Override
    public List<AssignmentDto> getAllUndoneAssignment() {
        return assignmentService.getAllUndoneAssignment(getAllAssignment());
    }

    @Override
    public List<AssignmentDto> getAllUndoneAssigment(){
        return assignmentService.getAllUndoneAssignment(getAllAssignmentByAuthor());
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

    @Override
    public ReviewDto deleteReview(Long id) {
        return reviewService.deleteReview(id);
    }

    @Override
    public ReviewDto restoreReview(Long id) {
        return reviewService.restoreReview(id);
    }

    @Override
    public MarkDto createMark(MarkDtoRequest markDtoRequest) {
        return markService.createMark(markDtoRequest);
    }

    @Override
    public MarkDto updateMark(MarkDtoRequest markDtoRequest) {
        return markService.updateMark(markDtoRequest);
    }

    @Override
    public AttendanceDto createAttendance(AttendanceDtoRequest attendanceDtoRequest) {
        return attendanceService.createAttendance(attendanceDtoRequest);
    }

    @Override
    public AttendanceDto updateAttendance(AttendanceDtoRequest attendanceDtoRequest) {
        return attendanceService.updateAttendance(attendanceDtoRequest);
    }
}
