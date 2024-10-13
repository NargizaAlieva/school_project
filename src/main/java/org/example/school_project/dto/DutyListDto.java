package org.example.school_project.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class DutyListDto {
    private Long id;
    private Date dutyDate;
    private Long studentId;
    private String studentName;
    private Long gradeId;
    private String gradeTitle;

    public DutyListDto(){}
}
