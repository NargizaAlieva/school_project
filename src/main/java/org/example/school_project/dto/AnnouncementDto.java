package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.school_project.enums.CanSeeOnly;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AnnouncementDto {
    private Long id;
    private String title;
    private String description;
    private CanSeeOnly forWhom;
    private LocalDateTime creationDate;
    private Long authorId;
    private String authorName;

    public AnnouncementDto(){}
}
