package org.example.school_project.dto;

import lombok.*;
import org.example.school_project.entity.Role;

import java.time.LocalDateTime;
import java.util.Set;

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
    private Set<Role> roleSet;

    public UserDto() {}
}
