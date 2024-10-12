package org.example.school_project.service;

import org.example.school_project.dto.LessonDto;
import org.example.school_project.dto.MarkDto;
import org.example.school_project.dto.MarkDtoRequest;
import org.example.school_project.entity.Mark;

import java.util.List;

public interface MarkService {
    Mark getMarkByIdEntity(Long id);
    MarkDto getMarkById(Long id);

    MarkDto createMark(MarkDtoRequest markDtoRequest);
    MarkDto updateMark(MarkDtoRequest markDtoRequest);

    List<MarkDto> getAllMark();
    List<MarkDto> getAllMarkByStudent(Long studentId);
    List<MarkDto> getStudentMarksBySubjectGrade(Long subjectId, Integer quarter, Long gradeId, Long studentId);

    List<MarkDto> convertToMark(List<LessonDto> lessonDtoList);
}
