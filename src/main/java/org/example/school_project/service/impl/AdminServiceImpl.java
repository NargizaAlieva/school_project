package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.service.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserService userService;
    private final EmployeeService employeeService;
    private final StudentService studentService;
    private final ParentService parentService;

    @Override
    public UserDto createUser(UserDtoRequest userDtoRequest) {
        return userService.createUser(userDtoRequest);
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDroRequest employeeDroRequest) {
        return employeeService.createEmployee(employeeDroRequest);
    }

    @Override
    public StudentDto createStudent(StudentDtoRequest studentDtoRequest) {
        return studentService.createStudent(studentDtoRequest);
    }

    @Override
    public ParentDto createParent(ParentDtoRequest parentDtoRequest) {
        return parentService.createParent(parentDtoRequest);
    }

    @Override
    public UserDto updateUser(UserDtoRequest userDtoRequest) {
        return userService.updateUser(userDtoRequest);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDroRequest employeeDroRequest) {
        return employeeService.updateEmployee(employeeDroRequest);
    }

    @Override
    public StudentDto updateStudent(StudentDtoRequest studentDtoRequest) {
        return studentService.updateStudent(studentDtoRequest);
    }

    @Override
    public ParentDto updateParent(ParentDtoRequest parentDtoRequest) {
        return parentService.updateParent(parentDtoRequest);
    }

    @Override
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }
}
