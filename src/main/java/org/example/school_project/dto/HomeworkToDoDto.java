package org.example.school_project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

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
