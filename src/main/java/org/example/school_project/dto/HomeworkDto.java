package org.example.school_project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HomeworkDto {
    private Long id;
    private Integer mark;
    private String teacherReview;
    private Boolean isChecked;
    private LocalDateTime creationDate;
    private Long lessonId;
    private String lessonTopic;
    private Long studentId;
    private String studentName;

    public HomeworkDto(){}
}
