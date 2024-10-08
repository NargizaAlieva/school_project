package org.example.school_project.service;

import org.example.school_project.dto.CharterDto;
import org.example.school_project.dto.CharterDtoRequest;

import java.util.List;

public interface CharterService {
    CharterDto createCharter (CharterDtoRequest charterDtoR);
    CharterDto updateCharter (CharterDtoRequest charterDtoR);
    List<CharterDto> getAllCharter ();
}
