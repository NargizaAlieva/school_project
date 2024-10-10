package org.example.school_project.service;

import org.example.school_project.dto.*;

import java.util.List;

public interface ParentRoleService {
    StudentDto createStudent(StudentDtoRequest studentDtoRequest);
    List<ScheduleDto> getStudentSchedule(Long studentId);


    List<MarkDto> getAllMark(Long studentId);
    List<MarkDto> getAllMarkBySubject(Long studentId, Long subject);
    List<MarkDto> getAllMarkBySubjectQuarter(Long studentId, String year, Long subjectId, Integer quarter);

    List<AttendanceDto> getAttendance(Long studentId);
    List<AttendanceDto> getAttendanceSubject(Long studentId, Long subjectId);
    List<AttendanceDto> getAttendanceSubject(Long studentId, Long subjectId, String year);
    List<AttendanceDto> getAttendanceSubject(Long studentId, Long subjectId, String year, Integer quarter);

    List<HomeworkDto> getAllHomework(Long studentId);

    List<HomeworkToDoDto> getAllUndoneHomework(Long studentId);

    List<HomeworkDto> getAllHomeworkSubject(Long studentId, Long subjectId);

    List<HomeworkToDoDto> getAllUndoneHomework(Long studentId, Long subjectId);

    List<ReviewDto> getStudentReview(Long studentId);

    void leaveSchool(Long studentId);
}
