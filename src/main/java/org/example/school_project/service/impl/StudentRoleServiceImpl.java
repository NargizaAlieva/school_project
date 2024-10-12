package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.entity.Student;
import org.example.school_project.service.*;
import org.example.school_project.util.mapper.HomeworkToDoMapping;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private final AverageMarkService averageMarkService;
    private final AttendCountService attendCountService;
    public Student getCurrentStudent() {
        return studentService.getStudentByUserId(userService.getCurrentUser().getId());
    }
    @Override
    public List<MarkDto> getAllMark() {
        return markService.getAllMarkByStudent(getCurrentStudent().getId());
    }

    @Override
    public Map<String, Double> getAvgMarkByGradeStudentQuarter(Integer quarter) {
        return averageMarkService.getAvgMarkByGradeStudentQuarter(quarter, getCurrentStudent().getGrade().getId(), getCurrentStudent().getId());
    }

    @Override
    public Map<String, Double> getAvgMarkBySubjectGradeStudent(Long subjectId) {
        return averageMarkService.getAvgMarkBySubjectGradeStudent(subjectId, getCurrentStudent().getGrade().getId(), getCurrentStudent().getId());
    }

    @Override
    public Map<String, Double> getAvgMarkByGradeStudent() {
        return averageMarkService.getAvgMarkByGradeStudent(getCurrentStudent().getGrade().getId(), getCurrentStudent().getId());
    }

    @Override
    public Map<String, Double> getAttendByQuarterGradeStudent(Integer quarter) {
        return attendCountService.getAttendByQuarterGradeStudent(quarter, getCurrentStudent().getGrade().getId(), getCurrentStudent().getId());
    }

    @Override
    public Map<String, Double> getAttendBySubjectGradeStudent(Long subjectId) {
        return attendCountService.getAttendBySubjectGradeStudent(subjectId, getCurrentStudent().getGrade().getId(), getCurrentStudent().getId());
    }

    @Override
    public Map<String, Double> getAttendByGradeStudent() {
        return attendCountService.getAttendByGradeStudent(getCurrentStudent().getGrade().getId(), getCurrentStudent().getId());
    }

    @Override
    public HomeworkDto sendHomework(HomeworkDtoRequest homeworkDtoRequest) {
        return homeworkService.createHw(homeworkDtoRequest);
    }

    @Override
    public List<HomeworkDto> getAllHomework() {
        return homeworkService.getHwByStudent(getCurrentStudent().getId());
    }

    @Override
    public List<LessonDto> getAllUndoneHomework() {
        return lessonService.getUndoneHwByStudent(getAllHomework(), getCurrentStudent().getId());
    }

    @Override
    public List<HomeworkDto> getAllHomeworkSubject(Long subjectId) {
        return homeworkService.getHwByStudentSubject(getCurrentStudent().getId(), subjectId);
    }

    @Override
    public List<LessonDto> getAllUndoneHomework(Long subjectId) {
        return lessonService.getUndoneHwByStudent(getAllHomework(), getCurrentStudent().getId(),subjectId);
    }

    @Override
    public List<ScheduleDto> getStudentSchedule() {
        return scheduleService.getAllScheduleByStudent(getCurrentStudent().getId());
    }

    @Override
    public List<LessonDto> getAllLessonByGrade() {
        return lessonService.getAllLessonByGradeId(lessonService.getAllLesson(), getCurrentStudent().getGrade().getId());
    }

    @Override
    public Map<String, String> getLessonsTopics() {
        return lessonService.getLessonsTopics(getAllLessonByGrade());
    }

    @Override
    public Map<String, String> getLessonsTopicsBySubject(Long subjectId) {
        return lessonService.getLessonsTopics(lessonService.getAllLessonBySubjectId(getAllLessonByGrade(), subjectId));
    }

    @Override
    public List<StudentDto> getClassmates() {
        return studentService.getAllStudentByGrade(getCurrentStudent().getGrade().getId());
    }

    @Override
    public Set<SubjectDto> getStudentSubject() {
        return subjectService.getSubjectForGrade(getCurrentStudent().getGrade().getId(), scheduleService.getAllActiveSchedule());
    }
}
