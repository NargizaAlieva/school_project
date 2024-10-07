package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.SubjectDto;
import org.example.school_project.dto.SubjectDtoRequest;
import org.example.school_project.entity.Subject;
import org.example.school_project.repository.SubjectRepository;
import org.example.school_project.service.SubjectService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.SubjectMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public Subject findByIdEntity(Long id) {
        return subjectRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Subject"));
    }
    @Override
    public SubjectDto addSubject(SubjectDtoRequest subjectDtoRequest) {
        return subjectMapper.entityToDto(subjectRepository.save(subjectMapper.dtoToEntity(subjectDtoRequest)));
    }

    @Override
    public SubjectDto updateSubject(SubjectDtoRequest subjectDtoRequest) {
        if(subjectRepository.findById(subjectDtoRequest.getId()).isEmpty()){
            throw new ObjectNotFoundException("Subject");
        }
        Subject oldSubject = subjectMapper.dtoToEntity(subjectDtoRequest);
        Subject newSubject = new Subject();
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
