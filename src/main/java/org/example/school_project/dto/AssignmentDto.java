package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class AssignmentDto {
    private String assignment;
    private Boolean isDone;
    private Timestamp creationDate;

    public AssignmentDto(){}
}
