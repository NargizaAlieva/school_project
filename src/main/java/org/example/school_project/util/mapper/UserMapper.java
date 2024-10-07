package org.example.school_project.util.mapper;

import org.example.school_project.dto.UserDto;
import org.example.school_project.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setCreationDate(user.getCreationDate());
        userDto.setRoleSet(user.getRoleSet());
        return userDto;
    }

    public List<UserDto> entityToDtoList(List<User> tasks) {
        return tasks.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
