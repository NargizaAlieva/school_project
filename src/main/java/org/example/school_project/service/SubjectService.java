package org.example.school_project.service;

import org.example.school_project.dto.ScheduleDto;
import org.example.school_project.dto.SubjectDto;
import org.example.school_project.dto.SubjectDtoRequest;
import org.example.school_project.entity.Subject;

import java.util.List;
import java.util.Set;

public interface SubjectService {
    Subject findByIdEntity(Long id);
    List<SubjectDto> getSubjectSchedule(List<ScheduleDto> scheduleDtoList);

    SubjectDto addSubject(SubjectDtoRequest subjectDtoRequest);
    SubjectDto updateSubject(SubjectDtoRequest subjectDtoRequest);
    SubjectDto deleteSubject(Long id);

    SubjectDto restoreSubject(Long id);

    List<SubjectDto> getAllSubject();
    List<SubjectDto> getAllActiveSubject();
    Set<SubjectDto> getSubjectForGrade(Long gradeId, List<ScheduleDto> scheduleDtoList);
}
