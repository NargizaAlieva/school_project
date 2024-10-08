package org.example.school_project.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.GradeDto;
import org.example.school_project.dto.GradeDtoRequest;
import org.example.school_project.dto.MessageDto;
import org.example.school_project.dto.MessageDtoRequest;
import org.example.school_project.entity.Grade;
import org.example.school_project.entity.Message;
import org.example.school_project.entity.User;
import org.example.school_project.enums.MessageTheme;
import org.example.school_project.service.EmployeeService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GradeMapper {
    private final EmployeeService employeeService;
    public GradeDto entityToDto(Grade grade) {
        User teacher = grade.getClassTeacher().getUser();
        String teacherFullName = teacher.getFirstName() + teacher.getLastName();
        if (teacher.getMiddleName() != null) teacherFullName = " " + teacher.getMiddleName();

        GradeDto gradeDto = new GradeDto();
        gradeDto.setId(grade.getId());
        gradeDto.setTitle(grade.getTitle());
        gradeDto.setClassTeacher(teacherFullName);
        gradeDto.setStudentCount(grade.getStudentSet().size());
        gradeDto.setCreationDate(grade.getCreationDate());
        return gradeDto;
    }

    public List<GradeDto> entityToDtoList(List<Grade> grades) {
        return grades.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Grade dtoToEntity(GradeDtoRequest gradeDtoRequest) {
        Grade grade = new Grade();
        grade.setId(gradeDtoRequest.getId());
        grade.setTitle(gradeDtoRequest.getTitle());
        grade.setClassTeacher(employeeService.findByIdEntity(gradeDtoRequest.getId()));

        if(gradeDtoRequest.getCreationDate() == null) grade.setCreationDate(LocalDateTime.now());
        else grade.setCreationDate(gradeDtoRequest.getCreationDate());
        return grade;
    }
}
