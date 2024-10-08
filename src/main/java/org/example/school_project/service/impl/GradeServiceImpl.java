package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.GradeDto;
import org.example.school_project.dto.GradeDtoRequest;
import org.example.school_project.entity.Grade;
import org.example.school_project.repository.GradeRepository;
import org.example.school_project.service.GradeService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.GradeMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;
    @Override
    public Grade getByIdEntity(Long id) {
        return gradeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Grade"));
    }

    @Override
    public GradeDto getById(Long id) {
        return gradeMapper.entityToDto(getByIdEntity(id));
    }

    @Override
    public GradeDto createGrade(GradeDtoRequest gradeDtoRequest) {
        return gradeMapper.entityToDto(save(gradeMapper.dtoToEntity(gradeDtoRequest)));
    }

    @Override
    public GradeDto updateGrade(GradeDtoRequest gradeDtoRequest) {
        Grade oldGrade = gradeMapper.dtoToEntity(gradeDtoRequest);
        Grade newGrade = getByIdEntity(gradeDtoRequest.getId());

        newGrade.setId(oldGrade.getId());
        newGrade.setTitle(oldGrade.getTitle());
        newGrade.setCreationDate(oldGrade.getCreationDate());
        newGrade.setClassTeacher(oldGrade.getClassTeacher());
        return gradeMapper.entityToDto(save(newGrade));
    }

    @Override
    public GradeDto deleteGrade(Long id) {
        Grade grade = getByIdEntity(id);
//        grade.setIsActive(false);
        return gradeMapper.entityToDto(save(grade));
    }

    @Override
    public List<GradeDto> getAllActiveGrade() {
        List<GradeDto> activeGrades = new ArrayList<>();
        for (GradeDto g : getAllGrade()) {
//            if (g.getIsActive) activeGrades.add(g);
        }
        return activeGrades;
    }

    @Override
    public List<GradeDto> getAllGrade() {
        return gradeMapper.entityToDtoList(gradeRepository.findAll());
    }

    public Grade save(Grade grade) {
        return gradeRepository.save(grade);
    }
}
