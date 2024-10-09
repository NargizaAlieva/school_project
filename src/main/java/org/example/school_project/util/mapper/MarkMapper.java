package org.example.school_project.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.MarkDto;
import org.example.school_project.dto.MarkDtoRequest;
import org.example.school_project.entity.Mark;
import org.example.school_project.service.LessonService;
import org.example.school_project.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MarkMapper {
    private final LessonService lessonService;
    private final StudentService studentService;
    public MarkDto entityToDto (Mark mark) {
        String teacherName = mark.getLessonMark().getSchedule().getTeacherSchedule().getUser().getFirstName() + " " + mark.getLessonMark().getSchedule().getTeacherSchedule().getUser().getLastName();
        if(mark.getLessonMark().getSchedule().getTeacherSchedule().getUser().getMiddleName() != null) teacherName += " " + mark.getLessonMark().getSchedule().getTeacherSchedule().getUser().getMiddleName();

        String studentName = mark.getStudentMark().getUser().getFirstName() + mark.getStudentMark().getUser().getLastName();
        if(mark.getStudentMark().getUser().getMiddleName() != null) studentName += " " + mark.getStudentMark().getUser().getMiddleName();
        MarkDto markDto = new MarkDto();
        markDto.setId(mark.getId());
        markDto.setMark(mark.getMark());
        markDto.setLessonId(mark.getLessonMark().getId());
        markDto.setLessonTopic(mark.getLessonMark().getTopic());
        markDto.setSubjectId(mark.getLessonMark().getSchedule().getSubjectSchedule().getId());
        markDto.setSubjectTitle(mark.getLessonMark().getSchedule().getSubjectSchedule().getTitle());
        markDto.setTeacherId(mark.getLessonMark().getSchedule().getTeacherSchedule().getId());
        markDto.setTeacherTitle(teacherName);
        markDto.setStudentId(mark.getLessonMark().getSchedule().getId());
        markDto.setStudentId(mark.getStudentMark().getId());
        markDto.setStudentName(studentName);
        return markDto;
    }

    public List<MarkDto> entityToDtoList(List<Mark> marks) {
        return marks.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Mark dtoToEntity(MarkDtoRequest markDtoRequest) {
        Mark mark = new Mark();
        mark.setId(markDtoRequest.getId());
        mark.setMark(markDtoRequest.getMark());
        mark.setStudentMark(studentService.getStudentByIdEntity(markDtoRequest.getStudentId()));
        mark.setLessonMark(lessonService.getLessonByIdEntity(markDtoRequest.getLessonId()));
        return mark;
    }
}
