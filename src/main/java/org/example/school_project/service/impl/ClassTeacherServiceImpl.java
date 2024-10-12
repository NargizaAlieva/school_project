package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.entity.Employee;
import org.example.school_project.entity.Grade;
import org.example.school_project.service.*;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClassTeacherServiceImpl implements ClassTeacherService {
    private final AssignmentService assignmentService;
    private final StudentService studentService;
    private final ReviewService reviewService;
    private final EmployeeService employeeService;
    private final UserService userService;
    private final MarkService markService;
    private final MessageService messageService;
    private final AttendanceService attendanceService;
    private final AverageMarkService averageMarkService;

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
    public List<AssignmentDto> getAllAssignmentFromClassRepresent(){
        return assignmentService.getAllAssignmentByAuthor(getCurrentClassTeacher().getId());
    }

    @Override
    public List<AssignmentDto> getAllDoneAssFromClassRepresent() {
        return assignmentService.getAllDoneAssignment(getAllAssignmentFromClassRepresent());
    }

    @Override
    public List<AssignmentDto> getAllUndoneAssFromClassRepresent(){
        return assignmentService.getAllUndoneAssignment(getAllAssignmentFromClassRepresent());
    }

    @Override
    public AssignmentDto markAsDone(Long id) {
        return assignmentService.markAsDone(id, getCurrentClassTeacher().getUser().getId());
    }

    @Override
    public List<AssignmentDto> getAllAssFromDean() {
        return assignmentService.getAllAssignmentFromReceiver(getCurrentClassTeacher().getUser().getId());
    }
    @Override
    public List<AssignmentDto> getAllUndoneAssFromDean() {
        return assignmentService.getAllUndoneAssignment(getAllAssFromDean());
    }

    @Override
    public StudentDto chooseClassRepresentative(Long id) {
        return studentService.chooseClassRepresentative(id);
    }

    @Override
    public List<StudentDto> getAllStudentsGrade(Long gradeId) {
        return studentService.getAllStudentByGrade(gradeId);
    }

    @Override
    public List<StudentDto> getAllStudentsFromClass() {
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
        return reviewService.createReview(reviewDtoRequest, getCurrentClassTeacher().getId());
    }

    @Override
    public ReviewDto updateReview(ReviewDtoRequest reviewDtoRequest) {
        return reviewService.updateReview(reviewDtoRequest, getCurrentClassTeacher().getId());
    }

    @Override
    public ReviewDto deleteReview(Long id) {
        return reviewService.deleteReview(id, getCurrentClassTeacher().getId());
    }

    @Override
    public ReviewDto restoreReview(Long id) {
        return reviewService.restoreReview(id, getCurrentClassTeacher().getId());
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
    public Map<String, Double> getAvgMarkBySubjectGradeQuarter(Long subjectId, Long gradeId, Integer quarter) {
        return averageMarkService.getAvgMarkBySubjectGradeQuarter(subjectId, gradeId, quarter);
    }

    @Override
    public Map<String, Double> getAvgMarkBySubjectGrade(Long gradeId, Long subjectId) {
        return averageMarkService.getAvgMarkBySubjectGrade(subjectId, gradeId);
    }

    @Override
    public Map<String, Double> getAvgMarkBySubject(Long subjectId) {
        return averageMarkService.getAvgMarkBySubject(subjectId);
    }

    @Override
    public AttendanceDto createAttendance(AttendanceDtoRequest attendanceDtoRequest) {
        return attendanceService.createAttendance(attendanceDtoRequest);
    }

    @Override
    public AttendanceDto updateAttendance(AttendanceDtoRequest attendanceDtoRequest) {
        return attendanceService.updateAttendance(attendanceDtoRequest);
    }

    @Override
    public List<StudentDto> getAllStudentHomeGrade(Long gradeId) {
        for (Grade g : getCurrentClassTeacher().getHomeGrades())
            if (g.getId().equals(gradeId))
                return getAllStudentsGrade(g.getId());
        throw new ObjectNotFoundException("Home Grade");
    }

    @Override
    public MessageDto sendToParentByStudentId(Long studentId, MessageDtoRequest messageDtoRequest) {
        messageDtoRequest.setReceiverId(studentService.getStudentById(studentId).getParentId());
        return messageService.createMessage(messageDtoRequest);
    }

    @Override
    public void sendMessageForGradeStudentsByGrade(Long gradeId, MessageDtoRequest messageDtoRequest) {
        messageService.sendMessageForGradeStudents(getAllStudentHomeGrade(gradeId), messageDtoRequest);
    }

    @Override
    public void sendMessageForGradeParentsByGrade(Long gradeId, MessageDtoRequest messageDtoRequest) {
        messageService.sendMessageForGradeParent(getAllStudentHomeGrade(gradeId), messageDtoRequest);
    }

    @Override
    public void sendMessageForGradeStudents(MessageDtoRequest messageDtoRequest) {
        messageService.sendMessageForGradeStudents(getAllStudentsFromClass(), messageDtoRequest);
    }

    @Override
    public void sendMessageForGradeParent(MessageDtoRequest messageDtoRequest) {
        messageService.sendMessageForGradeParent(getAllStudentsFromClass(), messageDtoRequest);
    }
}
