package org.example.school_project.service;

import org.example.school_project.dto.RoleDto;
import org.example.school_project.dto.UserDto;
import org.example.school_project.dto.UserDtoRequest;
import org.example.school_project.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);
    User getCurrentUser();
    User getUserWithRole(String role);
    UserDto getById(Long id);
    UserDto saveUser(User user);
    UserDto getByUsername(String username);
    UserDto getByEmail(String mail);
    UserDto createUser(UserDtoRequest userDtoRequest);
    User addRoleToUser(RoleDto roleDto);
    User removeRoleFromUser(RoleDto roleDto);
    List<UserDto> getAllUser();
    void deleteUser(Long id);
    UserDto updateUser(UserDtoRequest userDtoRequest);
    User getEntityById(Long id);
}
