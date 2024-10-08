package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MessageDtoRequest {
    private Long id;
    private String theme;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private Long receiverId;

    public MessageDtoRequest() {}
}
