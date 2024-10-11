package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.CharterDto;
import org.example.school_project.dto.CharterDtoRequest;
import org.example.school_project.entity.Charter;
import org.example.school_project.repository.CharterRepository;
import org.example.school_project.service.CharterService;
import org.example.school_project.util.exception.AlreadyExistException;
import org.example.school_project.util.exception.DontHaveAccessException;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.CharterMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CharterServiceImpl implements CharterService {
    private final CharterRepository charterRepository;
    private final CharterMapper charterMapper;

    public Charter getCharterByIdEntity(Long id) {
        return charterRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Charter"));
    }

    public Charter save (Charter charter) {
        return charterRepository.save(charter);
    }

    @Override
    public CharterDto createCharter(CharterDtoRequest charterDtoR, Long authorId) {
        if(charterRepository.existsById(charterDtoR.getId()))
            throw new AlreadyExistException("Charter", "'id'");
        charterDtoR.setAuthorId(authorId);
        return charterMapper.entityToDto(save(charterMapper.dtoToEntity(charterDtoR)));
    }

    @Override
    public CharterDto updateCharter(CharterDtoRequest charterDtoR, Long authorId) {
        charterDtoR.setAuthorId(authorId);
        Charter oldCharter = charterMapper.dtoToEntity(charterDtoR);
        Charter newCharter = getCharterByIdEntity(charterDtoR.getId());

        if(!newCharter.getAuthorOfCharter().getId().equals(authorId))
            throw new DontHaveAccessException();

        newCharter.setTitle(oldCharter.getTitle());
        newCharter.setDescription(oldCharter.getDescription());
        newCharter.setCreationDate(oldCharter.getCreationDate());
        newCharter.setAuthorOfCharter(oldCharter.getAuthorOfCharter());
        return charterMapper.entityToDto(save(newCharter));
    }

    @Override
    public CharterDto deleteCharter(Long id) {
        Charter charter = getCharterByIdEntity(id);
        charter.setIsActive(false);
        return charterMapper.entityToDto(save(charter));
    }
    @Override
    public CharterDto restoreCharter(Long id) {
        Charter charter = getCharterByIdEntity(id);
        charter.setIsActive(true);
        return charterMapper.entityToDto(save(charter));
    }
    @Override
    public CharterDto deleteCharter(Long id, Long authorId) {
        Charter charter = getCharterByIdEntity(id);

        if (!charter.getAuthorOfCharter().getId().equals(authorId))
            throw new DontHaveAccessException();

        charter.setIsActive(false);
        return charterMapper.entityToDto(save(charter));
    }
    @Override
    public CharterDto restoreCharter(Long id, Long authorId) {
        Charter charter = getCharterByIdEntity(id);

        if (!charter.getAuthorOfCharter().getId().equals(authorId))
            throw new DontHaveAccessException();

        charter.setIsActive(true);
        return charterMapper.entityToDto(save(charter));
    }

    @Override
    public List<CharterDto> getAllCharter() {
        return charterMapper.entityToDtoList(charterRepository.findAll());
    }

    @Override
    public List<CharterDto> getAllCharterByAuthor(Long authorId) {
        List<CharterDto> charterDtoList = new ArrayList<>();
        for (CharterDto c : getAllCharter())
            if (c.getAuthorId().equals(authorId))
                charterDtoList.add(c);
        return charterDtoList;
    }

    @Override
    public List<CharterDto> filterActiveCharter(List<CharterDto> charterDtoList) {
        List<CharterDto> activeCharter = new ArrayList<>();
        for (CharterDto c : getAllCharter())
            if (c.getIsActive())
                charterDtoList.add(c);
        return activeCharter;
    }
}
