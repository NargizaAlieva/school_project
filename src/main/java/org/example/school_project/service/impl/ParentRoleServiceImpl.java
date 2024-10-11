package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.service.*;
import org.example.school_project.util.mapper.HomeworkToDoMapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ParentRoleServiceImpl implements ParentRoleService {
    private final UserService userService;
    private final MarkService markService;
    private final HomeworkService homeworkService;
    private final ScheduleService scheduleService;
    private final LessonService lessonService;
    private final ReviewService reviewService;
    private final StudentService studentService;
    private final HomeworkToDoMapping homeworkToDoMapping;
    private final AttendanceService attendanceService;

    @Override
    public StudentDto createStudent(StudentDtoRequest studentDtoRequest) {
        return studentService.createStudent(studentDtoRequest);
    }

    @Override
    public List<ScheduleDto> getStudentSchedule(Long studentId) {
        return scheduleService.getAllScheduleByStudent(studentId);
    }

    @Override
    public List<MarkDto> getAllMark(Long studentId) {
        return markService.getAllMarkByStudent(studentId);    }

    @Override
    public List<MarkDto> getAllMarkBySubject(Long studentId, Long subject) {
        return null;
    }

    @Override
    public List<MarkDto> getAllMarkBySubjectQuarter(Long studentId, String year, Long subjectId, Integer quarter) {
        return null;
    }

    @Override
    public List<AttendanceDto> getAttendance(Long studentId) {
        return attendanceService.getAllAttendanceStudent(lessonService.getAllLesson(), studentId);
    }

    @Override
    public List<AttendanceDto> getAttendanceSubject(Long studentId, Long subjectId) {
        return null;
    }

    @Override
    public List<AttendanceDto> getAttendanceSubject(Long studentId, Long subjectId, String year) {
        return null;
    }

    @Override
    public List<AttendanceDto> getAttendanceSubject(Long studentId, Long subjectId, String year, Integer quarter) {
        return null;
    }


    @Override
    public List<HomeworkDto> getAllHomework(Long studentId) {
        return homeworkService.getHwByStudent(studentId);
    }

    @Override
    public List<HomeworkToDoDto> getAllUndoneHomework(Long studentId) {
        return homeworkToDoMapping.entityToDtoList(lessonService.getUndoneHwByStudent(getAllHomework(studentId), studentId));
    }

    @Override
    public List<HomeworkDto> getAllHomeworkSubject(Long studentId, Long subjectId) {
        return homeworkService.getHwByStudentSubject(studentId, subjectId);
    }

    @Override
    public List<HomeworkToDoDto> getAllUndoneHomework(Long studentId, Long subjectId) {
        return homeworkToDoMapping.entityToDtoList(lessonService.getUndoneHwByStudent(getAllHomework(studentId), studentId, subjectId));
    }

    @Override
    public List<ReviewDto> getStudentReview(Long studentId) {
        return reviewService.getReviewByStudentId(studentId);
    }

    @Override
    public void leaveSchool(Long studentId) {
        userService.deleteUser(studentService.getStudentById(studentId).getUser().getId());
    }
}
