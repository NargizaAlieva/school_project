package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LessonDtoRequest {
    private Long id;
    private String topic;
    private String homework;
    private LocalDateTime creationDate;
    private Long scheduleId;

    public LessonDtoRequest(){}
}
