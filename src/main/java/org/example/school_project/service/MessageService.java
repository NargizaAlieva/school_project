package org.example.school_project.service;

import org.example.school_project.dto.MessageDto;
import org.example.school_project.dto.MessageDtoRequest;
import org.example.school_project.dto.StudentDto;

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

    List<MessageDto> getAllAdmissionApplication(List<MessageDto> messageDtoList);
    List<MessageDto> getAllAppeal(List<MessageDto> messageDtoList);
    List<MessageDto> getAllNormal(List<MessageDto> messageDtoList);

    void sendMessageForGradeStudents(List<StudentDto> studentDtoList, MessageDtoRequest messageDtoRequest);

    void sendMessageForGradeParent(List<StudentDto> studentDtoList, MessageDtoRequest messageDtoRequest);
}
