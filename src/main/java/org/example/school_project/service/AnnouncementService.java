package org.example.school_project.service;

import org.example.school_project.dto.AnnouncementDto;
import org.example.school_project.dto.AnnouncementDtoRequest;
import org.example.school_project.entity.Announcement;
import org.example.school_project.entity.Role;
import org.example.school_project.enums.CanSeeOnly;

import java.util.List;
import java.util.Set;

public interface AnnouncementService {
    Announcement getAnnouncementByIdEntity(Long id);

    AnnouncementDto getAnnouncementById(Long id);

    AnnouncementDto createAnnouncement(AnnouncementDtoRequest announcementDtoRequest);
    AnnouncementDto updateAnnouncement(AnnouncementDtoRequest announcementDtoRequest);

    AnnouncementDto deleteAnnouncement(Long id);

    AnnouncementDto restoreAnnouncement(Long id);

    List<Announcement> getAllAnnouncementEntity();

    List<AnnouncementDto> getAllAnnouncement();

    List<AnnouncementDto> getAllActiveAnnouncement();

    List<AnnouncementDto> getAllAnnouncementForWhom(CanSeeOnly canSeeOnly);

    List<AnnouncementDto> filterByRoleAnnouncement(Set<Role> roleSet);

    List<AnnouncementDto> getAllAnnouncementByAuthor(Long id);
}
