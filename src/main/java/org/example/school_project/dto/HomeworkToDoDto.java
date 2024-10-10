package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class HomeworkToDoDto {
    private Long lessonId;
    private String lessonTopic;
    private String subjectTitle;
    private String homework;
    private LocalDateTime creationDate;

    public HomeworkToDoDto(){}
}
