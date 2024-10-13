package org.example.school_project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GradeDtoRequest {
    private Long id;
    private String title;
    private LocalDateTime creationDate;
    private Long classTeacherId;
}
