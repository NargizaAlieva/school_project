package org.example.school_project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private String review;
    private LocalDateTime creationDate;
    private Long authorId;
    private String authorName;
    private Long studentId;
    private String studentName;
    private String gradeName;
    private Boolean isActive;

    public ReviewDto(){}
}
