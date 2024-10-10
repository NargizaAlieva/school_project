package org.example.school_project.service;

import org.example.school_project.dto.*;
import org.example.school_project.entity.*;

import java.util.List;

public interface PrincipleService {
    // methods connected with schedule
    ScheduleDto getScheduleById(Long id);
    ScheduleDto approveSchedule(Long id);
    ScheduleDto disapproveSchedule(Long id);
    ScheduleDto deleteSchedule(Long id);
    ScheduleDto restoreSchedule(Long id);

    List<ScheduleDto> getAllSchedule();
    List<ScheduleDto> getAllActiveSchedule();
    List<ScheduleDto> getAllActiveScheduleByYear(String year);
    List<ScheduleDto> getAllUnapprovedSchedule();
    ScheduleDto createSchedule(ScheduleDtoRequest scheduleDtoRequest);
    ScheduleDto updateSchedule(ScheduleDtoRequest scheduleDtoRequest);

    //methods connected with employee
    EmployeeDto hireEmployee(EmployeeDroRequest employeeDtoR);
    EmployeeDto updateEmployee(EmployeeDroRequest employeeDtoR);
    List<EmployeeDto> getAllEmployee();
    List<EmployeeDto> getAllActiveEmployee();
    void fireEmployee(Long id);

    // methods connected with subject
    SubjectDto addSubject(SubjectDtoRequest subjectDtoRequest);
    SubjectDto updateSubject(SubjectDtoRequest subjectDtoRequest);
    SubjectDto deleteSubject(Long id);
    SubjectDto restoreSubject(Long id);

    List<SubjectDto> getAllSubject();

    List<SubjectDto> getAllActiveSubject();

    // methods connected with charter
    CharterDto createCharter(CharterDtoRequest charterDtoR);
    CharterDto updateCharter(CharterDtoRequest charterDtoR);

    CharterDto deleteCharter(Long id);

    CharterDto restoreCharter(Long id);

    List<CharterDto> getAllCharter();

    List<CharterDto> getAllCharterByAuthor();

    AssignmentDto createAssignment(AssignmentDtoRequest assignmentDtoRequest);
    AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest);

    AssignmentDto deleteAssignment(Long id);
    AssignmentDto restoreAssignment(Long id);
    List<AssignmentDto> getAllAssignmentByAuthor();
    List<AssignmentDto> getAllUndoneAssigment();


}
