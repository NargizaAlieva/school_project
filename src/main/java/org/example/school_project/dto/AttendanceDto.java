package org.example.school_project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

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
