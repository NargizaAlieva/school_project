package org.example.school_project.service;

import org.example.school_project.dto.AnnouncementDto;
import org.example.school_project.dto.AnnouncementDtoRequest;
import org.example.school_project.entity.Announcement;
import org.example.school_project.enums.CanSeeOnly;

import java.util.List;

public interface AnnouncementService {
    Announcement getAnnouncementByIdEntity(Long id);
    AnnouncementDto getAnnouncementById(Long id);
    AnnouncementDto createAnnouncement(AnnouncementDtoRequest announcementDtoRequest);
    AnnouncementDto updateAnnouncement(AnnouncementDtoRequest announcementDtoRequest);

    List<AnnouncementDto> getAllAnnouncement();
    List<AnnouncementDto> getAllAnnouncementForWhom(CanSeeOnly canSeeOnly);
    List<AnnouncementDto> getAllAnnouncementByAuthor(Long id);
}
