package org.example.school_project.service;

import org.example.school_project.dto.HomeworkDto;
import org.example.school_project.dto.HomeworkDtoRequest;
import org.example.school_project.dto.LessonDto;
import org.example.school_project.entity.Homework;

import java.util.List;

public interface HomeworkService {
    Homework getHwByIdEntity(Long id);
    HomeworkDto getHwById(Long id);
    HomeworkDto createHw(HomeworkDtoRequest homeworkDtoRequest);
    HomeworkDto updateHw(HomeworkDtoRequest homeworkDtoRequest);

    List<HomeworkDto> filtHw(List<LessonDto> lessonDtoList);
    List<HomeworkDto> getHwByStudent(Long studentId);

    List<HomeworkDto> getHwByStudentSubject(Long studentId, Long subjectId);

    List<Integer> getAllHwMark(List<LessonDto> lessonDtoList);

    Double getAverageHwMark(List<Integer> mark);

    List<HomeworkDto> getHwByLesson(Long id);

    HomeworkDto leaveHwMarkReview(Long hwId, Integer mark, String hwReview);
}
