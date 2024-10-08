package org.example.school_project.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.CharterDto;
import org.example.school_project.dto.CharterDtoRequest;
import org.example.school_project.entity.Charter;
import org.example.school_project.entity.Employee;
import org.example.school_project.entity.User;
import org.example.school_project.service.EmployeeService;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CharterMapper {
    public final EmployeeService employeeService;
    public CharterDto entityToDto(Charter charter) {
        User author = charter.getAuthorOfCharter().getUser();
        String authorFullName = author.getFirstName() + " " + author.getLastName();
        if (author.getMiddleName() != null) authorFullName = " " + author.getMiddleName();

        CharterDto charterDto = new CharterDto();
        charterDto.setId(charter.getId());
        charterDto.setTitle(charter.getTitle());
        charterDto.setDescription(charter.getDescription());
        charterDto.setAuthorName(authorFullName);
        charterDto.setCreationDate(author.getCreationDate());
        return charterDto;
    }
    public List<CharterDto> entityToDtoList(List<Charter> charters) {
        return charters.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Charter dtoToEntity(CharterDtoRequest charterDtoRequest) {
        if(charterDtoRequest.getCreationDate() == null) charterDtoRequest.setCreationDate(LocalDateTime.now());
        Employee employee = employeeService.findByIdEntity(charterDtoRequest.getAuthorId());
        Charter charter = new Charter();
        charter.setId(charterDtoRequest.getId());
        charter.setTitle(charterDtoRequest.getTitle());
        charter.setDescription(charterDtoRequest.getDescription());
        charter.setAuthorOfCharter(employee);
        charter.setCreationDate(charterDtoRequest.getCreationDate());
        return charter;
    }
}
