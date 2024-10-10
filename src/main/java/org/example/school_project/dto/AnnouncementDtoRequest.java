package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.school_project.enums.CanSeeOnly;

import java.time.LocalDateTime;

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
