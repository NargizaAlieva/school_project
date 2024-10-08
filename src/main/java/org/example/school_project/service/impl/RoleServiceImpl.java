package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.entity.Role;
import org.example.school_project.entity.User;
import org.example.school_project.repository.RoleRepository;
import org.example.school_project.service.RoleService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Role"));
    }

    @Override
    public Role updateRole(Role role) {
        Role newRole = new Role();
        newRole.setId(role.getId());
        newRole.setTitle(role.getTitle());
        newRole.setUserSet(role.getUserSet());
        return save(newRole);
    }

    @Override
    public Role addUser(Role role, User user) {
        Set<User> roleSet = role.getUserSet();
        roleSet.add(user);
        role.setUserSet(roleSet);
        return save(role);
    }

    @Override
    public Role removeUser(Role role, User user) {
        Set<User> roleSet = role.getUserSet();
        roleSet.remove(user);
        role.setUserSet(roleSet);
        return save(role);
    }

    public Role save (Role role){
        return roleRepository.save(role);
    }

    @Override
    public Role createRole(Role role) {
        return null;
    }
}
