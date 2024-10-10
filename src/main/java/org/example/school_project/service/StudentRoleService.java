package org.example.school_project.service;

import org.example.school_project.dto.*;

import java.util.List;

public interface StudentRoleService {
    List<MarkDto> getAllMark();
    List<MarkDto> getAllMarkBySubject(Long subject);
    List<MarkDto> getAllMarkBySubjectQuarter(String year, Long subjectId, Integer quarter);

    HomeworkDto sendHomework(HomeworkDtoRequest homeworkDtoRequest);
    List<HomeworkDto> getAllHomework();
    List<HomeworkToDoDto> getAllUndoneHomework();
    List<HomeworkDto> getAllHomeworkSubject(Long subjectId);
    List<HomeworkToDoDto> getAllUndoneHomework(Long subjectId);

    List<AttendanceDto> getAttendance();
    List<AttendanceDto> getAttendanceSubject(Long subjectId);
    List<AttendanceDto> getAttendanceSubject(Long subjectId, String year);
    List<AttendanceDto> getAttendanceSubject(Long subjectId, String year, Integer quarter);

    List<ScheduleDto> getStudentSchedule();
    List<LessonDto> getAllLessonByGrade();
    List<SubjectDto> getSubjectList(String year);
    List<StudentDto> getClassmates();
}
