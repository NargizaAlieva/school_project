package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

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
