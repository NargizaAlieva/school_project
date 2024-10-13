package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.entity.Employee;
import org.example.school_project.entity.Grade;
import org.example.school_project.entity.Student;
import org.example.school_project.service.*;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.GradeMapper;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private final AttendCountService attendCountService;
    private final GradeMapper gradeMapper;

    private Employee getCurrentClassTeacher() {
        return employeeService.getByUserId(userService.getCurrentUser().getId());
    }

    public Grade getTeacherGrades(Long subjectId) {
        if (getCurrentClassTeacher().getHomeGrades().size() < subjectId)
            throw new ObjectNotFoundException("Subject");
        return getCurrentClassTeacher().getHomeGrades().get((int) (subjectId-1));
    }

    public Boolean isStudentFromTeacherGrade(Long studentId) {
        if (getCurrentClassTeacher().getHomeGrades().contains(studentService.getStudentByIdEntity(studentId).getGrade()))
            return true;
        return false;
    }

    @Override
    public Map<String, GradeDto> getTeacherGradesList() {
        Map<String, GradeDto> studentDtoMap = new HashMap<>();
        for (int i = 1; i <= getCurrentClassTeacher().getHomeGrades().size(); i++) {
            String gradeIndex = "Grade: " + i;
            GradeDto gradeDto = gradeMapper.entityToDto(getTeacherGrades((long) i));
            studentDtoMap.put(gradeIndex, gradeDto);
        }
        return studentDtoMap;
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
        Grade grade = getTeacherGrades(gradeId);
        return studentService.getAllStudentByGrade(grade.getId());
    }

    @Override
    public List<StudentDto> getAllStudentsFromClass() {
        return studentService.getAllStudentByGrades(employeeService.getHomeClassesId());
    }

    @Override
    public List<ReviewDto> getReviewByStudentId(Long id) {
        if (isStudentFromTeacherGrade(id))
            return reviewService.getReviewByStudentId(id);
        throw new ObjectNotFoundException("Student from home grade");
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
    public Map<String, Double> getAvgMarkByGradeStudentQuarter(Integer quarter, Long studentId) {
        if (isStudentFromTeacherGrade(studentId)) {
            Student student = studentService.getStudentByIdEntity(studentId);
            return averageMarkService.getAvgMarkByGradeStudentQuarter(quarter, student.getGrade().getId(), studentId);
        }
        throw new ObjectNotFoundException("Student");
    }

    @Override
    public Map<String, Double> getAvgMarkBySubjectGradeStudent(Long subjectId, Long studentId) {
        if (isStudentFromTeacherGrade(studentId)) {
            Student student = studentService.getStudentByIdEntity(studentId);
            return averageMarkService.getAvgMarkBySubjectGradeStudent(subjectId, student.getGrade().getId(), studentId);
        }
        throw new ObjectNotFoundException("Student");
    }

    @Override
    public Map<String, Double> getAvgMarkByGradeStudent(Long studentId) {
        if (isStudentFromTeacherGrade(studentId)) {
            Student student = studentService.getStudentByIdEntity(studentId);
            return averageMarkService.getAvgMarkByGradeStudent(student.getGrade().getId(), studentId);
        }
        throw new ObjectNotFoundException("Student");
    }

    @Override
    public Map<String, Double> getAvgMarkByGradeQuarter(Integer quarter, Long gradeId) {
        Grade grade = getTeacherGrades(gradeId);
        return averageMarkService.getAvgMarkByGradeQuarter(quarter, grade.getId());
    }

    @Override
    public Map<String, Double> getAvgMarkByGrade(Long gradeId) {
        Grade grade = getTeacherGrades(gradeId);
        return averageMarkService.getAvgMarkByGrade(grade.getId());
    }

    @Override
    public Map<String, Double> getAvgMarks() {
        Map<String, Double> markMap = new HashMap<>();
        for (Grade g : getCurrentClassTeacher().getHomeGrades()) {
            Collection<Double> avgMarkByGrade = getAvgMarkByGrade(g.getId()).values();

            String gradeName = "Grade: " + g.getTitle();
            Double avgMark = averageMarkService.countAvgByDouble(avgMarkByGrade);
            markMap.put(gradeName, avgMark);
        }
        return markMap;
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
    public Map<String, Double> getAttendByQuarterGradeStudent(Integer quarter, Long gradeId, Long studentId) {
        if (isStudentFromTeacherGrade(studentId)) {
            Grade grade = getTeacherGrades(gradeId);
            return attendCountService.getAttendByQuarterGradeStudent(quarter, grade.getId(), studentId);
        }
        throw new ObjectNotFoundException("Student from home grade");
    }

    @Override
    public Map<String, Double> getAttendBySubjectGradeStudent(Long subjectId, Long gradeId, Long studentId) {
        if (isStudentFromTeacherGrade(studentId)) {
            Grade grade = getTeacherGrades(gradeId);
            return attendCountService.getAttendBySubjectGradeStudent(subjectId, grade.getId(), studentId);
        }
        throw new ObjectNotFoundException("Student from home grade");
    }

    @Override
    public Map<String, Double> getAttendByGradeStudent(Long gradeId, Long studentId) {
        if (isStudentFromTeacherGrade(studentId)) {
            Grade grade = getTeacherGrades(gradeId);
            return attendCountService.getAttendByGradeStudent(grade.getId(), studentId);
        }
        throw new ObjectNotFoundException("Student from home grade");
    }

    @Override
    public Map<String, Double> getAttendByQuarterGrade(Integer quarter, Long gradeId) {
        Grade grade = getTeacherGrades(gradeId);
        return attendCountService.getAttendByQuarterGrade(quarter, grade.getId());
    }

    @Override
    public Map<String, Double> getAttendByGrade(Long gradeId) {
        Grade grade = getTeacherGrades(gradeId);
        return attendCountService.getAttendByGrade(grade.getId());
    }

    @Override
    public Map<String, Double> getAttendGrades() {
        Map<String, Double> markMap = new HashMap<>();
        for (Grade g : getCurrentClassTeacher().getHomeGrades()) {
            Collection<Double> attendByGrade = getAttendByGrade(g.getId()).values();

            String gradeName = "Grade: " + g.getTitle();
            Double trueAttend = attendCountService.getTrueAttendCount(attendByGrade);
            markMap.put(gradeName, trueAttend);
        }
        return markMap;
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
