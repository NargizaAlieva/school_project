package org.example.school_project.service;

import org.example.school_project.dto.UserDto;
import org.example.school_project.entity.User;

import java.util.List;

public interface UserService {
    UserDto getById(Long id);
    UserDto getByUsername(String username);
    UserDto getByEmail(String mail);
    void createUser(User user);
    List<UserDto> getAllUser();
    void deleteUser(Long id);
    UserDto updateUser(User user);
}
