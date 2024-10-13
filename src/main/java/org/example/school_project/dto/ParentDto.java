package org.example.school_project.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParentDto {
    private Long id;
    private UserDto user;
    private List<String> childrenNameList;

    public ParentDto(){}
}
