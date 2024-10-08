package org.example.school_project.service;

import org.example.school_project.dto.*;
import org.example.school_project.entity.*;

import java.util.List;

public interface PrincipleService {
    // methods connected with schedule
    ScheduleDto getScheduleById(Long id);
    ScheduleDto approveSchedule(Long id);
    List<ScheduleDto> getAllSchedule();
    List<ScheduleDto> getAllUnApprovedSchedule();

    //methods connected with employee
    EmployeeDto hireEmployee(EmployeeDroRequest employeeDtoR);
    EmployeeDto updateEmployee(EmployeeDroRequest employeeDtoR);
    List<EmployeeDto> getAllEmployee();
    List<EmployeeDto> getAllActiveEmployee();
    void fireEmployee(Long id);

    // methods connected with subject
    SubjectDto addSubject(SubjectDtoRequest subjectDtoRequest);
    SubjectDto updateSubject(SubjectDtoRequest subjectDtoRequest);
    void deleteSubject(Long id);

    // methods connected with charter
    CharterDto createCharter(CharterDtoRequest charterDtoR);
    CharterDto updateCharter(CharterDtoRequest charterDtoR);

    // methods connected with assignment
    AssignmentDto createAssignment(AssignmentDtoRequest assignmentDtoRequest);
//    AssignmentDto createAssignmentToSecretary(AssignmentDtoRequest assignmentDtoRequest);
    AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest);

}
