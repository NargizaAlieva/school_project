package org.example.school_project.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.school_project.entity.Student;
import org.example.school_project.entity.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private String review;
    private LocalDateTime creationDate;
    private String authorId;
    private String studentId;
    private Boolean isActive;

    public ReviewDto(){}
}
