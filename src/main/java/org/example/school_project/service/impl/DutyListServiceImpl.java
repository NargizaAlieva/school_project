package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.DutyListDto;
import org.example.school_project.dto.DutyListDtoRequest;
import org.example.school_project.entity.DutyList;
import org.example.school_project.repository.DutyListRepository;
import org.example.school_project.service.DutyListService;
import org.example.school_project.util.exception.AlreadyExistException;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.DutyListMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DutyListServiceImpl implements DutyListService {
    private final DutyListRepository dutyListRepository;
    private final DutyListMapper dutyListMapper;

    private DutyList save(DutyList dutyList) {
        return dutyListRepository.save(dutyList);
    }
    @Override
    public DutyList getDutyListByIdEntity(Long id) {
        return dutyListRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Duty List"));
    }
    @Override
    public DutyListDto getDutyListById(Long id) {
        return dutyListMapper.entityToDto(getDutyListByIdEntity(id));
    }

    @Override
    public DutyListDto createDutyList(DutyListDtoRequest dutyListDtoRequest) {
        if (dutyListRepository.existsById(dutyListDtoRequest.getId()))
            throw new AlreadyExistException("Duty List", "'id'");
        return dutyListMapper.entityToDto(save(dutyListMapper.dtoToEntity(dutyListDtoRequest)));
    }

    @Override
    public DutyListDto updateDutyList(DutyListDtoRequest dutyListDtoRequest) {
        DutyList oldDutyList = dutyListMapper.dtoToEntity(dutyListDtoRequest);
        DutyList newDutyList = getDutyListByIdEntity(dutyListDtoRequest.getId());

        newDutyList.setDutyDate(oldDutyList.getDutyDate());
        newDutyList.setStudentDuty(oldDutyList.getStudentDuty());
        newDutyList.setGradeDuty(oldDutyList.getGradeDuty());
        return dutyListMapper.entityToDto(save(newDutyList));
    }

    @Override
    public List<DutyListDto> getAllDutyList() {
        return dutyListMapper.entityToDtoList(dutyListRepository.findAll());
    }

    @Override
    public List<DutyListDto> getAllDutyListByStudent(Long studentId) {
        List<DutyListDto> dutyListDtoList = new ArrayList<>();
        for (DutyListDto d : getAllDutyList())
            if (d.getStudentId().equals(studentId))
                dutyListDtoList.add(d);
        return dutyListDtoList;
    }

    @Override
    public List<DutyListDto> getAllDutyListByGrade(Long gradeId) {
        List<DutyListDto> dutyListDtoList = new ArrayList<>();
        for (DutyListDto d : getAllDutyList())
            if (d.getGradeId().equals(gradeId))
                dutyListDtoList.add(d);
        return dutyListDtoList;
    }
}
