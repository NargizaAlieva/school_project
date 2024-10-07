package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.school_project.dto.UserDto;
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

    public User getUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User"));
    }

    @Override
    public void createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new AlreadyExistException("username");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("email");
        }
        save(user);
    }

    public UserDto save(User user) {
        return userMapper.entityToDto(userRepository.save(user));
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> userList = userRepository.findAll();
        return userMapper.entityToDtoList(userList);
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ObjectNotFoundException("User");
        }
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User"));
        user.setIsActive(false);
        updateUser(user);
    }

    @Override
    public UserDto updateUser(User request) throws ObjectNotFoundException {
        User user = userRepository.findById(request.getId()).orElseThrow(() -> new ObjectNotFoundException("User"));
        user = buildUser(request, user);
        userRepository.save(user);
        return userMapper.entityToDto(user);
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

    public UserDto getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    public UserDetailsService userDetailsService() {
        return this::getUsername;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
