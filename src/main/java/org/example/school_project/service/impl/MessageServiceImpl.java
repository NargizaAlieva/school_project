package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.MessageDto;
import org.example.school_project.dto.MessageDtoRequest;
import org.example.school_project.entity.Message;
import org.example.school_project.enums.MessageTheme;
import org.example.school_project.repository.MessageRepository;
import org.example.school_project.service.MessageService;
import org.example.school_project.util.exception.DontHaveAccessException;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.exception.WasDeletedException;
import org.example.school_project.util.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public Message getMessageByIdEntity(Long id) {
        return messageRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Message"));
    }
    public List<Message> getAllMessagesEntity(){
        return messageRepository.findAll();
    }
    public Message save(Message message) {
        return messageRepository.save(message);
    }
    public void markAsRead(MessageDto messageDto) {
        Message message = getMessageByIdEntity(messageDto.getId());
        message.setIsRead(true);
        save(message);
    }

    public void markAsRead(List<MessageDto> messageDtoList) {
        for(MessageDto m : messageDtoList) {
            markAsRead(m);
        }
    }

    @Override
    public MessageDto getMessageById(Long id, Long currentUserId) {
        MessageDto messageDto = messageMapper.entityToDto(messageRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Message")));
        if (!messageDto.getIsActive())
            throw new WasDeletedException("Message");
        if (messageDto.getReceiverId().equals(currentUserId) || messageDto.getAuthorId().equals(currentUserId))
            throw new DontHaveAccessException();
        markAsRead(messageDto);
        return messageDto;
    }
    @Override
    public MessageDto createMessage(MessageDtoRequest messageDtoRequest) {
        return messageMapper.entityToDto(save(messageMapper.dtoToEntity(messageDtoRequest)));
    }

    @Override
    public MessageDto markAsUnread(Long id, Long currentUserId) {
        Message message = getMessageByIdEntity(id);
        if (!message.getIsActive())
            throw new WasDeletedException("Message");
        if (message.getAuthorMessage().getId().equals(currentUserId) || message.getReceiverMessage().getId().equals(currentUserId))
            throw new DontHaveAccessException();
        message.setIsRead(false);
        return messageMapper.entityToDto(messageRepository.save(message));
    }

    @Override
    public MessageDto deleteMessage(Long messageId, Long userId) {
        Message message = getMessageByIdEntity(messageId);
        if (!(message.getReceiverMessage().getId().equals(userId)
                || message.getAuthorMessage().getId().equals(userId)))
            throw new DontHaveAccessException();
        message.setIsActive(false);
        return messageMapper.entityToDto(save(message));
    }

    @Override
    public MessageDto restoreMessage(Long messageId, Long userId) {
        Message message = getMessageByIdEntity(messageId);
        if (!(message.getReceiverMessage().getId().equals(userId)
                || message.getAuthorMessage().getId().equals(userId)))
            throw new DontHaveAccessException();
        message.setIsActive(true);
        return messageMapper.entityToDto(save(message));
    }

    @Override
    public List<MessageDto> getAllMessages(Long currentUserId) {
        List<Message> allMessage = new ArrayList<>();
        for (Message m : getAllMessagesEntity()) {
            if ((m.getReceiverMessage().getId().equals(currentUserId)
                    || m.getAuthorMessage().getId().equals(currentUserId)) && m.getIsActive()) allMessage.add(m);
        }
        markAsRead(messageMapper.entityToDtoList(allMessage));
        return messageMapper.entityToDtoList(allMessage);
    }

    @Override
    public List<MessageDto> getAllReceiverMessages(List<MessageDto> messageDtoList, Long receiverId) {
        List<MessageDto> allReceiverMessage = new ArrayList<>();
        for (MessageDto m : messageDtoList) {
            if (m.getReceiverId().equals(receiverId)) allReceiverMessage.add(m);
        }
        markAsRead(allReceiverMessage);
        return allReceiverMessage;
    }

    @Override
    public List<MessageDto> getAllAuthorMessages(List<MessageDto> messageDtoList, Long authorId) {
        List<MessageDto> allAuthorMessage = new ArrayList<>();
        for (MessageDto m : messageDtoList) {
            if (m.getReceiverId().equals(authorId)) allAuthorMessage.add(m);
        }
        markAsRead(allAuthorMessage);
        return allAuthorMessage;
    }

    @Override
    public List<MessageDto> getAllUnreadMessages(List<MessageDto> messageDtoList) {
        List<MessageDto> allUnreadMessage = new ArrayList<>();
        for (MessageDto m : messageDtoList) {
            if (!m.getIsRead()) allUnreadMessage.add(m);
        }
        markAsRead(allUnreadMessage);
        return allUnreadMessage;
    }

    @Override
    public List<MessageDto> getAllAdmissionApplication(List<MessageDto> messageDtoList, Long currentUserId) {
        List<MessageDto> allAdmissionMessage = new ArrayList<>();
        for (MessageDto m : messageDtoList) {
            if (m.getTheme().equals(MessageTheme.ADMISSION.name())) allAdmissionMessage.add(m);
        }
        markAsRead(allAdmissionMessage);
        return allAdmissionMessage;
    }

    @Override
    public List<MessageDto> getAllAppeal(List<MessageDto> messageDtoList, Long currentUserId) {
        List<MessageDto> allAppealMessage = new ArrayList<>();
        for (MessageDto m : messageDtoList) {
            if (m.getTheme().equals(MessageTheme.APPEAL.name())) allAppealMessage.add(m);
        }
        markAsRead(allAppealMessage);
        return allAppealMessage;
    }

    @Override
    public List<MessageDto> getAllNormal(List<MessageDto> messageDtoList, Long currentUserId) {
        List<MessageDto> allAppealMessage = new ArrayList<>();
        for (MessageDto m : messageDtoList) {
            if (m.getTheme().equals(MessageTheme.NORMAL.name())) allAppealMessage.add(m);
        }
        markAsRead(allAppealMessage);
        return allAppealMessage;
    }
}
