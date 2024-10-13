package org.example.school_project.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubjectDto {
    private Long id;
    private String title;
    private String description;
    private Boolean isActive;
    private Set<String> teachersSet;

    public SubjectDto() {}
}
