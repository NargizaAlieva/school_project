package org.example.school_project.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.example.school_project.enums.ParentStatus;

@Data
@AllArgsConstructor
public class StudentDto {
    private Long id;
    private Date birthday;
    private ParentStatus parentStatus;
    private Long parentId;
    private UserDto user;
    private String gradeTitle;

    public StudentDto(){}
}
