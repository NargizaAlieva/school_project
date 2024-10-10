package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AssignmentDto {
    private Long id;
    private String assignment;
    private Boolean isDone;
    private Long receiverId;
    private String receiverName;
    private Long authorId;
    private String authorName;
    private LocalDateTime creationDate;

    public AssignmentDto(){}
}
