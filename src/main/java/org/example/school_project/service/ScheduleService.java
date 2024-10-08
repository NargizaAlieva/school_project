package org.example.school_project.service;

import org.example.school_project.dto.ScheduleDto;
import org.example.school_project.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    ScheduleDto getScheduleById(Long id);
    ScheduleDto createSchedule(Schedule schedule);
    ScheduleDto updateSchedule(Schedule schedule);
    ScheduleDto approveSchedule(Long id);
    List<ScheduleDto> getAllSchedule();
    List<ScheduleDto> getAllUnApprovedSchedule();
}
