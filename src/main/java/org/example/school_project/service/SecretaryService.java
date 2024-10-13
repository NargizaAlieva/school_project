package org.example.school_project.service;

import org.example.school_project.dto.*;

import java.util.List;

public interface SecretaryService {
    AssignmentDto markAsDone(Long id);
    List<AssignmentDto> getAllAssignment();
    List<AssignmentDto> getAllUndoneAssignment();

    MessageDto getMessageById(Long id);
    List<MessageDto> getAllMessages();
    List<MessageDto> getAllReceiverMessages();
    List<MessageDto> getAllAdmissionApplication();
    List<MessageDto> getAllUnreadAdmissionApplication();
    List<MessageDto> getAllAppeal();
    List<MessageDto> getAllUnreadAppeal();

    CharterDto createCharter(CharterDtoRequest charterDtoR);

    CharterDto updateCharter(CharterDtoRequest charterDtoR);

    CharterDto deleteCharter(Long id);

    CharterDto restoreCharter(Long id);

    List<CharterDto> getAllCharterByAuthor();

    AnnouncementDto createAnnouncement(AnnouncementDtoRequest announcementDtoRequest);

    AnnouncementDto updateAnnouncement(AnnouncementDtoRequest announcementDtoRequest);

    AnnouncementDto deleteAnnouncement(Long id);

    AnnouncementDto restoreAnnouncement(Long id);

    List<AnnouncementDto> getAllActiveAnnouncement();

    List<AnnouncementDto> getAllSelfAnnouncement();
}
