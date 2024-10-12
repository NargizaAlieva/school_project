package org.example.school_project.service;

import org.example.school_project.dto.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    Map<String, Double> getAvgMarkBySubjectGradeQuarter(Long subjectId, Long gradeId, Integer quarter);

    Map<String, Double> getAvgMarkBySubjectGrade(Long gradeId, Long subjectId);

    Map<String, Double> getAvgMarkBySubject(Long subjectId);

    List<GradeDto> getAllGrade();

    Set<GradeDto> getTeacherGrade();

    List<StudentDto> getAllStudentByGrade(Long gradeId);

    ReviewDto createReview(ReviewDtoRequest reviewDtoRequest);

    ReviewDto updateReview(ReviewDtoRequest reviewDtoRequest);

    List<HomeworkDto> getAllHwByTeacher();

    List<HomeworkDto> getUncheckedHw();

    List<HomeworkDto> getAllUncheckedHwByTeacherLesson(Long lessonId);

    HomeworkDto markHw(Long hwId, Integer mark, String hwReview);

    void sendToParentByStudentId(Long studentId, MessageDtoRequest messageDtoRequest);
}
