package org.example.school_project.service;

import org.example.school_project.dto.*;

import java.util.List;

public interface TeacherService {
    List<ScheduleDto> getTeacherSchedule();

    LessonDto createLesson(LessonDtoRequest lessonDtoRequest);

    LessonDto updateLesson(LessonDtoRequest lessonDtoRequest);

    MarkDto createMark(MarkDtoRequest markDtoRequest);

    MarkDto updateMark(MarkDtoRequest markDtoRequest);

    AttendanceDto createAttendance(AttendanceDtoRequest attendanceDtoRequest);

    AttendanceDto updateAttendance(AttendanceDtoRequest attendanceDtoRequest);

    List<MarkDto> getMarkTeacher();

    List<MarkDto> getMarkTeacherByGrade(Long gradeId);

    List<GradeDto> getAllGrade();

    List<GradeDto> getTeacherGrade();

    List<StudentDto> getAllStudentByGrade(Long gradeId);

    ReviewDto createReview(ReviewDtoRequest reviewDtoRequest);

    ReviewDto updateReview(ReviewDtoRequest reviewDtoRequest);

    List<HomeworkDto> getAllHwByTeacher();
    HomeworkDto markHw(Long hwId, Integer mark, String hwReview);
}
