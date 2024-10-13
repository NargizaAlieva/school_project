package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.ScheduleDto;
import org.example.school_project.dto.SubjectDto;
import org.example.school_project.dto.SubjectDtoRequest;
import org.example.school_project.entity.Subject;
import org.example.school_project.repository.SubjectRepository;
import org.example.school_project.service.SubjectService;
import org.example.school_project.util.exception.AlreadyExistException;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.SubjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }
    @Override
    public Subject findByIdEntity(Long id) {
        return subjectRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Subject"));
    }

    public Subject findByTitle(String title) {
        return subjectRepository.findByTitle(title).orElseThrow(() -> new ObjectNotFoundException("Subject"));
    }

    @Override
    public List<SubjectDto> getSubjectSchedule(List<ScheduleDto> scheduleDtoList) {
        List<Subject> subjectList = new ArrayList<>();
        for (ScheduleDto s : scheduleDtoList)
            subjectList.add(findByTitle(s.getSubjectTitle()));
        return subjectMapper.entityToDtoList(subjectList);
    }
    @Override
    public SubjectDto addSubject(SubjectDtoRequest subjectDtoRequest) {
        if(subjectRepository.existsById(subjectDtoRequest.getId()))
            throw new AlreadyExistException("Subject", "id");
        if(subjectRepository.existsByTitle(subjectDtoRequest.getTitle()))
            throw new AlreadyExistException("Subject", "title");
        return subjectMapper.entityToDto(save(subjectMapper.dtoToEntity(subjectDtoRequest)));
    }

    @Override
    public SubjectDto updateSubject(SubjectDtoRequest subjectDtoRequest) {
        Subject oldSubject = subjectMapper.dtoToEntity(subjectDtoRequest);
        Subject newSubject = findByIdEntity(subjectDtoRequest.getId());

        newSubject.setId(oldSubject.getId());
        newSubject.setTitle(oldSubject.getTitle());
        newSubject.setDescription(oldSubject.getDescription());
        newSubject.setIsActive(oldSubject.getIsActive());
        newSubject.setTeachersSet(oldSubject.getTeachersSet());
        return subjectMapper.entityToDto(save(newSubject));
    }

    @Override
    public SubjectDto deleteSubject(Long id) {
        Subject subject = findByIdEntity(id);
        subject.setIsActive(false);
        return subjectMapper.entityToDto(save(subject));
    }

    @Override
    public SubjectDto restoreSubject(Long id) {
        Subject subject = findByIdEntity(id);
        subject.setIsActive(true);
        return subjectMapper.entityToDto(save(subject));
    }

    @Override
    public List<SubjectDto> getAllSubject() {
        return subjectMapper.entityToDtoList(subjectRepository.findAll());
    }

    @Override
    public List<SubjectDto> getAllActiveSubject() {
        List<SubjectDto> activeSubject = new ArrayList<>();
        for (SubjectDto s : getAllSubject())
            if (s.getIsActive())
                activeSubject.add(s);
        return activeSubject;
    }

    @Override
    public Set<SubjectDto> getSubjectForGrade(Long gradeId, List<ScheduleDto> scheduleDtoList) {
        Set<SubjectDto> activeSubject = new HashSet<>();
        for (ScheduleDto sch : scheduleDtoList)
            if (sch.getGradeId().equals(gradeId))
                activeSubject.add(
                        subjectMapper.entityToDto(
                                findByIdEntity(sch.getSubjectId())));
        return activeSubject;
    }
}
