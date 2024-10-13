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
    List<HomeworkDto> getStudentHwBySubjectGrade(Long subjectId, Integer quarter, Long gradeId, Long studentId);

    List<HomeworkDto> getUncheckedHw(List<HomeworkDto> homeworklist);

    List<HomeworkDto> getHwByStudent(Long studentId);

    List<HomeworkDto> getHwByStudentSubject(Long studentId, Long subjectId);

    List<HomeworkDto> getHwByLesson(Long id);


    List<Integer> getAllHwMark(List<LessonDto> lessonDtoList);

    HomeworkDto leaveHwMarkReview(Long hwId, Integer mark, String hwReview);

    List<HomeworkDto> convertToHw(List<LessonDto> lessonDtoList);
}
