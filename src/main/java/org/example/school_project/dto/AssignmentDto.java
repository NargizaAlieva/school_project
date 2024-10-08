package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AssignmentDto {
    private String assignment;
    private Boolean isDone;
    private LocalDateTime creationDate;

    public AssignmentDto(){}
}
