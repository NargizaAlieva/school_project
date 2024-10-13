package org.example.school_project.service;

import org.example.school_project.dto.*;
import org.example.school_project.entity.Grade;

import java.util.List;
import java.util.Set;

public interface GradeService {
    Grade getByIdEntity(Long id);
    GradeDto getById(Long id);
    GradeDto createGrade(GradeDtoRequest gradeDtoRequest);
    GradeDto updateGrade(GradeDtoRequest gradeDtoRequest);
    GradeDto deleteGrade(Long id);

    GradeDto restoreGrade(Long id);

    List<GradeDto> getAllActiveGrade();

    Set<GradeDto> getAllTeacherGrade(List<LessonDto> lessonDtoList);

    List<GradeDto> getAllGrade();
}
