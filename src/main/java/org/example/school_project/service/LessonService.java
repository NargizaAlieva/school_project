package org.example.school_project.service;

import org.example.school_project.dto.HomeworkDto;
import org.example.school_project.dto.LessonDto;
import org.example.school_project.dto.LessonDtoRequest;
import org.example.school_project.entity.Lesson;

import java.util.List;
import java.util.Map;

public interface LessonService {
    Lesson getLessonByIdEntity(Long id);
    LessonDto getLessonById(Long id);
    LessonDto createLesson(LessonDtoRequest lessonDtoRequest);
    LessonDto updateLesson(LessonDtoRequest lessonDtoRequest);
    List<LessonDto> getAllLesson();

    Map<String, String> getLessonsTopics(List<LessonDto> lessonDtoList);

    List<LessonDto> getAllLessonByTeacherId(List<LessonDto> lessonDtoList, Long teacherId);

    List<LessonDto> getAllLessonByGradeId(List<LessonDto> lessonDtoList, Long gradeId);

    List<LessonDto> getAllLessonBySubjectId(List<LessonDto> lessonDtoList, Long subjectId);

    List<LessonDto> getAllLessonByYear(List<LessonDto> lessonDtoList, String year);

    List<LessonDto> getAllLessonByQuarter(List<LessonDto> lessonDtoList, Integer quarter);

    List<LessonDto> getAllLessonBySubjectQuarter(Long subjectId, Integer quarter, Long gradeId);

    List<LessonDto> getUndoneHwByStudent(List<HomeworkDto> homeworkDtoList, Long studentId);

    List<LessonDto> getUndoneHwByStudent(List<HomeworkDto> homeworkDtoList, Long studentId, Long subjectId);
}
