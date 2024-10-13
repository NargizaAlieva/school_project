package org.example.school_project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnnouncementDtoRequest {
    private Long id;
    private String title;
    private String description;
    private String forWhom;
    private LocalDateTime creationDate;
    private Long authorId;

    public AnnouncementDtoRequest(){}
}
