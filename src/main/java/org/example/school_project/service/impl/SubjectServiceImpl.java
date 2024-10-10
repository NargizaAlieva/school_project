package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.ScheduleDto;
import org.example.school_project.dto.SubjectDto;
import org.example.school_project.dto.SubjectDtoRequest;
import org.example.school_project.entity.Subject;
import org.example.school_project.repository.SubjectRepository;
import org.example.school_project.service.SubjectService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.SubjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
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
            subjectList.add(findByTitle(s.getSubjectSchedule()));
        return subjectMapper.entityToDtoList(subjectList);
    }
    @Override
    public SubjectDto addSubject(SubjectDtoRequest subjectDtoRequest) {
        return subjectMapper.entityToDto(subjectRepository.save(subjectMapper.dtoToEntity(subjectDtoRequest)));
    }

    @Override
    public SubjectDto updateSubject(SubjectDtoRequest subjectDtoRequest) {
        if(findByIdEntity(subjectDtoRequest.getId()) == null){
            throw new ObjectNotFoundException("Subject");
        }
        Subject oldSubject = subjectMapper.dtoToEntity(subjectDtoRequest);
        Subject newSubject = findByIdEntity(subjectDtoRequest.getId());

        newSubject.setId(oldSubject.getId());
        newSubject.setTitle(oldSubject.getTitle());
        newSubject.setDescription(oldSubject.getDescription());
        newSubject.setIsActive(oldSubject.getIsActive());
        newSubject.setTeachersSet(oldSubject.getTeachersSet());
        return subjectMapper.entityToDto(subjectRepository.save(newSubject));
    }

    @Override
    public void deleteSubject(Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Subject"));
        subject.setIsActive(false);
        subjectRepository.save(subject);
    }
}
