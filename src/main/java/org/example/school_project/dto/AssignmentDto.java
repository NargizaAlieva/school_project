package org.example.school_project.dto;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

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
