package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.service.*;
import org.example.school_project.util.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserService userService;
    private final EmployeeService employeeService;
    private final StudentService studentService;
    private final ParentService parentService;
    private final UserMapper userMapper;
    private final FileService fileService;
    @Override
    public List<UserDto> getAllUser() {
        return userService.getAllUser();
    }

    @Override
    public List<UserDto> getAllActiveUser() {
        return userService.getAllActiveUser();
    }

    @Override
    public List<UserDto> getAllUserByRole(String role) {
        return userMapper.entityToDtoList(userService.getUserListWithRole(role));
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
    public List<ParentDto> getAllParent() {
        return parentService.getAllParent();
    }

    @Override
    public List<ParentDto> getAllActiveParent() {
        return parentService.getAllActiveParent();
    }

    @Override
    public List<StudentDto> getAllStudent() {
        return studentService.getAllActiveStudent();
    }

    @Override
    public List<StudentDto> getAllActiveStudent() {
        return studentService.getAllActiveStudent();
    }

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
        fileService.saveStudentDataToDocx(studentDtoRequest);
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
    public UserDto restoreUser(Long id) {
        return userService.restoreUser(id);
    }

    @Override
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    @Override
    public UserDto addRoleToUser(RoleDto roleDto) {
        return userMapper.entityToDto(userService.addRoleToUser(roleDto));
    }

    @Override
    public UserDto removeRoleFromUser(RoleDto roleDto) {
        return userMapper.entityToDto(userService.removeRoleFromUser(roleDto));
    }
}
