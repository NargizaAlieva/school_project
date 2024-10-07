package org.example.school_project.util.mapper;

import org.example.school_project.dto.SubjectDto;
import org.example.school_project.dto.SubjectDtoRequest;
import org.example.school_project.entity.Employee;
import org.example.school_project.entity.Subject;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SubjectMapper {
    public SubjectDto entityToDto(Subject subject) {
        Set<String> teacherSet = new HashSet<>();

        for (Employee teacher : subject.getTeachersSet()) {
            String fullName = teacher.getUser().getFirstName() + " " + teacher.getUser().getLastName();
            if (!teacher.getUser().getMiddleName().isEmpty()) {
                fullName = " " + teacher.getUser().getMiddleName();
            }
            teacherSet.add(fullName);
        }

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setTitle(subject.getTitle());
        subjectDto.setDescription(subject.getDescription());
        subjectDto.setTeachersSet(teacherSet);
        subjectDto.setIsActive(subject.getIsActive());
        return subjectDto;
    }
    public List<SubjectDto> entityToDtoList(List<Subject> subjects) {
        return subjects.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Set<SubjectDto> entityToDtoList(Set<Subject> subjects) {
        return subjects.stream().map(this::entityToDto).collect(Collectors.toSet());
    }

    public Subject dtoToEntity(SubjectDtoRequest subjectDtoRequest) {
        Subject subject = new Subject();
        subject.setId(subjectDtoRequest.getId());
        subject.setTitle(subjectDtoRequest.getTitle());
        subject.setDescription(subjectDtoRequest.getDescription());
        subject.setIsActive(subjectDtoRequest.getIsActive());
        return subject;
    }
}
