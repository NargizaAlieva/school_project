package org.example.school_project.service;

import org.example.school_project.dto.AssignmentDto;
import org.example.school_project.dto.AssignmentDtoRequest;
import org.example.school_project.entity.Assignment;

import java.util.List;

public interface AssignmentService {
    AssignmentDto createAssigment(AssignmentDtoRequest assignmentDtoRequest, Long authorId);
    AssignmentDto createAssigment(AssignmentDtoRequest assignmentDtoRequest);

    AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest, Long authorId);

    AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest);

    AssignmentDto deleteAssignment(Long id, Long authorId);
    AssignmentDto restoreAssignment(Long id, Long authorId);
    AssignmentDto markAsDone(Long id, Long authorId);

    List<AssignmentDto> getAllAssignment();
    List<AssignmentDto> getAllAssignmentByAuthor(Long authorId);
    List<AssignmentDto> getAllUndoneAssignment(List<AssignmentDto> assignmentDtoList);
    List<AssignmentDto> getAllUndoneAssignmentFrom(List<Long> ids);
    List<AssignmentDto> getAllDoneAssignment();
    List<AssignmentDto> getAllDoneAssignmentFrom(List<Long> ids);
    List<AssignmentDto> getAllAssignmentFromReceiver(Long Id);
    List<AssignmentDto> getAllAssignmentFromReceiver(List<Long> receiverIds);
}
