package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.EmployeeDto;
import org.example.school_project.dto.ParentDto;
import org.example.school_project.dto.ParentDtoRequest;
import org.example.school_project.dto.UserDto;
import org.example.school_project.entity.Parent;
import org.example.school_project.repository.ParentRepository;
import org.example.school_project.service.ParentService;
import org.example.school_project.service.UserService;
import org.example.school_project.util.exception.AlreadyExistException;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.ParentMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {
    private final ParentRepository parentRepository;
    private final ParentMapping parentMapping;
    private final UserService userService;

    public List<Parent> getAllParentEntity(){
        return parentRepository.findAll();
    }

    public Boolean isExistByUserId(Long id) {
        for (ParentDto e : getAllParent())
            if (e.getUser().getId().equals(id))
                return true;
        return false;
    }

    public ParentDto getParentByUserId(Long id) {
        for (ParentDto p : getAllParent())
            if (p.getUser().getId().equals(id))
                return p;
        throw new ObjectNotFoundException("Parent");
    }
    @Override
    public Parent getByIdEntity(Long id) {
        return parentRepository.findById(id).orElseThrow(() ->  new ObjectNotFoundException("Parent"));
    }

    @Override
    public ParentDto createParent(ParentDtoRequest parentDtoRequest) {
        if (isExistByUserId(parentDtoRequest.getUserId()))
            throw new AlreadyExistException("Employee", "'userId'");

        return parentMapping.entityToDto(parentRepository.save(parentMapping.dtoToEntity(parentDtoRequest)));
    }

    @Override
    public ParentDto updateParent(ParentDtoRequest parentDtoRequest) {
        if (userService.getById(parentDtoRequest.getUserId()) == null)
            throw new ObjectNotFoundException("User");

        Parent newParent = getByIdEntity(parentDtoRequest.getId());
        Parent oldParent = parentMapping.dtoToEntity(parentDtoRequest);

        if(!oldParent.getUser().getId().equals(newParent.getUser().getId()))
            if (isExistByUserId(parentDtoRequest.getUserId()))
                throw new AlreadyExistException("Parent", "'userId'");

        newParent.setId(oldParent.getId());
        newParent.setUser(oldParent.getUser());
        return parentMapping.entityToDto(parentRepository.save(newParent));
    }

    @Override
    public List<ParentDto> getAllParent() {
        return parentMapping.entityToDtoList(getAllParentEntity());
    }

    @Override
    public List<ParentDto> getAllActiveParent() {
        List<ParentDto> activeParent = new ArrayList<>();
        for (ParentDto p : getAllParent())
            if (p.getUser().getIsActive())
                activeParent.add(p);
        return activeParent;
    }
}
