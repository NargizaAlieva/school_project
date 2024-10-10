package org.example.school_project.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.school_project.entity.Lesson;
import org.example.school_project.entity.Student;

import java.time.LocalDateTime;

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
