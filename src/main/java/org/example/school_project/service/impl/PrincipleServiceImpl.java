package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.EmployeeDto;
import org.example.school_project.dto.UserDto;
import org.example.school_project.entity.*;
import org.example.school_project.repository.*;
import org.example.school_project.service.PrincipleService;
import org.example.school_project.service.UserService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrincipleServiceImpl implements PrincipleService {
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final SubjectRepository subjectRepository;
    private final CharterRepository charterRepository;

    private final EmployeeMapper employeeMapper;

    @Override
    public Schedule getScheduleById(Long id) {
        if (id == null) return null;
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Schedule"));
        return schedule;
    }

    @Override
    public Schedule approveSchedule(Long id) {
        if (id == null) return null;
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Schedule"));
        schedule.setIsApprove(true);
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> getAllUnApprovedSchedule() {
        List<Schedule> allSchedule = scheduleRepository.findAll();
        List<Schedule> unapprovedSchedule = new ArrayList<>();
        for (Schedule sch : allSchedule) {
            if(!sch.getIsApprove()) unapprovedSchedule.add(sch);
        }
        return unapprovedSchedule;
    }

    @Override
    public EmployeeDto hireEmployee(Employee employee) {
        User user = userService.getEntityById(employee.getUser().getId());
        userService.createUser(user);
        return employeeMapper.entityToDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto updateEmployee(Employee employee) {
        User user = userService.getEntityById(employee.getUser().getId());
        Employee newEmployee = new Employee();
        newEmployee.toBuilder()
                .id(employee.getId())
                .position(employee.getPosition())
                .salary(employee.getSalary())
                .user(employee.getUser());
        return employeeMapper.entityToDto(employeeRepository.save(newEmployee));
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        return employeeMapper.entityToDtoList(employeeRepository.findAll());
    }

    @Override
    public void fireEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Employee"));
        User user = employee.getUser();
        user.setIsActive(false);
        userService.updateUser(user);
    }

    @Override
    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject updateSubject(Subject subject) {
        Subject newSubject = new Subject();
        newSubject.toBuilder()
                .id(subject.getId())
                .title(subject.getTitle())
                .description(subject.getDescription())
                .isActive(subject.getIsActive())
                .build();
        return subjectRepository.save(newSubject);
    }

    @Override
    public void deleteSubject(Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Employee"));
        subject.setIsActive(false);
        subjectRepository.save(subject);
    }

    @Override
    public Charter createCharter(Charter charter) {
        return charterRepository.save(charter);
    }
}
