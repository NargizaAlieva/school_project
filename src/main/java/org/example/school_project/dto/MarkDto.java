package org.example.school_project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MarkDto {
    private Long id;
    private Integer mark;
    private Long studentId;
    private String studentName;
    private Long subjectId;
    private String subjectTitle;
    private Long teacherId;
    private String teacherTitle;
    private Long lessonId;
    private String lessonTopic;
    private LocalDateTime gottenDate;

    public MarkDto(){}
}
