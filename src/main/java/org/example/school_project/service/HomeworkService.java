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

    List<HomeworkDto> filterHw(List<LessonDto> lessonDtoList);
    List<HomeworkDto> getHwByLesson(Long id);

    HomeworkDto liveHwMarkReview(Integer mark, String hwReview);
}
