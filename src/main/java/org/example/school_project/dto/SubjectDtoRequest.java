package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubjectDtoRequest {
    private Long id;
    private String title;
    private String description;
    private Boolean isActive;

    public SubjectDtoRequest(){}
}
