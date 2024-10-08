package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ParentDto {
    private Long id;
    private UserDto user;
    private List<String> childrenNameList;

    public ParentDto(){}
}
