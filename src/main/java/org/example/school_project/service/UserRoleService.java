package org.example.school_project.service;

import org.example.school_project.dto.MessageDto;
import org.example.school_project.dto.MessageDtoRequest;

import java.util.List;

public interface UserRoleService {
    MessageDto createMessage(MessageDtoRequest messageDtoRequest);
    MessageDto getMessageById(Long id);
    MessageDto markAsUnread(Long id);

    MessageDto deleteMessage(Long id);
    MessageDto restoreMessage(Long id);

    List<MessageDto> getAllMessages();
    List<MessageDto> getAllReceiverMessages();
    List<MessageDto> getAllAuthorMessages();

    List<MessageDto> getAllUnreadReceiverMessages();
    List<MessageDto> getAllUnreadAuthorMessages();
}
