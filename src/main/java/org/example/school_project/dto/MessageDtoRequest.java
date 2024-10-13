package org.example.school_project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

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
