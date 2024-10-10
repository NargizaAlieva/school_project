package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class HomeworkDtoRequest {
    private Long id;
    private Integer mark;
    private String teacherReview;
    private Long lessonId;
    private Long studentId;

    public HomeworkDtoRequest(){}
}
