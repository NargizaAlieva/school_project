package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttendanceDtoRequest {
    private Long id;
    private Boolean isAttended;
    private Long studentId;
    private Long lessonId;

    public AttendanceDtoRequest(){}
}
