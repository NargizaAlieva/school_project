package org.example.school_project.service;

import org.example.school_project.dto.HomeworkDto;
import org.example.school_project.dto.MarkDto;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface AverageMarkService {
    Map<String, Double> getAvgMarkByGradeStudentQuarter(Integer quarter, Long gradeId, Long studentId);

    Map<String, Double> getAvgMarkByGradeQuarter(Integer quarter, Long gradeId);

    Map<String, Double> getAvgMarkBySubjectGradeStudent(Long subjectId, Long gradeId, Long studentId);

    Map<String, Double> getAvgMarkByGradeStudent(Long gradeId, Long studentId);

    Map<String, Double> getAvgMarkByGrade(Long gradeId);

    Map<String, Double> getAvgMarkAll();

    Double getSchoolAvgMark();


    Map<String, Double> getAvgMarkBySubjectGradeQuarter(Long subjectId, Long gradeId, Integer quarter);

    Map<String, Double> getAvgMarkBySubjectGrade(Long subjectId, Long gradeId);

    Map<String, Double> getAvgMarkBySubject(Long subjectId);

    Double countAvgByMarkDto(List<MarkDto> markDtoList);

    Double countAvgByHwDto(List<HomeworkDto> markDtoList);

    Double countAvgByDouble(Collection<Double> markList);
}
