package org.example.school_project.dto;

import jakarta.persistence.*;
import org.example.school_project.entity.User;

import java.time.LocalDateTime;

public class MessageDtoRequest {
    private Long id;
    private String theme;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private Long receiverId;
}
