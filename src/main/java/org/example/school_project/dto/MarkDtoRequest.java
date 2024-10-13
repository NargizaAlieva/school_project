package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MarkDtoRequest {
    private Long id;
    private Integer mark;
    private Long studentId;
    private Long lessonId;

    public MarkDtoRequest(){}
}
