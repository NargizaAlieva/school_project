package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.ParentDto;
import org.example.school_project.dto.ParentDtoRequest;
import org.example.school_project.entity.Parent;
import org.example.school_project.repository.ParentRepository;
import org.example.school_project.service.ParentService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.ParentMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {
    private final ParentRepository parentRepository;
    private final ParentMapping parentMapping;
    @Override
    public Parent getByIdEntity(Long id) {
        return parentRepository.findById(id).orElseThrow(() ->  new ObjectNotFoundException("Parent"));
    }

    @Override
    public ParentDto createParent(ParentDtoRequest parentDtoRequest) {
        return parentMapping.entityToDto(parentRepository.save(parentMapping.dtoToEntity(parentDtoRequest)));
    }

    @Override
    public ParentDto updateParent(ParentDtoRequest parentDtoRequest) {
        Parent newParent = getByIdEntity(parentDtoRequest.getId());
        Parent oldParent = parentMapping.dtoToEntity(parentDtoRequest);

        newParent.setId(oldParent.getId());
        newParent.setUser(oldParent.getUser());
        return parentMapping.entityToDto(parentRepository.save(newParent));
    }
}
