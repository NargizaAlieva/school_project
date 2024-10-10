package org.example.school_project.service;

import org.example.school_project.dto.*;

public interface AdminService {
    UserDto createUser(UserDtoRequest userDtoRequest);
    EmployeeDto createEmployee(EmployeeDroRequest employeeDroRequest);
    StudentDto createStudent(StudentDtoRequest studentDtoRequest);
    ParentDto createParent(ParentDtoRequest parentDtoRequest);

    UserDto updateUser(UserDtoRequest userDtoRequest);
    EmployeeDto updateEmployee(EmployeeDroRequest employeeDroRequest);
    StudentDto updateStudent(StudentDtoRequest studentDtoRequest);
    ParentDto updateParent(ParentDtoRequest parentDtoRequest);

    void deleteUser(Long id);

    UserDto addRoleToUser(RoleDto roleDto);
    UserDto removeRoleFromUser(RoleDto roleDto);
}
