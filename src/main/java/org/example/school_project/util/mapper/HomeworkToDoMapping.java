package org.example.school_project.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.HomeworkToDoDto;
import org.example.school_project.dto.LessonDto;
import org.example.school_project.entity.Lesson;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HomeworkToDoMapping {
    public HomeworkToDoDto entityToDto(LessonDto lesson) {
        HomeworkToDoDto homework = new HomeworkToDoDto();

        homework.setLessonId(lesson.getId());
        homework.setLessonTopic(lesson.getTopic());
        homework.setHomework(lesson.getHomework());
        homework.setCreationDate(lesson.getCreationDate());
        homework.setSubjectTitle(lesson.getSubjectTitle());

        return homework;
    }

    public List<HomeworkToDoDto> entityToDtoList(List<LessonDto> lessons) {
        return lessons.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
