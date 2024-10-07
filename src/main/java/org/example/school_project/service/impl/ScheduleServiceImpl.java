package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.ScheduleDto;
import org.example.school_project.entity.Schedule;
import org.example.school_project.repository.ScheduleRepository;
import org.example.school_project.service.ScheduleService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.ScheduleMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    @Override
    public ScheduleDto getScheduleById(Long id) {
        if (id == null) return null;
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Schedule"));
        return scheduleMapper.entityToDto(schedule);
    }

    @Override
    public ScheduleDto createSchedule(Schedule schedule) {
        return scheduleMapper.entityToDto(scheduleRepository.save(schedule));
    }

    @Override
    public ScheduleDto updateSchedule(Schedule schedule) {
        if (getScheduleById(schedule.getId()) == null) return null;
        Schedule newSchedule = new Schedule();
        newSchedule.toBuilder()
                .id(schedule.getId())
                .dayOfWeek(schedule.getDayOfWeek())
                .dueTime(schedule.getDueTime())
                .quarter(schedule.getQuarter())
                .year(schedule.getYear())
                .subjectSchedule(schedule.getSubjectSchedule())
                .gradeSchedule(schedule.getGradeSchedule())
                .teacherSchedule(schedule.getTeacherSchedule())
                .isApprove(schedule.getIsApprove()).build();
        return scheduleMapper.entityToDto(scheduleRepository.save(newSchedule));
    }

    @Override
    public List<ScheduleDto> getAllSchedule() {
        return scheduleMapper.entityToDtoList(scheduleRepository.findAll());
    }

    @Override
    public ScheduleDto approveSchedule(Long id) {
        if (id == null) return null;
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Schedule"));
        schedule.setIsApprove(true);
        return scheduleMapper.entityToDto(scheduleRepository.save(schedule));
    }

    @Override
    public List<ScheduleDto> getAllUnApprovedSchedule() {
        List<Schedule> allSchedule = scheduleRepository.findAll();
        List<Schedule> unapprovedSchedule = new ArrayList<>();
        for (Schedule sch : allSchedule) {
            if(!sch.getIsApprove()) unapprovedSchedule.add(sch);
        }
        return scheduleMapper.entityToDtoList(unapprovedSchedule);
    }
}
