package org.example.school_project.service;

import org.example.school_project.dto.*;

import java.util.List;

public interface DeanService {
    ScheduleDto createSchedule(ScheduleDtoRequest scheduleDtoRequest);
    ScheduleDto updateSchedule(ScheduleDtoRequest scheduleDtoRequest);
    ScheduleDto deleteSchedule(Long id);
    ScheduleDto restoreSchedule(Long id);
    List<ScheduleDto> getAllSchedule();
    List<ScheduleDto> getAllActiveSchedule();

    GradeDto getGradeById(Long id);
    GradeDto createGrade(GradeDtoRequest gradeDtoRequest);
    GradeDto updateGrade(GradeDtoRequest gradeDtoRequest);
    GradeDto deleteGrade(Long id);

    GradeDto restoreGrade(Long id);

    List<GradeDto> getAllActiveGrade();
    List<GradeDto> getAllGrade();

    CharterDto createCharter (CharterDtoRequest charterDtoR);
    CharterDto updateCharter (CharterDtoRequest charterDtoR);
    CharterDto deleteCharter(Long id);
    CharterDto restoreCharter(Long id);
    List<CharterDto> getAllCharter();
    List<CharterDto> getAllCharterByAuthor();

    StudentDto getStudentById(Long id);
    StudentDto updateStudent(StudentDtoRequest studentDtoRequest);
    StudentDto excludeStudent(Long id);

    StudentDto restoreStudent(Long id);

    List<StudentDto> getAllStudent();
    List<StudentDto> getAllActiveStudent();
    List<StudentDto> getAllActiveStudentByGrade(Long gradeId);

    AssignmentDto createAssignment(AssignmentDtoRequest assignmentDtoRequest);
    AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest);
    AssignmentDto deleteAssignment(Long id);
    AssignmentDto restoreAssignment(Long id);
    List<AssignmentDto> getAllAssignmentByAuthor();
    List<AssignmentDto> getAllUndoneAssigment();

    List<AssignmentDto> getAllDoneAssigment();
}
