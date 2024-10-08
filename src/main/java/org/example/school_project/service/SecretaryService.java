package org.example.school_project.service;

import org.example.school_project.dto.AssignmentDto;
import org.example.school_project.dto.MessageDto;

import java.util.List;

public interface SecretaryService {
    AssignmentDto markAsDone(Long id);
    List<AssignmentDto> getAllAssignment();
    List<AssignmentDto> getAllUndoneAssignment();

    MessageDto getMessageById(Long id);
    List<MessageDto> getAllAdmissionApplication();
    List<MessageDto> getAllUnreadAdmissionApplication();
    List<MessageDto> getAllAppeal();
    List<MessageDto> getAllUnreadAppeal();
}
