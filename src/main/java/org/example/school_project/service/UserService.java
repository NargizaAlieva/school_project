package org.example.school_project.service;

import org.example.school_project.dto.RoleDto;
import org.example.school_project.dto.UserDto;
import org.example.school_project.dto.UserDtoRequest;
import org.example.school_project.entity.User;

import java.util.List;

public interface UserService {
    User getEntityById(Long id);
    UserDto getById(Long id);
    UserDto getByUsername(String username);
    UserDto getByEmail(String mail);
    User getCurrentUser();

    User getUserWithRole(String role);
    List<User> getUserListWithRole(String role);

    UserDto saveUser(User user);
    UserDto createUser(UserDtoRequest userDtoRequest);
    UserDto updateUser(UserDtoRequest userDtoRequest);

    User addRoleToUser(RoleDto roleDto);

    UserDto restoreUser(Long id);

    void deleteUser(Long id);

    User removeRoleFromUser(RoleDto roleDto);
    List<UserDto> getAllUser();

    List<UserDto> getAllActiveUser();
}
