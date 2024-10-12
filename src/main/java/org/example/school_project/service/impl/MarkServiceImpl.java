package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.entity.Mark;
import org.example.school_project.repository.MarkRepository;
import org.example.school_project.service.*;
import org.example.school_project.util.exception.AlreadyExistException;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.MarkMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarkServiceImpl implements MarkService {
    private final MarkRepository markRepository;
    private final MarkMapper markMapper;
    private final LessonService lessonService;

    public Mark save(Mark mark) {
        return markRepository.save(mark);
    }

    @Override
    public Mark getMarkByIdEntity(Long id) {
        return markRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Mark"));
    }

    @Override
    public MarkDto getMarkById(Long id) {
        return markMapper.entityToDto(getMarkByIdEntity(id));
    }

    @Override
    public MarkDto createMark(MarkDtoRequest markDtoRequest) {
        if (markRepository.existsById(markDtoRequest.getId()))
            throw new AlreadyExistException("Mark", "'id'");
        return markMapper.entityToDto(save(markMapper.dtoToEntity(markDtoRequest)));
    }

    @Override
    public MarkDto updateMark(MarkDtoRequest markDtoRequest) {
        Mark oldMark = markMapper.dtoToEntity(markDtoRequest);
        Mark newMark = getMarkByIdEntity(markDtoRequest.getId());

        newMark.setId(oldMark.getId());
        newMark.setMark(oldMark.getMark());
        newMark.setStudentMark(oldMark.getStudentMark());
        newMark.setLessonMark(oldMark.getLessonMark());
        return markMapper.entityToDto(save(newMark));
    }

    @Override
    public List<MarkDto> getAllMark() {
        return markMapper.entityToDtoList(markRepository.findAll());
    }

    @Override
    public List<MarkDto> getAllMarkByStudent(Long studentId) {
        List<MarkDto> markDtoList = new ArrayList<>();
        for (MarkDto m : getAllMark())
            if (m.getStudentId().equals(studentId))
                markDtoList.add(m);
        return markDtoList;
    }

    @Override
    public List<MarkDto> getStudentMarksBySubjectGrade(Long subjectId, Integer quarter, Long gradeId, Long studentId) {
        List<MarkDto> markDtoList = new ArrayList<>();
        List<LessonDto> lessonDtoList = lessonService.getAllLessonBySubjectQuarter(subjectId, quarter, gradeId);
        for (LessonDto l : lessonDtoList) {
            for (MarkDto m : getAllMark())
                if (l.getId().equals(m.getLessonId()))
                    if (m.getStudentId().equals(studentId))
                        markDtoList.add(m);
        }
        return markDtoList;
    }

    @Override
    public List<MarkDto> convertToMark(List<LessonDto> lessonDtoList) {
        List<MarkDto> markDtoList = new ArrayList<>();
        for (LessonDto l : lessonDtoList) {
            for (MarkDto m : getAllMark())
                if (l.getId().equals(m.getLessonId()))
                    markDtoList.add(m);
        }
        return markDtoList;
    }
}
