package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.entity.Grade;
import org.example.school_project.repository.GradeRepository;
import org.example.school_project.service.GradeService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    @Override
    public Grade getByIdEntity(Long id) {
        return gradeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Grade"));
    }
}
