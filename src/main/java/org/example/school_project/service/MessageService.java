package org.example.school_project.service;

import org.example.school_project.dto.MessageDto;
import org.example.school_project.dto.MessageDtoRequest;

import java.util.List;

public interface MessageService {
    MessageDto getMessageById(Long id, Long currentUserId);
    MessageDto createMessage(MessageDtoRequest messageDtoRequest);
    MessageDto markAsUnread(Long id, Long currentUserId);

    MessageDto deleteMessage(Long messageId, Long userId);
    MessageDto restoreMessage(Long messageId, Long userId);

    List<MessageDto> getAllMessages(Long currentUserId);
    List<MessageDto> getAllReceiverMessages(List<MessageDto> messageDtoList, Long receiverId);
    List<MessageDto> getAllAuthorMessages(List<MessageDto> messageDtoList, Long authorId);
    List<MessageDto> getAllUnreadMessages(List<MessageDto> messageDtoList);

    List<MessageDto> getAllAdmissionApplication(List<MessageDto> messageDtoList, Long currentUserId);
    List<MessageDto> getAllAppeal(List<MessageDto> messageDtoList, Long currentUserId);
    List<MessageDto> getAllNormal(List<MessageDto> messageDtoList, Long currentUserId);
}
