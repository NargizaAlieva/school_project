package org.example.school_project.service;

import org.example.school_project.dto.GradeDto;
import org.example.school_project.dto.GradeDtoRequest;
import org.example.school_project.dto.ScheduleDto;
import org.example.school_project.dto.ScheduleDtoRequest;
import org.example.school_project.entity.Grade;

import java.util.List;

public interface GradeService {
    Grade getByIdEntity(Long id);
    GradeDto getById(Long id);
    GradeDto createGrade(GradeDtoRequest gradeDtoRequest);
    GradeDto updateGrade(GradeDtoRequest gradeDtoRequest);
    GradeDto deleteGrade(Long id);
    List<GradeDto> getAllActiveGrade();
    List<GradeDto> getAllGrade();
}
