package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.example.school_project.enums.DaysOfWeek;

@Data
@AllArgsConstructor
public class ScheduleDto {
    private Long id;
    private DaysOfWeek dayOfWeek;
    private Integer quarter;
    private String dueTime;
    private String year;
    private Boolean isApprove;
    private Long subjectId;
    private String subjectTitle;
    private String teacherName;
    private Long gradeId;
    private String gradeName;
    private Boolean isActive;

    public ScheduleDto(){}
}
