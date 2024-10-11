package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.HomeworkDto;
import org.example.school_project.dto.LessonDto;
import org.example.school_project.dto.LessonDtoRequest;
import org.example.school_project.entity.Lesson;
import org.example.school_project.repository.LessonRepository;
import org.example.school_project.service.LessonService;
import org.example.school_project.service.ScheduleService;
import org.example.school_project.util.exception.AlreadyExistException;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.LessonMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;
    private final ScheduleService scheduleService;

    public Lesson save(Lesson lesson) {
        return lessonRepository.save(lesson);
    }
    @Override
    public Lesson getLessonByIdEntity(Long id) {
        return lessonRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Lesson"));
    }

    @Override
    public LessonDto getLessonById(Long id) {
        return lessonMapper.entityToDto(getLessonByIdEntity(id));
    }

    @Override
    public LessonDto createLesson(LessonDtoRequest lessonDtoRequest) {
        if (lessonRepository.existsById(lessonDtoRequest.getId()))
            throw new AlreadyExistException("Lesson", "'id'");
        return lessonMapper.entityToDto(save(lessonMapper.dtoToEntity(lessonDtoRequest)));
    }

    @Override
    public LessonDto updateLesson(LessonDtoRequest lessonDtoRequest) {
        Lesson oldLesson = lessonMapper.dtoToEntity(lessonDtoRequest);
        Lesson newLesson = getLessonByIdEntity(lessonDtoRequest.getId());

        newLesson.setId(oldLesson.getId());
        newLesson.setTopic(oldLesson.getTopic());
        newLesson.setHomework(oldLesson.getHomework());
        newLesson.setCreationDate(oldLesson.getCreationDate());
        newLesson.setSchedule(oldLesson.getSchedule());
        return lessonMapper.entityToDto(save(newLesson));
    }

    @Override
    public List<LessonDto> getAllLesson() {
        return lessonMapper.entityToDtoList(lessonRepository.findAll());
    }

    @Override
    public List<LessonDto> getAllLessonByTeacherId(List<LessonDto> lessonDtoList, Long teacherId) {
        List<LessonDto> teacherLesson = new ArrayList<>();
        for (LessonDto l : lessonDtoList) {
            if (l.getTeacherId().equals(teacherId))
                teacherLesson.add(l);
        }
        return teacherLesson;
    }

    @Override
    public List<LessonDto> getAllLessonByGradeId(List<LessonDto> lessonDtoList, Long gradeId) {
        List<LessonDto> gradeLesson = new ArrayList<>();
        for (LessonDto l : lessonDtoList)
            if (l.getGradeId().equals(gradeId))
                gradeLesson.add(l);
        return gradeLesson;
    }

    @Override
    public List<LessonDto> getAllLessonBySubjectId(List<LessonDto> lessonDtoList, Long subjectId) {
        List<LessonDto> subjectLesson = new ArrayList<>();
        for (LessonDto l : lessonDtoList)
            if (l.getSubjectId().equals(subjectId))
                subjectLesson.add(l);
        return subjectLesson;
    }

    @Override
    public List<LessonDto> getAllLessonByYear(List<LessonDto> lessonDtoList, String year) {
        List<LessonDto> subjectLesson = new ArrayList<>();
        for (LessonDto l : lessonDtoList)
            if (scheduleService.getScheduleById(l.getScheduleId()).getYear().equals(year))
                subjectLesson.add(l);
        return subjectLesson;
    }

    @Override
    public List<LessonDto> getAllLessonByQuarter(List<LessonDto> lessonDtoList, Integer quarter) {
        List<LessonDto> subjectLesson = new ArrayList<>();
        for (LessonDto l : lessonDtoList)
            if (scheduleService.getScheduleById(l.getScheduleId()).getQuarter().equals(quarter))
                subjectLesson.add(l);
        return subjectLesson;
    }

    @Override
    public List<LessonDto> getAllLessonByYearSubjectQuarter(String year, Long subjectId, Integer quarter, Long gradeId) {
        List<LessonDto> allLessonByGrade = getAllLessonByGradeId(getAllLesson(), gradeId);
        List<LessonDto> allLessonByYear = getAllLessonByYear(allLessonByGrade, year);
        List<LessonDto> allLessonBySubject = getAllLessonBySubjectId(allLessonByYear, subjectId);
        return getAllLessonByQuarter(allLessonBySubject, quarter);
    }

    @Override
    public List<LessonDto> getUndoneHwByStudent(List<HomeworkDto> homeworkDtoList, Long studentId) {
        List<LessonDto> allHw = new ArrayList<>();
        for (LessonDto l : getAllLesson()) {
            Boolean isDone = false;
            for (HomeworkDto h : homeworkDtoList) {
                if (l.getId().equals(h.getLessonId()) && h.getStudentId().equals(studentId))
                    isDone = true;
            }
            if (!isDone) allHw.add(l);
        }
        return allHw;
    }

    @Override
    public List<LessonDto> getUndoneHwByStudent(List<HomeworkDto> homeworkDtoList, Long studentId, Long subjectId) {
        List<LessonDto> allHw = new ArrayList<>();
        for (LessonDto l : getAllLessonBySubjectId(getAllLesson(), studentId)) {
            Boolean isDone = false;
            for (HomeworkDto h : homeworkDtoList) {
                if (l.getId().equals(h.getLessonId()) && h.getStudentId().equals(studentId))
                    isDone = true;
            }
            if (!isDone) allHw.add(l);
        }
        return allHw;
    }
}
