package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class HomeworkDto {
    private Long id;
    private Integer mark;
    private String teacherReview;
    private Boolean isDone;
    private LocalDateTime creationDate;
    private Long lessonId;
    private String lessonTopic;
    private Long studentId;
    private String studentName;

    public HomeworkDto(){}
}
