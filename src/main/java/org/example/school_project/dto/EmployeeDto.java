package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String position;
    private Integer salary;
    private UserDto userDto;
    private String subjectSet;

    public EmployeeDto(){}

}
