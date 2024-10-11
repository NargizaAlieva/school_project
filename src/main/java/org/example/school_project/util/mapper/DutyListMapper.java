package org.example.school_project.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.CharterDto;
import org.example.school_project.dto.DutyListDto;
import org.example.school_project.dto.DutyListDtoRequest;
import org.example.school_project.entity.Charter;
import org.example.school_project.entity.DutyList;
import org.example.school_project.entity.Student;
import org.example.school_project.service.GradeService;
import org.example.school_project.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DutyListMapper {
    private final StudentService studentService;
    private final GradeService gradeService;
    public DutyListDto entityToDto(DutyList dutyList) {
        String studentFullName = dutyList.getStudentDuty().getUser().getFirstName() + " " + dutyList.getStudentDuty().getUser().getLastName();
        if (dutyList.getStudentDuty().getUser().getMiddleName() != null) studentFullName = " " + dutyList.getStudentDuty().getUser().getMiddleName();

        DutyListDto dutyListDto = new DutyListDto();
        dutyListDto.setId(dutyList.getId());
        dutyListDto.setDutyDate(dutyList.getDutyDate());
        dutyListDto.setStudentId(dutyList.getStudentDuty().getId());
        dutyListDto.setStudentName(studentFullName);
        dutyListDto.setGradeId(dutyList.getGradeDuty().getId());
        dutyListDto.setGradeTitle(dutyList.getGradeDuty().getTitle());
        return dutyListDto;
    }

    public List<DutyListDto> entityToDtoList(List<DutyList> dutyLists) {
        return dutyLists.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public DutyList dtoToEntity(DutyListDtoRequest dutyListDtoRequest) {
        DutyList dutyList = new DutyList();
        dutyList.setId(dutyListDtoRequest.getId());
        dutyList.setDutyDate(dutyListDtoRequest.getDutyDate());
        dutyList.setStudentDuty(studentService.getStudentByIdEntity(dutyListDtoRequest.getStudentId()));
        dutyList.setGradeDuty(gradeService.getByIdEntity(dutyListDtoRequest.getGradeId()));
        return dutyList;
    }
}
