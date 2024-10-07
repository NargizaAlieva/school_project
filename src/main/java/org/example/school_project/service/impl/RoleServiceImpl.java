package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.entity.Role;
import org.example.school_project.repository.RoleRepository;
import org.example.school_project.service.RoleService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Role"));
    }

    @Override
    public Role createRle(Role role) {
        return null;
    }
}
