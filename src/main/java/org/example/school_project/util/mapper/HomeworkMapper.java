package org.example.school_project.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.HomeworkDto;
import org.example.school_project.dto.HomeworkDtoRequest;
import org.example.school_project.dto.LessonDto;
import org.example.school_project.entity.Homework;
import org.example.school_project.entity.Lesson;
import org.example.school_project.service.LessonService;
import org.example.school_project.service.StudentService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HomeworkMapper {
    private final StudentService studentService;
    private final LessonService lessonService;
    public HomeworkDto entityToDto(Homework homework) {
        String studentName = homework.getStudentHW().getUser().getFirstName() + homework.getStudentHW().getUser().getLastName();
        if (homework.getStudentHW().getUser().getMiddleName() != null)
            studentName += " " + homework.getStudentHW().getUser().getMiddleName();

        HomeworkDto homeworkDto = new HomeworkDto();
        homeworkDto.setId(homework.getId());
        homeworkDto.setMark(homework.getMark());
        homeworkDto.setCreationDate(homework.getCreationDate());
        homeworkDto.setIsChecked(homework.getIsChecked());
        homeworkDto.setTeacherReview(homework.getTeacherReview());
        homeworkDto.setLessonId(homework.getLessonHW().getId());
        homeworkDto.setLessonTopic(homework.getLessonHW().getTopic());
        homeworkDto.setStudentId(homework.getStudentHW().getId());
        homeworkDto.setStudentName(studentName);
        return homeworkDto;
    }

    public List<HomeworkDto> entityToDtoList(List<Homework> homeworks) {
        return homeworks.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Homework dtoToEntity(HomeworkDtoRequest homeworkDtoRequest) {
        Homework homework = new Homework();
        homework.setId(homeworkDtoRequest.getId());
        homework.setCreationDate(LocalDateTime.now());
        homework.setIsChecked(false);
        homework.setLessonHW(lessonService.getLessonByIdEntity(homeworkDtoRequest.getLessonId()));
        homework.setStudentHW(studentService.getStudentByIdEntity(homeworkDtoRequest.getStudentId()));
        return homework;
    }

}
