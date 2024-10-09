package org.example.school_project.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.school_project.entity.Lesson;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AttendanceDto {
    private Long id;
    private Boolean isAttended;
    private Long studentId;
    private String studentName;
    private Long subjectId;
    private String subjectTitle;
    private Long teacherId;
    private String teacherName;
    private Long lessonId;
    private String lessonTopic;
    private LocalDateTime lessonDate;

    public AttendanceDto(){}
}
