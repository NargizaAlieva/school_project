package org.example.school_project.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class DutyListDtoRequest {
    private Long id;
    private Date dutyDate;
    private Long studentId;
    private Long gradeId;

    public DutyListDtoRequest(){}
}
