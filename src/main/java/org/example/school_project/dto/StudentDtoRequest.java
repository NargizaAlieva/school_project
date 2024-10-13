package org.example.school_project.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.example.school_project.enums.ParentStatus;

@Data
@AllArgsConstructor
public class StudentDtoRequest {
    private Long id;
    private Date birthday;
    private ParentStatus parentStatus;
    private Long userId;
    private Long parentId;
    private Long gradeId;

    public StudentDtoRequest(){}
}
