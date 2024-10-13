package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.entity.Employee;
import org.example.school_project.entity.Subject;
import org.example.school_project.service.*;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.SubjectMapper;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private final AverageMarkService averageMarkService;
    private final SubjectMapper subjectMapper;

    public Employee getCurrentTeacher() {
        return employeeService.getByUserId(userService.getCurrentUser().getId());
    }
    public Subject getTeacherSubjects(Long subjectId) {
        List<Subject> subjectList = new ArrayList<>(getCurrentTeacher().getSubjectSet());
        if (subjectList.size() < subjectId)
            throw new ObjectNotFoundException("Subject");
        return subjectList.get((int) (subjectId-1));
    }

    @Override
    public Map<String, SubjectDto> getTeacherSubjectList() {
        Map<String, SubjectDto> studentDtoMap = new HashMap<>();
        for (int i = 1; i <= getCurrentTeacher().getSubjectSet().size(); i++) {
            String subjectIndex = "Subject: " + i;
            SubjectDto subjectDto = subjectMapper.entityToDto(getTeacherSubjects((long) i));
            studentDtoMap.put(subjectIndex, subjectDto);
        }
        return studentDtoMap;
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
        return markService.convertToMark(lessonService.getAllLessonByTeacherId(
                lessonService.getAllLesson(), getCurrentTeacher().getId()));
    }

    @Override
    public List<MarkDto> getMarkTeacherByGrade(Long gradeId) {
        return markService.convertToMark(lessonService.getAllLessonByGradeId(
                lessonService.getAllLessonByTeacherId(
                        lessonService.getAllLesson(), getCurrentTeacher().getId()), gradeId));
    }

    @Override
    public Map<String, Double> getAvgMarkBySubjectGradeQuarter(Long gradeId, Integer quarter, Long subjectId) {
        Subject getTeacerSubject = getTeacherSubjects(subjectId);
        return averageMarkService.getAvgMarkBySubjectGradeQuarter(getTeacerSubject.getId(), gradeId, quarter);
    }

    @Override
    public Map<String, Double> getAvgMarkBySubjectGrade(Long gradeId, Long subjectId) {
        Subject getTeacerSubject = getTeacherSubjects(subjectId);
        return averageMarkService.getAvgMarkBySubjectGrade(getTeacerSubject.getId(), gradeId);
    }

    @Override
    public Map<String, Double> getAvgMarkBySubject(Long subjectId) {
        Subject getTeacerSubject = getTeacherSubjects(subjectId);
        return averageMarkService.getAvgMarkBySubject(getTeacerSubject.getId());
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
