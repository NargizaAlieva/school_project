package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.service.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {
    private final StudentService studentService;
    private final AssignmentService assignmentService;
    private final ScheduleService scheduleService;
    private final CharterService charterService;
    private final EmployeeService employeeService;
    @Override
    public StudentDto createStudent(StudentDtoRequest studentDtoRequest) {
        return studentService.createStudent(studentDtoRequest);
    }

    @Override
    public CharterDto createCharter(CharterDtoRequest charterDtoR) {
//        return charterService.createCharter(charterDtoR);
        return null;
    }

    @Override
    public CharterDto updateCharter(CharterDtoRequest charterDtoR) {

//        return charterService.updateCharter(charterDtoR);
        return null;
    }

    @Override
    public AssignmentDto createAssignment(AssignmentDtoRequest assignmentDtoRequest) {
//        return assignmentService.createAssigment(assignmentDtoRequest);
        return null;
    }

//    @Override
//    public AssignmentDto createAssignmentToSecretary(AssignmentDtoRequest assignmentDtoRequest) {
//        return assignmentService.createToSecreter(assignmentDtoRequest);
//    }

    @Override
    public AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest) {
//        return assignmentService.updateAssignment(assignmentDtoRequest);
        return null;
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDroRequest employeeDtoR) {
        return employeeService.updateEmployee(employeeDtoR);
    }

    @Override
    public ScheduleDto getScheduleById(Long id) {
        return scheduleService.getScheduleById(id);
    }

    @Override
    public List<ScheduleDto> getAllSchedule() {
        return scheduleService.getAllSchedule();
    }

    @Override
    public List<ScheduleDto> getAllUnApprovedSchedule() {
        return scheduleService.getAllUnApprovedSchedule();
    }
}
