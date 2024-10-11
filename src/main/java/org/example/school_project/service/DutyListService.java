package org.example.school_project.service;

import org.example.school_project.dto.DutyListDto;
import org.example.school_project.dto.DutyListDtoRequest;
import org.example.school_project.entity.DutyList;

import java.util.List;

public interface DutyListService {
    DutyList getDutyListByIdEntity(Long id);

    DutyListDto getDutyListById(Long id);

    DutyListDto createDutyList(DutyListDtoRequest dutyListDtoRequest);
    DutyListDto updateDutyList(DutyListDtoRequest dutyListDtoRequest);

    List<DutyListDto> getAllDutyList();
    List<DutyListDto> getAllDutyListByStudent(Long studentId);
    List<DutyListDto> getAllDutyListByGrade(Long gradeId);
}
