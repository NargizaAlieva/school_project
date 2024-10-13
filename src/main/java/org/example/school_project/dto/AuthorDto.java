package org.example.school_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.example.school_project.enums.AuthorStatus;

@Data
@AllArgsConstructor
public class AuthorDto {
    private Long id;
    private String username;
    private String fullName;
    private AuthorStatus status;

    public AuthorDto(){}
}
