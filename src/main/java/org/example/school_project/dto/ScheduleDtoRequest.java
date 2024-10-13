package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleDtoRequest {
    private Long id;
    private String dayOfWeek;
    private Integer quarter;
    private String dueTime;
    private String year;
    private Boolean isApprove = false;
    private Long subjectId;
    private Long teacherId;
    private Long gradeId;
    public ScheduleDtoRequest(){}
}
