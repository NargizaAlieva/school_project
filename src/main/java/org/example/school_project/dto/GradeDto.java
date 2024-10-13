package org.example.school_project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GradeDto {
    private Long id;
    private String title;
    private LocalDateTime creationDate;
    private String classTeacher;
    private Integer studentCount;
    private Boolean isActive;
    public GradeDto(){}
}
