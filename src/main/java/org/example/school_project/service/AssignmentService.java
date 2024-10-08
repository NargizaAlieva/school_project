package org.example.school_project.service;

import org.example.school_project.dto.AssignmentDto;
import org.example.school_project.dto.AssignmentDtoRequest;
import org.example.school_project.entity.Assignment;

import java.util.List;

public interface AssignmentService {

    AssignmentDto createAssigment(AssignmentDtoRequest assignmentDtoRequest);
//    AssignmentDto createToSecreter(AssignmentDtoRequest assignmentDtoRequest);
//    AssignmentDto createToClassRepresent(AssignmentDtoRequest assignmentDtoRequest);
    AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest);
    AssignmentDto markAsDone(Long id);
    List<AssignmentDto> getAllAssignment();
    List<AssignmentDto> getAllUndoneAssignment();
}
