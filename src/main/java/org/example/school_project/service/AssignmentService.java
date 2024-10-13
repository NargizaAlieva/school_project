package org.example.school_project.service;

import org.example.school_project.dto.AssignmentDto;
import org.example.school_project.dto.AssignmentDtoRequest;

import java.util.List;

public interface AssignmentService {
    AssignmentDto createAssigment(AssignmentDtoRequest assignmentDtoRequest, Long authorId);
    AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest, Long authorId);

    AssignmentDto deleteAssignment(Long id, Long authorId);
    AssignmentDto restoreAssignment(Long id, Long authorId);
    AssignmentDto markAsDone(Long id, Long authorId);

    List<AssignmentDto> getAllAssignment();
    List<AssignmentDto> getAllAssignmentByAuthor(Long authorId);
    List<AssignmentDto> getAllUndoneAssignment(List<AssignmentDto> assignmentDtoList);
    List<AssignmentDto> getAllDoneAssignment(List<AssignmentDto> assignmentDtoList);

    List<AssignmentDto> getAllAssignmentFromReceiver(Long Id);
    List<AssignmentDto> getAllAssignmentFromReceiver(List<Long> receiverIds);
}
