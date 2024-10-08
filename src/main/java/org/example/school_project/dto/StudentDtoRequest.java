package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.school_project.enums.ParentStatus;

import java.sql.Date;

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
