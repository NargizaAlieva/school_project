package org.example.school_project.service;

import org.example.school_project.dto.ParentDto;
import org.example.school_project.dto.ParentDtoRequest;
import org.example.school_project.dto.UserDto;
import org.example.school_project.entity.Parent;

import java.util.List;

public interface ParentService {
    Parent getByIdEntity(Long id);

    ParentDto createParent(ParentDtoRequest parentDtoRequest);
    ParentDto updateParent(ParentDtoRequest parentDtoRequest);

    List<ParentDto> getAllParent();
    List<ParentDto> getAllActiveParent();
}
