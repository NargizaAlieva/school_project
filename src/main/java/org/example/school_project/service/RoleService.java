package org.example.school_project.service;

import org.example.school_project.entity.Role;

public interface RoleService {
    Role findById(Long id);
    Role createRle(Role role);
}
