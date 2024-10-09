package org.example.school_project.service;

import org.example.school_project.dto.LessonDto;
import org.example.school_project.dto.LessonDtoRequest;
import org.example.school_project.entity.Lesson;

import java.util.List;

public interface LessonService {
    Lesson getLessonByIdEntity(Long id);
    LessonDto getLessonById(Long id);
    LessonDto createLesson(LessonDtoRequest lessonDtoRequest);
    LessonDto updateLesson(LessonDtoRequest lessonDtoRequest);
    List<LessonDto> getAllLesson();
    List<LessonDto> getAllLessonByTeacherId(Long id);
    List<LessonDto> getAllLessonByGradeId(Long id);
    List<LessonDto> getAllLessonBySubjectId(Long id);
    List<LessonDto> getAllLessonByYear(String year);
    List<LessonDto> getAllLessonByYearTeacherId(String year, Long teacherId);
    List<LessonDto> getAllLessonByYearGradeId(String year, Long gradeId);
    List<LessonDto> getAllLessonByYearSubjectId(String year, Long subjectId);

    List<LessonDto> getAllLessonByYearTeacherIdSubjectId(String year, Long teacherId, Long subjectId);
    List<LessonDto> getAllLessonByYearTeacherIdGradeId(String year, Long teacherId, Long gradeId);
    List<LessonDto> getAllLessonByYearSubjectIdGradeId(String year, Long subjectId, Long gradeId);

    List<LessonDto> getAllLessonByYearTeacherId(String year, Long teacherId, Integer quarter);
    List<LessonDto> getAllLessonByYearGradeId(String year, Long gradeId, Integer quarter);
    List<LessonDto> getAllLessonByYearSubjectId(String year, Long subjectId, Integer quarter);
    List<LessonDto> getAllLessonByYearTeacherIdSubjectId(String year, Long teacherId, Long subjectId, Integer quarter);
    List<LessonDto> getAllLessonByYearTeacherIdGradeId(String year, Long teacherId, Long gradeId, Integer quarter);
    List<LessonDto> getAllLessonByYearSubjectIdGradeId(String year, Long subjectId, Long gradeId, Integer quarter);
}
