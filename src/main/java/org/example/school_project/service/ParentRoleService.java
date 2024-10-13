package org.example.school_project.service;

import org.example.school_project.dto.*;

import java.util.List;
import java.util.Map;

public interface ParentRoleService {
    Map<String, StudentDto> getChildList();

    StudentDto createStudent(StudentDtoRequest studentDtoRequest);

    List<MarkDto> getAllMark(Long childId);
    Map<String, Double> getAvgMarkByGradeStudentQuarter(Integer quarter, Long childId);
    Map<String, Double> getAvgMarkBySubjectGradeStudent(Long subjectId, Long childId);
    Map<String, Double> getAvgMarkByGradeStudent(Long childId);

    Map<String, Double> getAttendByQuarterGradeStudent(Integer quarter, Long childId);
    Map<String, Double> getAttendBySubjectGradeStudent(Long subjectId, Long childId);
    Map<String, Double> getAttendByGradeStudent(Long childId);

    List<ScheduleDto> getStudentSchedule(Long studentId);

    List<HomeworkDto> getAllHomework(Long studentId);
    List<HomeworkToDoDto> getAllUndoneHomework(Long studentId);
    List<HomeworkDto> getAllHomeworkSubject(Long studentId, Long subjectId);
    List<HomeworkToDoDto> getAllUndoneHomework(Long studentId, Long subjectId);

    List<ReviewDto> getStudentReview(Long studentId);
    void leaveSchool(Long studentId);
}
