package org.example.school_project.service;

import org.example.school_project.dto.*;

import java.util.List;

public interface StudentRoleService {
    List<MarkDto> getAllMark();

    List<SubjectDto> getStudentSubject(String year);

    List<LessonDto> getAllMarkByYearSubjectQuarter(String year, Long subjectId, Integer quarter);

    List<Double> getGradeByYearSubject(String year, Long subjectId);

    List<AttendanceDto> getAttendanceByYearSubject(String year, Long subjectId);

    List<Double> getGradeByQuarterYear(Integer quarter, String year);

    List<Double> getGradeByYear(String year);

    HomeworkDto sendHomework(HomeworkDtoRequest homeworkDtoRequest);
    List<HomeworkDto> getAllHomework();
    List<HomeworkToDoDto> getAllUndoneHomework();
    List<HomeworkDto> getAllHomeworkSubject(Long subjectId);
    List<HomeworkToDoDto> getAllUndoneHomework(Long subjectId);

    List<AttendanceDto> getAttendance();
    List<AttendanceDto> getAttendanceSubject(Long subjectId);

    List<ScheduleDto> getStudentSchedule();
    List<LessonDto> getAllLessonByGrade();
    List<StudentDto> getClassmates();
}
