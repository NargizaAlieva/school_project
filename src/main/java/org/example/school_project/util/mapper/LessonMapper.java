package org.example.school_project.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.LessonDto;
import org.example.school_project.dto.LessonDtoRequest;
import org.example.school_project.entity.Lesson;
import org.example.school_project.service.AttendanceService;
import org.example.school_project.service.ScheduleService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LessonMapper {
    private final ScheduleService scheduleService;
    public LessonDto entityToDto(Lesson lesson) {
        LessonDto lessonDto = new LessonDto();

        lessonDto.setId(lesson.getId());
        lessonDto.setTopic(lesson.getTopic());
        lessonDto.setHomework(lesson.getHomework());
        lessonDto.setCreationDate(lesson.getCreationDate());
        lessonDto.setScheduleId(lesson.getSchedule().getId());
        lessonDto.setSubjectId(lesson.getSchedule().getSubjectSchedule().getId());
        lessonDto.setSubjectTitle(lesson.getSchedule().getSubjectSchedule().getTitle());
        lessonDto.setTeacherId(lesson.getSchedule().getTeacherSchedule().getId());

        String teacherFullName = lesson.getSchedule().getTeacherSchedule().getUser().getFirstName() + " " + lesson.getSchedule().getTeacherSchedule().getUser().getLastName();
        if(lesson.getSchedule().getTeacherSchedule().getUser().getMiddleName() != null) teacherFullName += " " + lesson.getSchedule().getTeacherSchedule().getUser().getMiddleName();

        lessonDto.setTeacherName(teacherFullName);
        lessonDto.setGradeId(lesson.getSchedule().getGradeSchedule().getId());
        lessonDto.setGradeTitle(lesson.getSchedule().getGradeSchedule().getTitle());

        return lessonDto;
    }

    public List<LessonDto> entityToDtoList(List<Lesson> lessons) {
        return lessons.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Lesson dtoToEntity(LessonDtoRequest lessonDtoRequest) {
        Lesson lesson = new Lesson();

        lesson.setId(lessonDtoRequest.getId());
        lesson.setTopic(lessonDtoRequest.getTopic());
        lesson.setHomework(lessonDtoRequest.getHomework());
        if(lessonDtoRequest.getCreationDate() == null) lesson.setCreationDate(LocalDateTime.now());
        else lesson.setCreationDate(lessonDtoRequest.getCreationDate());
        lesson.setSchedule(scheduleService.getScheduleByIdEntity(lessonDtoRequest.getScheduleId()));
        return lesson;
    }
}
