package org.example.school_project.service;

import org.example.school_project.dto.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StudentRoleService {
    List<MarkDto> getAllMark();
    Map<String, Double> getAvgMarkByGradeStudentQuarter(Integer quarter);
    Map<String, Double> getAvgMarkBySubjectGradeStudent(Long subjectId);
    Map<String, Double> getAvgMarkByGradeStudent();

    Map<String, Double> getAttendByQuarterGradeStudent(Integer quarter);
    Map<String, Double> getAttendBySubjectGradeStudent(Long subjectId);
    Map<String, Double> getAttendByGradeStudent();

    HomeworkDto sendHomework(HomeworkDtoRequest homeworkDtoRequest);
    List<HomeworkDto> getAllHomework();
    List<LessonDto> getAllUndoneHomework();
    List<HomeworkDto> getAllHomeworkSubject(Long subjectId);
    List<LessonDto> getAllUndoneHomework(Long subjectId);

    List<ScheduleDto> getStudentSchedule();
    List<LessonDto> getAllLessonByGrade();

    Map<String, String> getLessonsTopics();

    Map<String, String> getLessonsTopicsBySubject(Long subjectId);

    List<StudentDto> getClassmates();
    Set<SubjectDto> getStudentSubject();
}
