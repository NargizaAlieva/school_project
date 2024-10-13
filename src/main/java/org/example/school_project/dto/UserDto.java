package org.example.school_project.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phone;
    private String email;
    private LocalDateTime creationDate;
    private Boolean isActive;
    private Set<String> roleSet;

    public UserDto() {}
}
