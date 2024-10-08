package org.example.school_project.service;

import org.example.school_project.dto.*;

import java.util.List;

public interface CommonService {
    StudentDto createStudent(StudentDtoRequest studentDtoRequest);
    AssignmentDto createAssignment(AssignmentDtoRequest assignmentDtoRequest);
    AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest);
    CharterDto updateCharter(CharterDtoRequest charterDtoR);
    CharterDto createCharter(CharterDtoRequest charterDtoR);
    EmployeeDto updateEmployee(EmployeeDroRequest employeeDtoR);
    List<ScheduleDto> getAllSchedule();
    List<ScheduleDto> getAllUnApprovedSchedule();
    ScheduleDto getScheduleById(Long id);
}
