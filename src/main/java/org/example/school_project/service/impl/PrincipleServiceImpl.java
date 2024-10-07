package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.entity.*;
import org.example.school_project.service.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrincipleServiceImpl implements PrincipleService {
    private final ScheduleService scheduleService;
    private final CharterService charterService;
    private final EmployeeService employeeService;
    private final SubjectService subjectService;
    private final AssignmentService assignmentService;

    @Override
    public ScheduleDto getScheduleById(Long id) {
        return scheduleService.getScheduleById(id);
    }

    @Override
    public ScheduleDto approveSchedule(Long id) {
        return scheduleService.approveSchedule(id);
    }

    @Override
    public List<ScheduleDto> getAllSchedule() {
        return scheduleService.getAllSchedule();
    }

    @Override
    public List<ScheduleDto> getAllUnApprovedSchedule() {
        return scheduleService.getAllUnApprovedSchedule();
    }

    @Override
    public EmployeeDto hireEmployee(EmployeeDroRequest employeeDtoR) {
        return employeeService.createEmployee(employeeDtoR);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDroRequest employeeDtoR) {
        return employeeService.updateEmployee(employeeDtoR);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @Override
    public List<EmployeeDto> getAllActiveEmployee() {
        return employeeService.getAllActiveEmployee();
    }

    @Override
    public void fireEmployee(Long id) {
        employeeService.deleteEmployee(id);
    }

    @Override
    public SubjectDto addSubject(SubjectDtoRequest subjectDtoRequest) {
        return subjectService.addSubject(subjectDtoRequest);
    }

    @Override
    public SubjectDto updateSubject(SubjectDtoRequest subjectDtoRequest) {
        return subjectService.updateSubject(subjectDtoRequest);
    }

    @Override
    public void deleteSubject(Long id) {
        subjectService.deleteSubject(id);
    }

    @Override
    public CharterDto createCharter(CharterDtoRequest charterDtoR) {
        return charterService.createCharter(charterDtoR);
    }

    @Override
    public CharterDto updateCharter(CharterDtoRequest charterDtoR) {
        return charterService.updateCharter(charterDtoR);
    }

    @Override
    public AssignmentDto createAssignment(AssignmentDtoRequest assignmentDtoRequest) {
        return assignmentService.createAssigment(assignmentDtoRequest);
    }

//    @Override
//    public AssignmentDto createAssignmentToSecretary(AssignmentDtoRequest assignmentDtoRequest) {
//        return assignmentService.createToSecreter(assignmentDtoRequest);
//    }

    @Override
    public AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest) {
        return assignmentService.updateAssignment(assignmentDtoRequest);
    }

}
