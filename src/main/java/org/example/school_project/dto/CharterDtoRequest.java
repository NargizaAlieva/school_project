package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class CharterDtoRequest {
    private String title;
    private String description;
    private Timestamp creationDate;
    private Long authorId;

    public CharterDtoRequest(){}
}
