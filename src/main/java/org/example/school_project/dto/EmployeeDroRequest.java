package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class EmployeeDroRequest {
    private Long id;
    private String position;
    private Integer salary;
    private Long userId;
    private Set<Long> subjectSet = new HashSet<>();

    public EmployeeDroRequest(){}
}
