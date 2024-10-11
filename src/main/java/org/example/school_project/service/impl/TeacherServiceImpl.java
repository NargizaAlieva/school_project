package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.entity.Employee;
import org.example.school_project.service.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final ScheduleService scheduleService;
    private final ReviewService reviewService;
    private final HomeworkService homeworkService;
    private final MarkService markService;
    private final AttendanceService attendanceService;
    private final GradeService gradeService;
    private final StudentService studentService;
    private final EmployeeService employeeService;
    private final UserService userService;
    private final LessonService lessonService;
    private final MessageService messageService;

    public Employee getCurrentTeacher() {
        return employeeService.getByUserId(userService.getCurrentUser().getId());
    }

    @Override
    public List<ScheduleDto> getTeacherSchedule() {
        return scheduleService.filterActiveSchedule(scheduleService.getAllScheduleByTeacher(getCurrentTeacher().getId()));
    }

    @Override
    public ReviewDto createReview(ReviewDtoRequest reviewDtoRequest) {
        return reviewService.createReview(reviewDtoRequest, getCurrentTeacher().getUser().getId());
    }

    @Override
    public ReviewDto updateReview(ReviewDtoRequest reviewDtoRequest) {
        return reviewService.updateReview(reviewDtoRequest, getCurrentTeacher().getUser().getId());
    }

    @Override
    public LessonDto createLesson(LessonDtoRequest lessonDtoRequest) {
        return lessonService.createLesson(lessonDtoRequest);
    }

    @Override
    public LessonDto updateLesson(LessonDtoRequest lessonDtoRequest) {
        return lessonService.updateLesson(lessonDtoRequest);
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

    @Override
    public List<MarkDto> getMarkTeacher() {
        return markService.filterMark(lessonService.getAllLessonByTeacherId(
                lessonService.getAllLesson(), getCurrentTeacher().getId()));
    }

    @Override
    public List<MarkDto> getMarkTeacherByGrade(Long gradeId) {
        return markService.filterMark(lessonService.getAllLessonByGradeId(
                lessonService.getAllLessonByTeacherId(
                        lessonService.getAllLesson(), getCurrentTeacher().getId()), gradeId));
    }

    @Override
    public List<GradeDto> getAllGrade() {
        return gradeService.getAllGrade();
    }

    @Override
    public Set<GradeDto> getTeacherGrade() {
        return gradeService.getAllTeacherGrade(lessonService.getAllLessonByTeacherId(
                lessonService.getAllLesson(), getCurrentTeacher().getId()));
    }

    @Override
    public List<StudentDto> getAllStudentByGrade(Long gradeId) {
        return studentService.getAllStudentByGrade(gradeId);
    }

    @Override
    public List<HomeworkDto> getAllHwByTeacher() {
        return homeworkService.convertToHw(lessonService.getAllLessonByTeacherId(
                lessonService.getAllLesson(), getCurrentTeacher().getId()));
    }

    @Override
    public List<HomeworkDto> getUncheckedHw() {
        return homeworkService.getUncheckedHw(getAllHwByTeacher());
    }

    @Override
    public List<HomeworkDto> getAllUncheckedHwByTeacherLesson(Long lessonId) {
        return homeworkService.getUncheckedHw(homeworkService.getHwByLesson(lessonId));
    }
    @Override
    public HomeworkDto markHw(Long hwId, Integer mark, String hwReview) {
        return homeworkService.leaveHwMarkReview(hwId, mark, hwReview);
    }

    @Override
    public void sendToParentByStudentId(Long studentId, MessageDtoRequest messageDtoRequest) {
        messageDtoRequest.setReceiverId(studentService.getStudentById(studentId).getParentId());
        messageService.createMessage(messageDtoRequest);
    }
}
