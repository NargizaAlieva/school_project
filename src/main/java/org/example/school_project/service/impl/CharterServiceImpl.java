package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.CharterDto;
import org.example.school_project.dto.CharterDtoRequest;
import org.example.school_project.entity.Charter;
import org.example.school_project.repository.CharterRepository;
import org.example.school_project.service.CharterService;
import org.example.school_project.service.EmployeeService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.CharterMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharterServiceImpl implements CharterService {
    private final CharterRepository charterRepository;
    private final CharterMapper charterMapper;
    private final EmployeeService employeeService;

    public Charter getCharterByIdEntity(Long id) {
        return charterRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Charter"));
    }
    @Override
    public CharterDto createCharter(CharterDtoRequest charterDtoR) {
        if(employeeService.findByIdEntity(charterDtoR.getAuthorId()) == null)
            throw new ObjectNotFoundException("Employee");
        return charterMapper.entityToDto(charterRepository.save(charterMapper.dtoToEntity(charterDtoR)));
    }

    @Override
    public CharterDto updateCharter(CharterDtoRequest charterDtoR) {
        if (getCharterByIdEntity(charterDtoR.getId()) == null) return null;

        Charter oldCharter = charterMapper.dtoToEntity(charterDtoR);
        Charter newCharter = getCharterByIdEntity(charterDtoR.getId());

        newCharter.setId(oldCharter.getId());
        newCharter.setTitle(oldCharter.getTitle());
        newCharter.setDescription(oldCharter.getDescription());
        newCharter.setCreationDate(oldCharter.getCreationDate());
        newCharter.setAuthorOfCharter(oldCharter.getAuthorOfCharter());
        return charterMapper.entityToDto(charterRepository.save(newCharter));
    }

    @Override
    public List<CharterDto> getAllCharter() {
        return charterMapper.entityToDtoList(charterRepository.findAll());
    }
}
