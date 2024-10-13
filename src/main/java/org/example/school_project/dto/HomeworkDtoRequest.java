package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HomeworkDtoRequest {
    private Long id;
    private Long lessonId;
    private Long studentId;

    public HomeworkDtoRequest(){}
}
