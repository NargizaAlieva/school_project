package org.example.school_project.util.mapper;

import org.example.school_project.dto.ScheduleDto;
import org.example.school_project.entity.Schedule;
import org.example.school_project.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleMapper {
    public ScheduleDto entityToDto(Schedule schedule) {
        User teacher = schedule.getTeacherSchedule().getUser();
        String teacherFullName = teacher.getFirstName() + teacher.getLastName();
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setId(schedule.getId());
        scheduleDto.setDayOfWeek(schedule.getDayOfWeek());
        scheduleDto.setDueTime(schedule.getDueTime());
        scheduleDto.setQuarter(schedule.getQuarter());
        scheduleDto.setYear(schedule.getYear());
        scheduleDto.setSubjectSchedule(schedule.getSubjectSchedule().getTitle());
        scheduleDto.setGradeName(schedule.getGradeSchedule().getTitle());
        scheduleDto.setTeacherName(teacherFullName);
        scheduleDto.setIsApprove(schedule.getIsApprove());
        return scheduleDto;
    }

    public List<ScheduleDto> entityToDtoList(List<Schedule> schedules) {
        return schedules.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
