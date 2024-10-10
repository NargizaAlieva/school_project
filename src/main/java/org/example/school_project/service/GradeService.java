package org.example.school_project.service;

import org.example.school_project.dto.*;
import org.example.school_project.entity.Grade;
import org.example.school_project.entity.Student;

import java.util.List;

public interface GradeService {
    Grade getByIdEntity(Long id);
    GradeDto getById(Long id);
    GradeDto createGrade(GradeDtoRequest gradeDtoRequest);
    GradeDto updateGrade(GradeDtoRequest gradeDtoRequest);
    GradeDto deleteGrade(Long id);
    List<GradeDto> getAllActiveGrade();

    List<GradeDto> getAllTeacherGrade(List<LessonDto> lessonDtoList);

    List<GradeDto> getAllGrade();
}
