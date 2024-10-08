package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MessageDto {
    private Long id;
    private String theme;
    private String title;
    private String message;
    private Boolean isRead = false;
    private LocalDateTime creationDate;
    private String authorName;

    public MessageDto(){}
}
