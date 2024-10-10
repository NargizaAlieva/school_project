package org.example.school_project.service;

import org.example.school_project.dto.CharterDto;
import org.example.school_project.dto.CharterDtoRequest;

import java.util.List;

public interface CharterService {
    CharterDto createCharter(CharterDtoRequest charterDtoR, Long authorId);

    CharterDto updateCharter(CharterDtoRequest charterDtoR, Long authorId);

    CharterDto deleteCharter(Long id);

    CharterDto restoreCharter(Long id);

    List<CharterDto> getAllCharter ();

    List<CharterDto> getAllCharterByAuthor(Long authorId);

    List<CharterDto> filterActiveCharter(List<CharterDto> charterDtoList);
}
