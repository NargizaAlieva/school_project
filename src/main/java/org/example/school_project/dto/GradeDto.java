package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

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
