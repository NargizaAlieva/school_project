package org.example.school_project.dto;

import java.util.Set;
import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Data;

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
