package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LessonDto {
    private Long id;
    private String topic;
    private String homework;
    private LocalDateTime creationDate;
    private Long scheduleId;
    private Long subjectId;
    private String subjectTitle;
    private Long teacherId;
    private String teacherName;
    private Long gradeId;
    private String gradeTitle;

    public LessonDto(){}
}
