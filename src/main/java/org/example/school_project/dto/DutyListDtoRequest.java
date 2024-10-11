package org.example.school_project.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class DutyListDtoRequest {
    private Long id;
    private Date dutyDate;
    private Long studentId;
    private Long gradeId;

    public DutyListDtoRequest(){}
}
