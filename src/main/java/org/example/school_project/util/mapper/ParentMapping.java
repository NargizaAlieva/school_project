package org.example.school_project.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.ParentDto;
import org.example.school_project.dto.ParentDtoRequest;
import org.example.school_project.entity.Parent;
import org.example.school_project.entity.Student;
import org.example.school_project.service.UserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ParentMapping {
    private final UserMapper userMapper;
    private final UserService userService;
    public ParentDto entityToDto(Parent parent) {
        List<Student> studentList = parent.getChildrenList();
        List<String> childrenName = new ArrayList<>();

        String fullName;
        for (Student s : studentList) {
            fullName = s.getUser().getFirstName() + " " + s.getUser().getLastName();
            if (s.getUser().getMiddleName() != null)
                fullName += " " + s.getUser().getMiddleName();
            childrenName.add(fullName);
        }

        ParentDto parentDto = new ParentDto();
        parentDto.setId(parent.getId());
        parentDto.setUser(userMapper.entityToDto(parent.getUser()));
        parentDto.setChildrenNameList(childrenName);
        return parentDto;
    }

    public List<ParentDto> entityToDtoList(List<Parent> parents) {
        return parents.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Parent dtoToEntity(ParentDtoRequest parentDtoRequest) {
        Parent parent = new Parent();
        parent.setId(parentDtoRequest.getId());
        parent.setUser(userService.getEntityById(parentDtoRequest.getUserId()));
        return parent;
    }
}
