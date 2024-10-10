package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CharterDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private Long authorId;
    private String authorName;
    private Boolean isActive;

    public CharterDto(){}
}
