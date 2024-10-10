package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.HomeworkDto;
import org.example.school_project.dto.LessonDto;
import org.example.school_project.dto.LessonDtoRequest;
import org.example.school_project.entity.Lesson;
import org.example.school_project.repository.LessonRepository;
import org.example.school_project.service.LessonService;
import org.example.school_project.service.ScheduleService;
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
    public List<LessonDto> getAllLessonByTeacherId(Long id) {
        List<LessonDto> teacherLesson = new ArrayList<>();
        for (LessonDto l : getAllLesson()) {
            if (l.getTeacherId().equals(id))
                teacherLesson.add(l);
        }
        return teacherLesson;
    }

    @Override
    public List<LessonDto> getAllLessonByGradeId(Long id) {
        List<LessonDto> gradeLesson = new ArrayList<>();
        for (LessonDto l : getAllLesson())
            if (l.getGradeId().equals(id))
                gradeLesson.add(l);
        return gradeLesson;
    }

    @Override
    public List<LessonDto> getAllLessonBySubjectId(Long id) {
        List<LessonDto> subjectLesson = new ArrayList<>();
        for (LessonDto l : getAllLesson())
            if (l.getSubjectId().equals(id))
                subjectLesson.add(l);
        return subjectLesson;
    }

    @Override
    public List<LessonDto> getAllLessonByYear(String year) {
        List<LessonDto> subjectLesson = new ArrayList<>();
        for (LessonDto l : getAllLesson())
            if (scheduleService.getScheduleById(l.getScheduleId()).getYear().equals(year))
                subjectLesson.add(l);
        return subjectLesson;
    }

    @Override
    public List<LessonDto> getAllTeacherGrade(Long teacherId, Long gradeId) {
        List<LessonDto> teacherLesson = new ArrayList<>();
        for (LessonDto l : getAllLessonByTeacherId(teacherId))
            if (l.getGradeId().equals(gradeId))
                teacherLesson.add(l);
        return teacherLesson;
    }

    @Override
    public List<LessonDto> getAllSubjectGrade(Long subjectId, Long gradeId) {
        List<LessonDto> gradeLesson = new ArrayList<>();
        for (LessonDto l : getAllLessonBySubjectId(subjectId))
            if (l.getGradeId().equals(gradeId))
                gradeLesson.add(l);
        return gradeLesson;
    }

    @Override
    public List<LessonDto> getAllLessonByYearTeacherId(String year, Long teacherId) {
        List<LessonDto> subjectLesson = new ArrayList<>();
        for (LessonDto l : getAllLessonByYear(year))
            if (l.getTeacherId().equals(teacherId))
                subjectLesson.add(l);
        return subjectLesson;
    }

    @Override
    public List<LessonDto> getAllLessonByYearGradeId(String year, Long gradeId) {
        List<LessonDto> subjectLesson = new ArrayList<>();
        for (LessonDto l : getAllLessonByYear(year))
            if (l.getGradeId().equals(gradeId))
                subjectLesson.add(l);
        return subjectLesson;
    }

    @Override
    public List<LessonDto> getAllLessonByYearSubjectId(String year, Long subjectId) {
        List<LessonDto> subjectLesson = new ArrayList<>();
        for (LessonDto l : getAllLessonByYear(year))
            if (l.getSubjectId().equals(subjectId))
                subjectLesson.add(l);
        return subjectLesson;
    }

    @Override
    public List<LessonDto> getAllLessonByYearTeacherIdSubjectId(String year, Long teacherId, Long subjectId) {
        List<LessonDto> subjectLesson = new ArrayList<>();
        for (LessonDto l : getAllLessonByYear(year))
            if (l.getTeacherId().equals(teacherId))
                if (l.getSubjectId().equals(subjectId))
                    subjectLesson.add(l);
        return subjectLesson;
    }

    @Override
    public List<LessonDto> getAllLessonByYearTeacherIdGradeId(String year, Long teacherId, Long gradeId) {
        List<LessonDto> subjectLesson = new ArrayList<>();
        for (LessonDto l : getAllLessonByYear(year))
            if (l.getTeacherId().equals(teacherId))
                if (l.getGradeId().equals(gradeId))
                    subjectLesson.add(l);
        return subjectLesson;
    }

    @Override
    public List<LessonDto> getAllLessonByYearSubjectIdGradeId(String year, Long subjectId, Long gradeId) {
        List<LessonDto> subjectLesson = new ArrayList<>();
        for (LessonDto l : getAllLessonByYear(year))
            if (l.getSubjectId().equals(subjectId))
                if (l.getGradeId().equals(gradeId))
                    subjectLesson.add(l);
        return subjectLesson;
    }

    @Override
    public List<LessonDto> getAllLessonByYearTeacherId(String year, Long teacherId, Integer quarter) {
        List<LessonDto> subjectLesson = new ArrayList<>();
        for (LessonDto l : getAllLessonByYearTeacherId(year, teacherId))
            if (scheduleService.getScheduleById(l.getId()).getQuarter().equals(quarter))
                subjectLesson.add(l);
        return subjectLesson;
    }

    @Override
    public List<LessonDto> getAllLessonByYearGradeId(String year, Long gradeId, Integer quarter) {
        List<LessonDto> subjectLesson = new ArrayList<>();
        for (LessonDto l : getAllLessonByYearGradeId(year, gradeId))
            if (scheduleService.getScheduleById(l.getId()).getQuarter().equals(quarter))
                subjectLesson.add(l);
        return subjectLesson;
    }

    @Override
    public List<LessonDto> getAllLessonByYearSubjectId(String year, Long subjectId, Integer quarter) {
        List<LessonDto> subjectLesson = new ArrayList<>();
        for (LessonDto l : getAllLessonByYearSubjectId(year, subjectId))
            if (scheduleService.getScheduleById(l.getId()).getQuarter().equals(quarter))
                subjectLesson.add(l);
        return subjectLesson;
    }

    @Override
    public List<LessonDto> getAllLessonByYearTeacherIdSubjectId(String year, Long teacherId, Long subjectId, Integer quarter) {
        List<LessonDto> subjectLesson = new ArrayList<>();
        for (LessonDto l : getAllLessonByYearTeacherId(year, teacherId, quarter))
            if (l.getSubjectId().equals(subjectId))
                subjectLesson.add(l);
        return subjectLesson;
    }

    @Override
    public List<LessonDto> getAllLessonByYearTeacherIdGradeId(String year, Long teacherId, Long gradeId, Integer quarter) {
        List<LessonDto> subjectLesson = new ArrayList<>();
        for (LessonDto l : getAllLessonByYearGradeId(year, gradeId, quarter))
            if (l.getTeacherId().equals(teacherId))
                subjectLesson.add(l);
        return subjectLesson;
    }

    @Override
    public List<LessonDto> getAllLessonByYearSubjectIdGradeId(String year, Long subjectId, Long gradeId, Integer quarter) {
        List<LessonDto> subjectLesson = new ArrayList<>();
        for (LessonDto l : getAllLessonByYearSubjectId(year, subjectId, quarter))
            if (l.getGradeId().equals(gradeId))
                subjectLesson.add(l);
        return subjectLesson;
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
        for (LessonDto l : getAllLessonBySubjectId(studentId)) {
            Boolean isDone = false;
            for (HomeworkDto h : homeworkDtoList) {
                if (l.getId().equals(h.getLessonId()) && h.getStudentId().equals(studentId))
                    isDone = true;
            }
            if (!isDone) allHw.add(l);
        }
        return allHw;
    }

    public Lesson save(Lesson lesson) {
        return lessonRepository.save(lesson);
    }
}
