package org.example.school_project.service;

import org.example.school_project.dto.MessageDto;
import org.example.school_project.dto.MessageDtoRequest;

import java.util.List;

public interface MessageService {
    MessageDto createMessage(MessageDtoRequest messageDtoRequest);
    MessageDto getMessageById(Long id);
    MessageDto markAsUnread(Long id);
    List<MessageDto> getAllMessages();
    List<MessageDto> getAllUnreadMessages();
    List<MessageDto> getAllAdmissionApplication();
    List<MessageDto> getAllUnreadAdmissionApplication();
    List<MessageDto> getAllAppeal();
    List<MessageDto> getAllUnreadAppeal();
}
