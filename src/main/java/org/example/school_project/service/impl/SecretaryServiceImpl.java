package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.AssignmentDto;
import org.example.school_project.dto.CharterDto;
import org.example.school_project.dto.CharterDtoRequest;
import org.example.school_project.dto.MessageDto;
import org.example.school_project.entity.Employee;
import org.example.school_project.service.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecretaryServiceImpl implements SecretaryService {
    private final AssignmentService assignmentService;
    private final MessageService messageService;
    private final EmployeeService employeeService;
    private final UserService userService;
    private final CharterService charterService;

    private Employee getCurrentEmployee() {
        return employeeService.getByUserId(userService.getCurrentUser().getId());
    }
    @Override
    public AssignmentDto markAsDone(Long id) {
        return assignmentService.markAsDone(id, getCurrentEmployee().getUser().getId());
    }

    @Override
    public List<AssignmentDto> getAllAssignment() {
        return assignmentService.getAllAssignmentFromReceiver(getCurrentEmployee().getUser().getId());
    }

    @Override
    public List<AssignmentDto> getAllUndoneAssignment() {
        return assignmentService.getAllUndoneAssignment(getAllAssignment());
    }

    @Override
    public MessageDto getMessageById(Long id) {
        return messageService.getMessageById(id, getCurrentEmployee().getUser().getId());
    }

    @Override
    public List<MessageDto> getAllMessages() {
        return messageService.getAllMessages(getCurrentEmployee().getUser().getId());
    }

    @Override
    public List<MessageDto> getAllReceiverMessages(){
        return messageService.getAllReceiverMessages(getAllMessages(), getCurrentEmployee().getUser().getId());
    }
    @Override
    public List<MessageDto> getAllAdmissionApplication() {
        return messageService.getAllAdmissionApplication(getAllReceiverMessages());
    }
    @Override
    public List<MessageDto> getAllUnreadAdmissionApplication() {
        return messageService.getAllUnreadMessages(getAllAdmissionApplication());
    }

    @Override
    public List<MessageDto> getAllAppeal() {
        return messageService.getAllAppeal(getAllReceiverMessages());
    }

    @Override
    public List<MessageDto> getAllUnreadAppeal() {
        return messageService.getAllUnreadMessages(getAllAppeal());
    }

    @Override
    public CharterDto createCharter(CharterDtoRequest charterDtoR) {
        return charterService.createCharter(charterDtoR, getCurrentEmployee().getId());
    }

    @Override
    public CharterDto updateCharter(CharterDtoRequest charterDtoR) {
        return charterService.updateCharter(charterDtoR, getCurrentEmployee().getId());
    }

    @Override
    public CharterDto deleteCharter(Long id) {
        return charterService.deleteCharter(id);
    }

    @Override
    public CharterDto restoreCharter(Long id) {
        return charterService.restoreCharter(id);
    }

}
