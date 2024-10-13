package org.example.school_project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDto {
    private Long id;
    private String theme;
    private String title;
    private String message;
    private Boolean isRead = false;
    private LocalDateTime creationDate;
    private Long receiverId;
    private Long authorId;
    private String authorName;
    private Boolean isActive = true;

    public MessageDto(){}
}
