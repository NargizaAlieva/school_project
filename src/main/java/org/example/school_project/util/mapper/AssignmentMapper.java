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

        String authorFullName = assignment.getAuthorOfAssignments().getUser().getFirstName() + " " + assignment.getAuthorOfAssignments().getUser().getLastName();
        if (assignment.getAuthorOfAssignments().getUser().getMiddleName() != null) authorFullName = " " + assignment.getAuthorOfAssignments().getUser().getMiddleName();

        String receiverFullName = assignment.getReceiverOfAssignments().getFirstName() + " " + assignment.getReceiverOfAssignments().getLastName();
        if (assignment.getReceiverOfAssignments().getMiddleName() != null) receiverFullName = " " + assignment.getReceiverOfAssignments().getMiddleName();

        assignmentDto.setId(assignment.getId());
        assignmentDto.setAssignment(assignment.getAssignment());
        assignmentDto.setIsDone(assignment.getIsDone());
        assignmentDto.setCreationDate(assignment.getCreationDate());
        assignmentDto.setAuthorId(assignment.getAuthorOfAssignments().getId());
        assignmentDto.setAuthorName(authorFullName);
        assignmentDto.setReceiverId(assignment.getAuthorOfAssignments().getId());
        assignmentDto.setReceiverName(receiverFullName);
        return assignmentDto;
    }

    public List<AssignmentDto> entityToDtoList(List<Assignment> assignments) {
        return assignments.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Assignment dtoToEntity(AssignmentDtoRequest assignmentDtoRequest) {
        if(assignmentDtoRequest.getCreationDate() == null) assignmentDtoRequest.setCreationDate(LocalDateTime.now());
        Assignment assignment = new Assignment();
        assignment.setId(assignmentDtoRequest.getId());
        assignment.setAssignment(assignmentDtoRequest.getAssignment());
        assignment.setIsDone(assignmentDtoRequest.getIsDone());
        assignment.setCreationDate(assignmentDtoRequest.getCreationDate());
        assignment.setAuthorOfAssignments(employeeService.findByIdEntity(assignmentDtoRequest.getAuthorId()));
        assignment.setReceiverOfAssignments(userService.getEntityById(assignmentDtoRequest.getReceiverId()));
        return assignment;
    }
}
