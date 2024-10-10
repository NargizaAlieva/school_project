package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.example.school_project.dto.RoleDto;
import org.example.school_project.dto.UserDto;
import org.example.school_project.dto.UserDtoRequest;
import org.example.school_project.entity.Role;
import org.example.school_project.entity.User;
import org.example.school_project.repository.UserRepository;
import org.example.school_project.service.RoleService;
import org.example.school_project.service.UserService;
import org.example.school_project.util.exception.AlreadyExistException;
import org.example.school_project.util.exception.IncorrectRequestException;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;

    public User getByUsernameEntity(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User"));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUserEntity() {
        return userRepository.findAll();
    }

    public Boolean isIdExist(Long id) {
        if (userRepository.existsById(id))
            throw new AlreadyExistException("User", "'id'");
        return false;
    }

    public Boolean isUsernameExist(String username) {
        if (userRepository.existsByUsername(username))
            throw new AlreadyExistException("User", "'username'");
        return false;
    }

    public Boolean isEmailExist(String email) {
        if (userRepository.existsByEmail(email))
            throw new AlreadyExistException("User", "'email'");
        return false;
    }

    @Override
    public User getEntityById(Long id) {
        if (id == null) {
            throw new IncorrectRequestException("User id");
        }
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User"));
    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.entityToDto(getEntityById(id));
    }

    @Override
    public UserDto getByUsername(String username) {
        return userMapper.entityToDto(getByUsernameEntity(username));
    }

    @Override
    public UserDto getByEmail(String mail) {
        User user = userRepository.findByEmail(mail)
                .orElseThrow(() -> new ObjectNotFoundException("Email"));
        return userMapper.entityToDto(user);
    }

    @Override
    public User getUserWithRole(String role) {
        for (User user: getAllUserEntity())
            for (Role r : user.getRoleSet())
                if(r.getTitle().equals(role.toUpperCase()))
                    return user;
        throw new ObjectNotFoundException("User");
    }

    @Override
    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsernameEntity(username);
    }

    @Override
    public List<User> getUserListWithRole(String role) {
        List<User> userList = new ArrayList<>();
        for (User u: getAllUserEntity())
            for (Role r : u.getRoleSet())
                if(r.getTitle().equals(role.toUpperCase()))
                    userList.add(u);
        return userList;
    }

    @Override
    public UserDto saveUser(User user) {
        return userMapper.entityToDto(save(user));
    }

    @Override
    public UserDto createUser(UserDtoRequest userDtoRequest) {
        if (!(isIdExist(userDtoRequest.getId())
                || isUsernameExist(userDtoRequest.getUsername())
                || isEmailExist(userDtoRequest.getEmail())))
            return userMapper.entityToDto(save(userMapper.dtoToEntity(userDtoRequest)));
        return null;
    }

    @Override
    public UserDto updateUser(UserDtoRequest request)  {
        User oldUser = userMapper.dtoToEntity(request);
        User newUser = getEntityById(request.getId());

        if (!newUser.getUsername().equals(oldUser.getUsername()))
            isUsernameExist(oldUser.getUsername());

        if (!newUser.getEmail().equals(oldUser.getEmail()))
            isEmailExist(oldUser.getEmail());

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

        return userMapper.entityToDto(save(newUser));
    }

    @Override
    public User addRoleToUser(RoleDto roleDto) {
        User user = getEntityById(roleDto.getUserId());

        Set<Role> roleSet = user.getRoleSet();
        Set<Long> roleIdSet = roleDto.getRoleIdSet();

        for (Long r : roleIdSet) {
            Role role = roleService.findById(r);
            roleService.addUser(role, user);
            roleSet.add(role);
        }
        user.setRoleSet(roleSet);
        return save(user);
    }

    @Override
    public User removeRoleFromUser(RoleDto roleDto) {
        User user = getEntityById(roleDto.getUserId());

        Set<Role> roleSet = user.getRoleSet();
        Set<Long> roleIdSet = roleDto.getRoleIdSet();

        for (Long r : roleIdSet) {
            Role role = roleService.findById(r);
            roleService.removeUser(role, user);
            roleSet.remove(roleService.findById(r));
        }
        user.setRoleSet(roleSet);
        return save(user);
    }

    @Override
    public UserDto restoreUser(Long id) {
        User user = getEntityById(id);
        user.setIsActive(true);
        return userMapper.entityToDto(save(user));
    }

    @Override
    public void deleteUser(Long id) {
        User user = getEntityById(id);
        user.setIsActive(false);
        save(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> userList = userRepository.findAll();
        return userMapper.entityToDtoList(userList);
    }

    @Override
    public List<UserDto> getAllActiveUser() {
        List<UserDto> activeUserList = new ArrayList<>();
        for (UserDto u : getAllUser())
            if (u.getIsActive())
                activeUserList.add(u);
        return activeUserList;
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
