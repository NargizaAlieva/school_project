package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.MessageDto;
import org.example.school_project.dto.MessageDtoRequest;
import org.example.school_project.entity.Message;
import org.example.school_project.enums.MessageTheme;
import org.example.school_project.repository.MessageRepository;
import org.example.school_project.service.MessageService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Override
    public MessageDto createMessage(MessageDtoRequest messageDtoRequest) {
        return messageMapper.entityToDto(messageRepository.save(messageMapper.dtoToEntity(messageDtoRequest)));
    }
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    public MessageDto getMessageById(Long id) {
        MessageDto messageDto = messageMapper.entityToDto(messageRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Message")));
        markAsRead(messageDto);
        return messageDto;
    }

    public Message getMessageByIdEntity(Long id) {
        return messageRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Message"));
    }

    @Override
    public MessageDto markAsUnread(Long id) {
        Message message = messageRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Message"));
        message.setIsRead(false);
        return messageMapper.entityToDto(messageRepository.save(message));
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
    public List<MessageDto> getAllMessages() {
        List<MessageDto> allMessage = messageMapper.entityToDtoList(messageRepository.findAll());
        markAsRead(allMessage);
        return allMessage;
    }

    @Override
    public List<MessageDto> getAllUnreadMessages() {
        List<MessageDto> allUnreadMessage = new ArrayList<>();
        for (MessageDto m : getAllMessages()) {
            if (!m.getIsRead()) allUnreadMessage.add(m);
        }
        markAsRead(allUnreadMessage);
        return allUnreadMessage;
    }

    @Override
    public List<MessageDto> getAllAdmissionApplication() {
        List<MessageDto> allAdmissionMessage = new ArrayList<>();
        for (MessageDto m : getAllMessages()) {
            if (m.getTheme().equals(MessageTheme.ADMISSION.name())) allAdmissionMessage.add(m);
        }
        markAsRead(allAdmissionMessage);
        return allAdmissionMessage;
    }

    @Override
    public List<MessageDto> getAllUnreadAdmissionApplication() {
        List<MessageDto> allUnreadAdmissionMessage = new ArrayList<>();
        for (MessageDto m : getAllUnreadMessages()) {
            if (m.getTheme().equals(MessageTheme.ADMISSION.name())) allUnreadAdmissionMessage.add(m);
        }
        markAsRead(allUnreadAdmissionMessage);
        return allUnreadAdmissionMessage;
    }

    @Override
    public List<MessageDto> getAllAppeal() {
        List<MessageDto> allAppealMessage = new ArrayList<>();
        for (MessageDto m : getAllMessages()) {
            if (m.getTheme().equals(MessageTheme.APPEAL.name())) allAppealMessage.add(m);
        }
        markAsRead(allAppealMessage);
        return allAppealMessage;
    }

    @Override
    public List<MessageDto> getAllUnreadAppeal() {
        List<MessageDto> allUnreadAppealMessage = new ArrayList<>();
        for (MessageDto m : getAllUnreadMessages()) {
            if (m.getTheme().equals(MessageTheme.APPEAL.name())) allUnreadAppealMessage.add(m);
        }
        markAsRead(allUnreadAppealMessage);
        return allUnreadAppealMessage;
    }
}
