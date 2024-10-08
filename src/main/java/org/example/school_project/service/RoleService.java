package org.example.school_project.service;

import org.example.school_project.entity.Role;
import org.example.school_project.entity.User;

public interface RoleService {
    Role findById(Long id);
    Role updateRole(Role role);
    Role addUser(Role role, User user);
    Role removeUser(Role role, User user);
    Role createRole(Role role);
}
