package org.example.school_project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.AnnouncementDto;
import org.example.school_project.dto.AnnouncementDtoRequest;
import org.example.school_project.entity.Announcement;
import org.example.school_project.entity.Role;
import org.example.school_project.enums.CanSeeOnly;
import org.example.school_project.repository.AnnouncementRepository;
import org.example.school_project.service.AnnouncementService;
import org.example.school_project.util.exception.AlreadyExistException;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.AnnouncementMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;

    @Override
    public Announcement getAnnouncementByIdEntity(Long id) {
        return announcementRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Announcement"));
    }
    private Announcement save (Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    @Override
    public AnnouncementDto getAnnouncementById(Long id) {
        return announcementMapper.entityToDto(getAnnouncementByIdEntity(id));
    }

    @Override
    public AnnouncementDto createAnnouncement(AnnouncementDtoRequest announcementDtoRequest) {
        if (announcementRepository.existsById(announcementDtoRequest.getId()))
            throw new AlreadyExistException("Announcement", "'id'");
        return announcementMapper.entityToDto(save(announcementMapper.dtoToEntity(announcementDtoRequest)));
    }

    @Override
    public AnnouncementDto updateAnnouncement(AnnouncementDtoRequest announcementDtoRequest) {
        Announcement oldAnnouncement = announcementMapper.dtoToEntity(announcementDtoRequest);
        Announcement newAnnouncement = getAnnouncementByIdEntity(announcementDtoRequest.getId());

        newAnnouncement.setId(oldAnnouncement.getId());
        newAnnouncement.setTitle(oldAnnouncement.getTitle());
        newAnnouncement.setDescription(oldAnnouncement.getDescription());
        newAnnouncement.setForWhom(oldAnnouncement.getForWhom());
        newAnnouncement.setCreationDate(oldAnnouncement.getCreationDate());
        newAnnouncement.setAuthorOfAnnouncement(oldAnnouncement.getAuthorOfAnnouncement());
        return announcementMapper.entityToDto(newAnnouncement);
    }

    @Override
    public AnnouncementDto deleteAnnouncement(Long id) {
        Announcement announcement = getAnnouncementByIdEntity(id);
        announcement.setIsActive(false);
        return announcementMapper.entityToDto(save(announcement));
    }

    @Override
    public AnnouncementDto restoreAnnouncement(Long id) {
        Announcement announcement = getAnnouncementByIdEntity(id);
        announcement.setIsActive(true);
        return announcementMapper.entityToDto(save(announcement));
    }

    @Override
    public List<Announcement> getAllAnnouncementEntity() {
        return announcementRepository.findAll();
    }

    @Override
    public List<AnnouncementDto> getAllAnnouncement() {
        return announcementMapper.entityToDtoList(getAllAnnouncementEntity());
    }

    @Override
    public List<AnnouncementDto> getAllActiveAnnouncement() {
        List<Announcement> newList = new ArrayList<>();
        for (Announcement a : getAllAnnouncementEntity())
            if (a.getIsActive())
                newList.add(a);
        return announcementMapper.entityToDtoList(newList);
    }

    @Override
    public List<AnnouncementDto> getAllAnnouncementForWhom(CanSeeOnly canSeeOnly) {
        List<AnnouncementDto> newList = new ArrayList<>();

        for(AnnouncementDto a : getAllActiveAnnouncement())
            if (a.getForWhom().equals(canSeeOnly))
                newList.add(a);
        return newList;
    }

    @Override
    public List<AnnouncementDto> filterByRoleAnnouncement(Set<Role> roleSet) {
        List<AnnouncementDto> newList = new ArrayList<>(getAllAnnouncementForWhom(CanSeeOnly.ALL));
        for (Role r : roleSet)
            for (CanSeeOnly c : CanSeeOnly.values())
                if (r.getTitle().equals(c.name()))
                    newList.addAll(getAllAnnouncementForWhom(c));
        return newList;
    }

    @Override
    public List<AnnouncementDto> getAllAnnouncementByAuthor(Long id) {
        List<AnnouncementDto> newList = new ArrayList<>();

        for(AnnouncementDto a : getAllAnnouncement())
            if (a.getAuthorId().equals(id))
                newList.add(a);
        return newList;
    }
}
