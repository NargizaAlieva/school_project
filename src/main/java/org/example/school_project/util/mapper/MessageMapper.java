package org.example.school_project.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.MessageDto;
import org.example.school_project.dto.MessageDtoRequest;
import org.example.school_project.entity.Message;
import org.example.school_project.enums.MessageTheme;
import org.example.school_project.service.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MessageMapper {
    private final UserService userService;
    public MessageDto entityToDto(Message message) {
        MessageDto messageDto = new MessageDto();

        String authorFullName = message.getAuthorMessage().getFirstName() + " " + message.getAuthorMessage().getLastName();
        if(message.getAuthorMessage().getMiddleName() != null) authorFullName += " " + message.getAuthorMessage().getMiddleName();

        messageDto.setId(message.getId());
        messageDto.setTheme(message.getTheme().name());
        messageDto.setTitle(message.getTitle());
        messageDto.setCreationDate(message.getCreationDate());
        messageDto.setMessage(message.getMessage());
        messageDto.setIsRead(message.getIsRead());
        messageDto.setReceiverId(message.getReceiverMessage().getId());
        messageDto.setAuthorId(message.getAuthorMessage().getId());
        messageDto.setAuthorName(authorFullName);
        messageDto.setIsActive(message.getIsActive());
        return messageDto;
    }

    public List<MessageDto> entityToDtoList(List<Message> messages) {
        return messages.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Message dtoToEntity(MessageDtoRequest messageDtoRequest) {
        Message message = new Message();
        for (MessageTheme m : MessageTheme.values()) {
            if (messageDtoRequest.getTheme().equals(m.name()))
                message.setTheme(m);
        }
        message.setId(messageDtoRequest.getId());
        message.setTitle(messageDtoRequest.getTitle());
        message.setMessage(messageDtoRequest.getMessage());
        if(messageDtoRequest.getCreationDate() == null) message.setCreationDate(LocalDateTime.now());
        else message.setCreationDate(messageDtoRequest.getCreationDate());
        message.setReceiverMessage(userService.getEntityById(messageDtoRequest.getReceiverId()));
        message.setAuthorMessage(userService.getCurrentUser());
        return message;
    }
}
