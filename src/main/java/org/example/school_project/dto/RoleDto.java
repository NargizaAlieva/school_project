package org.example.school_project.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleDto {
    private Long userId;
    private Set<Long> roleIdSet;

    public RoleDto(){}
}
