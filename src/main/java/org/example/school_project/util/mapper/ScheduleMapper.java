package org.example.school_project.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.ScheduleDto;
import org.example.school_project.dto.ScheduleDtoRequest;
import org.example.school_project.entity.Schedule;
import org.example.school_project.entity.User;
import org.example.school_project.enums.DaysOfWeek;
import org.example.school_project.enums.MessageTheme;
import org.example.school_project.service.EmployeeService;
import org.example.school_project.service.GradeService;
import org.example.school_project.service.SubjectService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {
    private final SubjectService subjectService;
    private final EmployeeService employeeService;
    private final GradeService gradeService;

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

    public Schedule dtoToEntity(ScheduleDtoRequest scheduleDtoRequest) {
        Schedule schedule = new Schedule();
        for (DaysOfWeek d : DaysOfWeek.values()) {
            if (scheduleDtoRequest.getDayOfWeek().toUpperCase().equals(d.name()))
                schedule.setDayOfWeek(d);
        }
        schedule.setId(scheduleDtoRequest.getId());
        schedule.setQuarter(scheduleDtoRequest.getQuarter());
        schedule.setYear(scheduleDtoRequest.getYear());
        schedule.setIsApprove(scheduleDtoRequest.getIsApprove());
        schedule.setSubjectSchedule(subjectService.findByIdEntity(scheduleDtoRequest.getSubjectId()));
        schedule.setGradeSchedule(gradeService.getByIdEntity(scheduleDtoRequest.getGradeId()));
        schedule.setDueTime(scheduleDtoRequest.getDueTime());
        schedule.setTeacherSchedule(employeeService.findByIdEntity(scheduleDtoRequest.getTeacherId()));
        return schedule;
    }
}
