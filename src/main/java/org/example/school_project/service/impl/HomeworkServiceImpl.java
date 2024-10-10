package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.HomeworkDto;
import org.example.school_project.dto.HomeworkDtoRequest;
import org.example.school_project.dto.LessonDto;
import org.example.school_project.entity.Homework;
import org.example.school_project.repository.HomeworkRepository;
import org.example.school_project.service.HomeworkService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.HomeworkMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeworkServiceImpl implements HomeworkService {
    private final HomeworkRepository homeworkRepository;
    private final HomeworkMapper homeworkMapper;

    public Homework save(Homework homework) {
        return homeworkRepository.save(homework);
    }

    public List<HomeworkDto> getAllHw() {
        return homeworkMapper.entityToDtoList(homeworkRepository.findAll());
    }
    @Override
    public Homework getHwByIdEntity(Long id) {
        return homeworkRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Homework"));
    }

    @Override
    public HomeworkDto getHwById(Long id) {
        return homeworkMapper.entityToDto(getHwByIdEntity(id));
    }

    @Override
    public HomeworkDto createHw(HomeworkDtoRequest homeworkDtoRequest) {
        return homeworkMapper.entityToDto(save(homeworkMapper.dtoToEntity(homeworkDtoRequest)));
    }

    @Override
    public HomeworkDto updateHw(HomeworkDtoRequest homeworkDtoRequest) {
        Homework oldHomework = homeworkMapper.dtoToEntity(homeworkDtoRequest);
        Homework newHomework = getHwByIdEntity(homeworkDtoRequest.getId());

        newHomework.setId(oldHomework.getId());
        newHomework.setMark(oldHomework.getMark());
        newHomework.setCreationDate(oldHomework.getCreationDate());
        newHomework.setTeacherReview(oldHomework.getTeacherReview());
        newHomework.setIsChecked(oldHomework.getIsChecked());
        newHomework.setStudentHW(oldHomework.getStudentHW());
        newHomework.setLessonHW(oldHomework.getLessonHW());
        return homeworkMapper.entityToDto(newHomework);
    }

    @Override
    public List<HomeworkDto> filtHw(List<LessonDto> lessonDtoList) {
        List<HomeworkDto> allHw = new ArrayList<>();
        for (LessonDto l : lessonDtoList) {
            for (HomeworkDto h : getAllHw())
                if (l.getId().equals(h.getLessonId()))
                    allHw.add(h);

        }
        return allHw;
    }

    @Override
    public List<HomeworkDto> getHwByStudent(Long studentId) {
        List<HomeworkDto> allHw = new ArrayList<>();
        for (HomeworkDto h : getAllHw()) {
            if (h.getStudentId().equals(studentId))
                allHw.add(h);
        }
        return allHw;
    }

    @Override
    public List<HomeworkDto> getHwByStudentSubject(Long studentId, Long subjectId) {
        List<HomeworkDto> allHw = new ArrayList<>();
        for (HomeworkDto h : getAllHw()) {
            if (h.getStudentId().equals(studentId) && h.getStudentId().equals(subjectId))
                allHw.add(h);
        }
        return allHw;
    }

    @Override
    public List<Integer> getAllHwMark(List<LessonDto> lessonDtoList) {
        List<Integer> allHwMarks = new ArrayList<>();
        for (LessonDto l : lessonDtoList) {
            for (HomeworkDto h : getAllHw())
                if (l.getId().equals(h.getLessonId()))
                    allHwMarks.add(h.getMark());

        }
        return allHwMarks;
    }

    @Override
    public Double getAverageHwMark(List<Integer> mark) {
        Double sum = 0.0;
        for (Integer i : mark)
            sum += i;
        return sum/mark.size();
    }

    @Override
    public List<HomeworkDto> getHwByLesson(Long id) {
        List<HomeworkDto> homeworkDtoList = new ArrayList<>();
        for (HomeworkDto h : getAllHw())
            if (h.getLessonId().equals(id)) homeworkDtoList.add(h);
        return homeworkDtoList;
    }

    @Override
    public HomeworkDto leaveHwMarkReview(Long hwId, Integer mark, String hwReview) {
        Homework homework = getHwByIdEntity(hwId);
        homework.setMark(mark);
        homework.setTeacherReview(hwReview);
        homework.setIsChecked(true);
        return homeworkMapper.entityToDto(save(homework));
    }
}
