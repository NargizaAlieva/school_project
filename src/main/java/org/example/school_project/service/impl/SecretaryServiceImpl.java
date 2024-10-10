package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.AssignmentDto;
import org.example.school_project.dto.MessageDto;
import org.example.school_project.service.AssignmentService;
import org.example.school_project.service.MessageService;
import org.example.school_project.service.SecretaryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecretaryServiceImpl implements SecretaryService {
    private final AssignmentService assignmentService;
    private final MessageService messageService;
    @Override
    public AssignmentDto markAsDone(Long id) {
        return assignmentService.markAsDone(id);
    }

    @Override
    public List<AssignmentDto> getAllAssignment() {
        return assignmentService.getAllAssignment();
    }

    @Override
    public List<AssignmentDto> getAllUndoneAssignment() {
        return assignmentService.getAllUndoneAssignment();
    }
}
