package org.example.school_project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CharterDtoRequest {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private Long authorId;

    public CharterDtoRequest(){}
}
