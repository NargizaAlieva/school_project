package org.example.school_project.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.AnnouncementDto;
import org.example.school_project.dto.AnnouncementDtoRequest;
import org.example.school_project.entity.Announcement;
import org.example.school_project.enums.CanSeeOnly;
import org.example.school_project.service.EmployeeService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AnnouncementMapper {
    private final EmployeeService employeeService;
    public AnnouncementDto entityToDto(Announcement announcement) {
        String authorFullName = announcement.getAuthorOfAnnouncement().getUser().getFirstName() + " " + announcement.getAuthorOfAnnouncement().getUser().getLastName();
        if (announcement.getAuthorOfAnnouncement().getUser().getMiddleName() != null) authorFullName = " " + announcement.getAuthorOfAnnouncement().getUser().getMiddleName();

        AnnouncementDto announcementDto = new AnnouncementDto();
        announcementDto.setId(announcement.getId());
        announcementDto.setTitle(announcement.getTitle());
        announcementDto.setDescription(announcement.getDescription());
        announcementDto.setForWhom(announcement.getForWhom());
        announcementDto.setAuthorId(announcement.getAuthorOfAnnouncement().getId());
        announcementDto.setAuthorName(authorFullName);
        announcementDto.setCreationDate(announcement.getCreationDate());
        return announcementDto;
    }

    public List<AnnouncementDto> entityToDtoList(List<Announcement> announcements) {
        return announcements.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Announcement dtoToEntity(AnnouncementDtoRequest announcementDtoRequest) {
        Announcement announcement = new Announcement();
        for (CanSeeOnly c : CanSeeOnly.values()) {
            if (announcementDtoRequest.getForWhom().equals(c.name()))
                announcement.setForWhom(c);
        }

        announcement.setId(announcementDtoRequest.getId());
        announcement.setTitle(announcementDtoRequest.getTitle());
        announcement.setDescription(announcementDtoRequest.getDescription());
        if(announcementDtoRequest.getCreationDate() == null) announcement.setCreationDate(LocalDateTime.now());
        else announcement.setCreationDate(announcementDtoRequest.getCreationDate());
        announcement.setAuthorOfAnnouncement(employeeService.findByIdEntity(announcementDtoRequest.getAuthorId()));
        return announcement;
    }
}
