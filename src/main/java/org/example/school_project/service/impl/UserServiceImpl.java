package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.school_project.dto.UserDto;
import org.example.school_project.dto.UserDtoRequest;
import org.example.school_project.entity.Role;
import org.example.school_project.entity.User;
import org.example.school_project.repository.UserRepository;
import org.example.school_project.service.UserService;
import org.example.school_project.util.exception.AlreadyExistException;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.UserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getById(Long id) {
        if (id == null) {
            return null;
        }
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User"));
        return userMapper.entityToDto(user);
    }

    @Override
    public UserDto saveUser(User user) {
        return userMapper.entityToDto(userRepository.save(user));
    }

    public User getEntityById(Long id) {
        if (id == null) {
            return null;
        }
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User"));
    }

    @Override
    public UserDto getByEmail(String mail) {
        User user = userRepository.findByEmail(mail)
                .orElseThrow(() -> new ObjectNotFoundException("User"));
        return userMapper.entityToDto(user);
    }


    @Override
    public UserDto getByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User"));
        return userMapper.entityToDto(user);

    }

    public User getByUsernameEntity(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User"));
    }

    public User getUsernameEntity(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User"));
    }

    @Override
    public UserDto createUser(UserDtoRequest userDtoRequest) {
        return userMapper.entityToDto(userRepository.save(userMapper.dtoToEntity(userDtoRequest)));
    }

    public UserDto save(User user) {
        return userMapper.entityToDto(userRepository.save(user));
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> userList = userRepository.findAll();
        return userMapper.entityToDtoList(userList);
    }

    public List<User> getAllUserEntity() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ObjectNotFoundException("User");
        }
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User"));
        user.setIsActive(false);
        userRepository.save(user);
    }

    @Override
    public UserDto updateUser(UserDtoRequest request) throws ObjectNotFoundException {
        User oldUser = userMapper.dtoToEntity(request);
        User newUser = userRepository.findById(request.getId()).orElseThrow(() -> new ObjectNotFoundException("User"));

        newUser.setId(oldUser.getId());
        newUser.setUsername(oldUser.getUsername());
        newUser.setFirstName(oldUser.getFirstName());
        newUser.setLastName(oldUser.getLastName());
        newUser.setMiddleName(oldUser.getMiddleName());
        newUser.setEmail(oldUser.getEmail());
        newUser.setPhone(oldUser.getPhone());
        newUser.setPassword(oldUser.getPassword());
        newUser.setIsActive(oldUser.getIsActive());
        newUser.setCreationDate(oldUser.getCreationDate());
        newUser.setRoleSet(oldUser.getRoleSet());

        return userMapper.entityToDto(userRepository.save(newUser));
    }

    private User buildUser(User user, User newUser) {
        return newUser.toBuilder().
                username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .middleName(user.getMiddleName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .password(user.getPassword())
                .roleSet(user.getRoleSet())
                .build();
    }

    public User getUserWithRole(String role) {
        List<User> userList = getAllUserEntity();
        for (User user: userList) {
            Set<Role> roleSet = user.getRoleSet();
            for (Role r : roleSet) {
                if(r.equals(role)) {
                    return user;
                }
            }
        }
        return null;
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUsernameEntity(username);
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsernameEntity;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
