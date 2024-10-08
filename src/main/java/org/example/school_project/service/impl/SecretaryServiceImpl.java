package org.example.school_project.service.impl;

import org.example.school_project.dto.AssignmentDto;
import org.example.school_project.dto.MessageDto;
import org.example.school_project.service.SecretaryService;

import java.util.List;

public class SecretaryServiceImpl implements SecretaryService {
    @Override
    public AssignmentDto markAsDone(Long id) {
        return null;
    }

    @Override
    public List<AssignmentDto> getAllAssignment() {
        return null;
    }

    @Override
    public List<AssignmentDto> getAllUndoneAssignment() {
        return null;
    }

    @Override
    public MessageDto getMessageById(Long id) {
        return null;
    }

    @Override
    public List<MessageDto> getAllAdmissionApplication() {
        return null;
    }

    @Override
    public List<MessageDto> getAllUnreadAdmissionApplication() {
        return null;
    }

    @Override
    public List<MessageDto> getAllAppeal() {
        return null;
    }

    @Override
    public List<MessageDto> getAllUnreadAppeal() {
        return null;
    }
}
