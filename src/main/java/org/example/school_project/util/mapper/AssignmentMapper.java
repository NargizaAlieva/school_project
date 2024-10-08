package org.example.school_project.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.AssignmentDto;
import org.example.school_project.dto.AssignmentDtoRequest;
import org.example.school_project.entity.Assignment;
import org.example.school_project.service.EmployeeService;
import org.example.school_project.service.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AssignmentMapper {
    private final UserService userService;
    private final EmployeeService employeeService;
    public AssignmentDto entityToDto(Assignment assignment) {
        AssignmentDto assignmentDto = new AssignmentDto();
        assignmentDto.setAssignment(assignment.getAssignment());
        assignmentDto.setIsDone(assignment.getIsDone());
        assignmentDto.setCreationDate(assignment.getCreationDate());
        return assignmentDto;
    }

    public List<AssignmentDto> entityToDtoList(List<Assignment> assignments) {
        return assignments.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Assignment dtoToEntity(AssignmentDtoRequest assignmentDtoRequest) {
        Assignment assignment = new Assignment();
        assignment.setId(assignmentDtoRequest.getId());
        assignment.setAssignment(assignmentDtoRequest.getAssignment());
        assignment.setIsDone(assignmentDtoRequest.getIsDone());
        assignment.setAuthorOfAssignments(employeeService.findByIdEntity(assignmentDtoRequest.getAuthorId()));
        assignment.setReceiverOfAssignments(userService.getEntityById(assignmentDtoRequest.getReceiverId()));
        return assignment;
    }

    public Assignment dtoToEntityForSecretary(AssignmentDtoRequest assignmentDtoRequest) {
        if(assignmentDtoRequest.getCreationDate() == null) assignmentDtoRequest.setCreationDate(LocalDateTime.now());
        Assignment assignment = new Assignment();
        assignment.setId(assignmentDtoRequest.getId());
        assignment.setAssignment(assignmentDtoRequest.getAssignment());
        assignment.setCreationDate(assignmentDtoRequest.getCreationDate());
        assignment.setIsDone(assignmentDtoRequest.getIsDone());
        return assignment;
    }
}
