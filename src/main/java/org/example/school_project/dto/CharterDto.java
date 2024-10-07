package org.example.school_project.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class CharterDto {
    private Long id;
    private String title;
    private String description;
    private Timestamp creationDate;
    private String authorName;

    public CharterDto(){}
}
