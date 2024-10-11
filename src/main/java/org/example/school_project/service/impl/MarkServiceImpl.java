package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.LessonDto;
import org.example.school_project.dto.MarkDto;
import org.example.school_project.dto.MarkDtoRequest;
import org.example.school_project.entity.Mark;
import org.example.school_project.repository.MarkRepository;
import org.example.school_project.service.LessonService;
import org.example.school_project.service.MarkService;
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
    public Double getGradeByMarkDto(List<MarkDto> markDtoList) {
        Double sum = 0.0;
        for (MarkDto m : markDtoList) sum += m.getMark();
        return sum/markDtoList.size();
    }

    @Override
    public Double getGradeByDouble(List<Double> markList) {
        Double sum = 0.0;
        for (Double m : markList)
            sum += m;
        return sum/markList.size();
    }

    @Override
    public List<MarkDto> filterMark(List<LessonDto> lessonDtoList) {
        List<MarkDto> markDtoList = new ArrayList<>();
        for (LessonDto l : lessonDtoList) {
            for (MarkDto m : getAllMark())
                if (l.getId().equals(m.getLessonId()))
                    markDtoList.add(m);

        }
        return markDtoList;
    }

    @Override
    public List<MarkDto> filterMark(List<LessonDto> lessonDtoList, Long studentId) {
        List<MarkDto> allMarks = new ArrayList<>();
        for (LessonDto l : lessonDtoList) {
            for (MarkDto m : getAllMark())
                if (l.getId().equals(m.getLessonId()) && m.getStudentId().equals(studentId))
                    allMarks.add(m);

        }
        return allMarks;
    }

    @Override
    public Double getAverage(List<MarkDto> markDtoList) {
        Double sum = 0.0;
        for (MarkDto m : markDtoList)
            sum += m.getMark();
        return sum / markDtoList.size();
    }
}
