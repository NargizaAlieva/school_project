package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.entity.Employee;
import org.example.school_project.service.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrincipleServiceImpl implements PrincipleService {
    private final UserService userService;
    private final ScheduleService scheduleService;
    private final CharterService charterService;
    private final EmployeeService employeeService;
    private final SubjectService subjectService;
    private final AssignmentService assignmentService;

    private Employee getPrinciple() {
        return employeeService.getByUserId(userService.getCurrentUser().getId());
    }

    @Override
    public ScheduleDto getScheduleById(Long id) {
        return scheduleService.getScheduleById(id);
    }

    @Override
    public ScheduleDto approveSchedule(Long id) {
        return scheduleService.approveSchedule(id);
    }
    @Override
    public ScheduleDto disapproveSchedule(Long id) {
        return scheduleService.disapproveSchedule(id);
    }

    @Override
    public ScheduleDto deleteSchedule(Long id) {
        return scheduleService.deleteSchedule(id);
    }

    @Override
    public ScheduleDto restoreSchedule(Long id) {
        return scheduleService.restoreSchedule(id);
    }


    @Override
    public List<ScheduleDto> getAllSchedule() {
        return scheduleService.getAllSchedule();
    }

    @Override
    public List<ScheduleDto> getAllActiveSchedule() {
        return scheduleService.getAllActiveSchedule();
    }

    @Override
    public List<ScheduleDto> getAllActiveScheduleByYear(String year) {
        return scheduleService.filterActiveSchedule(scheduleService.getAllScheduleByYear(year));
    }

    @Override
    public List<ScheduleDto> getAllUnapprovedSchedule() {
        return scheduleService.getAllUnApprovedSchedule();
    }

    @Override
    public ScheduleDto createSchedule(ScheduleDtoRequest scheduleDtoRequest) {
        return scheduleService.createSchedule(scheduleDtoRequest);
    }

    @Override
    public ScheduleDto updateSchedule(ScheduleDtoRequest scheduleDtoRequest) {
        return scheduleService.updateSchedule(scheduleDtoRequest);
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
    public SubjectDto deleteSubject(Long id) {
        return subjectService.deleteSubject(id);
    }

    @Override
    public SubjectDto restoreSubject(Long id) {
        return subjectService.restoreSubject(id);
    }

    @Override
    public List<SubjectDto> getAllSubject() {
        return subjectService.getAllSubject();
    }

    @Override
    public List<SubjectDto> getAllActiveSubject() {
        return subjectService.getAllActiveSubject();
    }

    @Override
    public CharterDto createCharter(CharterDtoRequest charterDtoR) {
        return charterService.createCharter(charterDtoR, getPrinciple().getId());
    }

    @Override
    public CharterDto updateCharter(CharterDtoRequest charterDtoR) {
        return charterService.updateCharter(charterDtoR, getPrinciple().getId());
    }

    @Override
    public CharterDto deleteCharter(Long id) {
        return charterService.deleteCharter(id);
    }

    @Override
    public CharterDto restoreCharter(Long id) {
        return charterService.restoreCharter(id);
    }

    @Override
    public List<CharterDto> getAllCharter(){
        return charterService.getAllCharter();
    }

    @Override
    public List<CharterDto> getAllCharterByAuthor(){
        return charterService.getAllCharterByAuthor(getPrinciple().getId());
    }

    @Override
    public AssignmentDto createAssignment(AssignmentDtoRequest assignmentDtoRequest) {
        return assignmentService.createAssigment(assignmentDtoRequest, getPrinciple().getId());
    }

    @Override
    public AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest) {
        return assignmentService.updateAssignment(assignmentDtoRequest, getPrinciple().getId());
    }

    @Override
    public AssignmentDto deleteAssignment(Long id) {
        return assignmentService.deleteAssignment(id, getPrinciple().getId());
    }

    @Override
    public AssignmentDto restoreAssignment(Long id) {
        return assignmentService.restoreAssignment(id, getPrinciple().getId());
    }

    @Override
    public List<AssignmentDto> getAllAssignmentByAuthor(){
        return assignmentService.getAllAssignmentByAuthor(getPrinciple().getId());
    }

    @Override
    public List<AssignmentDto> getAllUndoneAssigment(){
        return assignmentService.getAllUndoneAssignment(getAllAssignmentByAuthor());
    }
}
