package org.example.school_project.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.UserDto;
import org.example.school_project.dto.UserDtoRequest;
import org.example.school_project.entity.Role;
import org.example.school_project.entity.User;
import org.example.school_project.service.RoleService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final RoleService roleService;
    public UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setIsActive(user.getIsActive());
        userDto.setCreationDate(user.getCreationDate());
        userDto.setRoleSet(user.getRoleSet());
        return userDto;
    }

    public List<UserDto> entityToDtoList(List<User> tasks) {
        return tasks.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public User dtoToEntity(UserDtoRequest userDtoRequest) {
        Set<Role> roleSet = new HashSet<>();
        Set<Long> roleIdSet = userDtoRequest.getRoleSet();

        for (Long r : roleIdSet) {
            roleSet.add(roleService.findById(r));
        }

        if(userDtoRequest.getCreationDate() == null) userDtoRequest.setCreationDate(LocalDateTime.now());

        User user = new User();
        user.setId(userDtoRequest.getId());
        user.setUsername(userDtoRequest.getUsername());
        user.setFirstName(userDtoRequest.getFirstName());
        user.setLastName(userDtoRequest.getLastName());
        user.setMiddleName(userDtoRequest.getMiddleName());
        user.setEmail(userDtoRequest.getEmail());
        user.setPassword(userDtoRequest.getPassword());
        user.setPhone(userDtoRequest.getPhone());
        user.setCreationDate(userDtoRequest.getCreationDate());
        user.setRoleSet(roleSet);
        return user;
    }
}
