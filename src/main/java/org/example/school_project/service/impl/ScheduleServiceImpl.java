package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.ScheduleDto;
import org.example.school_project.dto.ScheduleDtoRequest;
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

    public Schedule getScheduleByIdEntity(Long id) {
        if (id == null) return null;
        return scheduleRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Schedule"));
    }
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public ScheduleDto createSchedule(ScheduleDtoRequest scheduleDtoRequest) {
        return scheduleMapper.entityToDto(scheduleRepository.save(scheduleMapper.dtoToEntity(scheduleDtoRequest)));
    }

    @Override
    public ScheduleDto updateSchedule(ScheduleDtoRequest scheduleDtoRequest) {
        if (getScheduleById(scheduleDtoRequest.getId()) == null) return null;
        Schedule oldSchedule = scheduleMapper.dtoToEntity(scheduleDtoRequest);
        Schedule newSchedule = getScheduleByIdEntity(scheduleDtoRequest.getId());
        newSchedule.setId(oldSchedule.getId());
        newSchedule.setDayOfWeek(oldSchedule.getDayOfWeek());
        newSchedule.setDueTime(oldSchedule.getDueTime());
        newSchedule.setQuarter(oldSchedule.getQuarter());
        newSchedule.setYear(oldSchedule.getYear());
        newSchedule.setSubjectSchedule(oldSchedule.getSubjectSchedule());
        newSchedule.setGradeSchedule(oldSchedule.getGradeSchedule());
        newSchedule.setTeacherSchedule(oldSchedule.getTeacherSchedule());
        newSchedule.setIsApprove(oldSchedule.getIsApprove());
        return scheduleMapper.entityToDto(scheduleRepository.save(newSchedule));
    }

    @Override
    public ScheduleDto deleteSchedule(Long id) {
        Schedule schedule = getScheduleByIdEntity(id);
        //schedule.setIsActive(false);
        return scheduleMapper.entityToDto(save(schedule));
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
