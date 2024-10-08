package org.example.school_project.service;

import org.example.school_project.dto.*;

import java.util.List;

public interface DeanService {
    ScheduleDto createSchedule(ScheduleDtoRequest scheduleDtoRequest);
    ScheduleDto updateSchedule(ScheduleDtoRequest scheduleDtoRequest);
    ScheduleDto deleteSchedule(Long id);

    GradeDto getGradeById(Long id);
    GradeDto createGrade(GradeDtoRequest gradeDtoRequest);
    GradeDto updateGrade(GradeDtoRequest gradeDtoRequest);
    GradeDto deleteGrade(Long id);
    List<GradeDto> getAllActiveGrade();
    List<GradeDto> getAllGrade();

    CharterDto createCharter (CharterDtoRequest charterDtoR);
    CharterDto updateCharter (CharterDtoRequest charterDtoR);

    StudentDto getStudentById(Long id);
    StudentDto updateStudent(StudentDtoRequest studentDtoRequest);
    StudentDto excludeStudent(Long id);
    List<StudentDto> getAllStudent();
    List<StudentDto> getAllActiveStudent();

    AssignmentDto createAssignment(AssignmentDtoRequest assignmentDtoRequest);
    AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest);
}
