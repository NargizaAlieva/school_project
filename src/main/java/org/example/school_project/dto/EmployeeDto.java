package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.school_project.entity.*;

import java.util.Set;

@Data
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String position;
    private Integer salary;
    private UserDto userDto;
    private Set<Subject> subjectSet;

    public EmployeeDto(){}

}
