package org.example.school_project.service;

import org.example.school_project.dto.*;

import java.util.List;

public interface AdminService {
    List<UserDto> getAllUser();

    List<UserDto> getAllActiveUser();

    List<UserDto> getAllUserByRole(String role);
    List<EmployeeDto> getAllEmployee();
    List<EmployeeDto> getAllActiveEmployee();
    List<ParentDto> getAllParent();

    List<ParentDto> getAllActiveParent();

    List<StudentDto> getAllStudent();
    List<StudentDto> getAllActiveStudent();

    UserDto createUser(UserDtoRequest userDtoRequest);
    EmployeeDto createEmployee(EmployeeDroRequest employeeDroRequest);
    StudentDto createStudent(StudentDtoRequest studentDtoRequest);
    ParentDto createParent(ParentDtoRequest parentDtoRequest);

    UserDto updateUser(UserDtoRequest userDtoRequest);
    EmployeeDto updateEmployee(EmployeeDroRequest employeeDroRequest);
    StudentDto updateStudent(StudentDtoRequest studentDtoRequest);
    ParentDto updateParent(ParentDtoRequest parentDtoRequest);

    UserDto restoreUser(Long id);

    void deleteUser(Long id);

    UserDto addRoleToUser(RoleDto roleDto);
    UserDto removeRoleFromUser(RoleDto roleDto);
}
