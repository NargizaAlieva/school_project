package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.entity.Student;
import org.example.school_project.service.*;
import org.example.school_project.util.mapper.HomeworkToDoMapping;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentRoleServiceImpl implements StudentRoleService {
    private final UserService userService;
    private final MarkService markService;
    private final HomeworkService homeworkService;
    private final ScheduleService scheduleService;
    private final LessonService lessonService;
    private final SubjectService subjectService;
    private final StudentService studentService;
    private final HomeworkToDoMapping homeworkToDoMapping;
    private final AttendanceService attendanceService;

    public Student getCurrentStudentId() {
        return studentService.getStudentByUserId(userService.getCurrentUser().getId());
    }

    @Override
    public List<MarkDto> getAllMark() {
        return markService.getAllMarkByStudent(getCurrentStudentId().getId());
    }

    @Override
    public List<MarkDto> getAllMarkBySubject(Long subject) {
        return markService.filterMark(lessonService.getAllSubjectGrade(subject, getCurrentStudentId().getGrade().getId()));
    }

    @Override
    public List<MarkDto> getAllMarkBySubjectQuarter(String year, Long subjectId, Integer quarter) {
        return markService.filterMark(lessonService.getAllLessonByYearSubjectId(year, subjectId, quarter), getCurrentStudentId().getId());
    }

    @Override
    public HomeworkDto sendHomework(HomeworkDtoRequest homeworkDtoRequest) {
        return homeworkService.createHw(homeworkDtoRequest);
    }

    @Override
    public List<HomeworkDto> getAllHomework() {
        return homeworkService.getHwByStudent(getCurrentStudentId().getId());
    }

    @Override
    public List<HomeworkToDoDto> getAllUndoneHomework() {
        return homeworkToDoMapping.entityToDtoList(lessonService.getUndoneHwByStudent(getAllHomework(), getCurrentStudentId().getId()));
    }

    @Override
    public List<HomeworkDto> getAllHomeworkSubject(Long subjectId) {
        return homeworkService.getHwByStudentSubject(getCurrentStudentId().getId(), subjectId);
    }

    @Override
    public List<HomeworkToDoDto> getAllUndoneHomework(Long subjectId) {
        return homeworkToDoMapping.entityToDtoList(lessonService.getUndoneHwByStudent(getAllHomework(), getCurrentStudentId().getId(),subjectId));
    }

    @Override
    public List<AttendanceDto> getAttendance() {
        return attendanceService.getAllAttendanceStudent(lessonService.getAllLesson(), getCurrentStudentId().getId());
    }

    @Override
    public List<AttendanceDto> getAttendanceSubject(Long subjectId) {
        return attendanceService.getAllAttendanceStudent(lessonService.getAllLessonBySubjectId(subjectId), getCurrentStudentId().getId());
    }

    @Override
    public List<AttendanceDto> getAttendanceSubject(Long subjectId, String year) {
        return attendanceService.getAllAttendanceStudent(lessonService.getAllLessonByYearSubjectId(year, subjectId), getCurrentStudentId().getId());
    }

    @Override
    public List<AttendanceDto> getAttendanceSubject(Long subjectId, String year, Integer quarter) {
        return attendanceService.getAllAttendanceStudent(lessonService.getAllLessonByYearSubjectId(year, subjectId, quarter), getCurrentStudentId().getId());
    }

    @Override
    public List<ScheduleDto> getStudentSchedule() {
        return scheduleService.getAllScheduleByStudent(getCurrentStudentId().getId());
    }

    @Override
    public List<LessonDto> getAllLessonByGrade() {
        return lessonService.getAllLessonByGradeId(getCurrentStudentId().getGrade().getId());
    }

    @Override
    public List<SubjectDto> getSubjectList(String year) {
        return subjectService.getSubjectSchedule(scheduleService.getAllScheduleByYear(year));
    }

    @Override
    public List<StudentDto> getClassmates() {
        return studentService.getAllStudentByGrade(getCurrentStudentId().getGrade().getId());
    }
}
