package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.AnnouncementDto;
import org.example.school_project.dto.CharterDto;
import org.example.school_project.dto.MessageDto;
import org.example.school_project.dto.MessageDtoRequest;
import org.example.school_project.entity.Role;
import org.example.school_project.entity.User;
import org.example.school_project.service.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserService userService;
    private final MessageService messageService;
    private final AnnouncementService announcementService;
    private final CharterService charterService;

    private User getCurrentUser() {
        return userService.getCurrentUser();
    }

    @Override
    public MessageDto createMessage(MessageDtoRequest messageDtoRequest) {
        return messageService.createMessage(messageDtoRequest);
    }

    @Override
    public MessageDto getMessageById(Long id) {
        return messageService.getMessageById(id, getCurrentUser().getId());
    }

    @Override
    public MessageDto markAsUnread(Long id) {
        return messageService.markAsUnread(id, getCurrentUser().getId());
    }

    @Override
    public MessageDto deleteMessage(Long id) {
        return messageService.deleteMessage(id, getCurrentUser().getId());
    }

    @Override
    public MessageDto restoreMessage(Long id) {
        return messageService.restoreMessage(id, getCurrentUser().getId());
    }

    @Override
    public List<MessageDto> getAllMessages() {
        return messageService.getAllMessages(getCurrentUser().getId());
    }

    @Override
    public List<MessageDto> getAllReceiverMessages() {
        return messageService.getAllReceiverMessages(getAllMessages(), getCurrentUser().getId());
    }

    @Override
    public List<MessageDto> getAllAuthorMessages() {
        return messageService.getAllAuthorMessages(getAllMessages(), getCurrentUser().getId());
    }

    @Override
    public List<MessageDto> getAllUnreadReceiverMessages() {
        return messageService.getAllUnreadMessages(getAllReceiverMessages());
    }

    @Override
    public List<MessageDto> getAllUnreadAuthorMessages() {
        return messageService.getAllUnreadMessages(getAllAuthorMessages());
    }

    @Override
    public List<AnnouncementDto> getFilteredAnnouncement() {
        Set<Role> roleSet = userService.getCurrentUser().getRoleSet();
        return announcementService.filterByRoleAnnouncement(roleSet);
    }

    @Override
    public List<CharterDto> getAllActiveCharter() {
        return charterService.getAllActiveCharter();
    }
}
