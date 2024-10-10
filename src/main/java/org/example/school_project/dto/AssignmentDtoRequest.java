package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AssignmentDtoRequest {
    private Long id;
    private String assignment;
    private Boolean isDone = false;
    private LocalDateTime creationDate;
    private Long authorId;
    private Long receiverId;

    public AssignmentDtoRequest(){}
}
