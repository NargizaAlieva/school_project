package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewDtoRequest {
    private Long id;
    private String review;
    private LocalDateTime creationDate;
    private Long authorId;
    private Long studentId;

    public ReviewDtoRequest(){}
}
