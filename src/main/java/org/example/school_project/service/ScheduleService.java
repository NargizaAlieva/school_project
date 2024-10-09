package org.example.school_project.service;

import org.example.school_project.dto.ScheduleDto;
import org.example.school_project.dto.ScheduleDtoRequest;
import org.example.school_project.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    ScheduleDto getScheduleById(Long id);
    Schedule getScheduleByIdEntity(Long id);
    ScheduleDto createSchedule(ScheduleDtoRequest scheduleDtoRequest);
    ScheduleDto updateSchedule(ScheduleDtoRequest scheduleDtoRequest);
    ScheduleDto deleteSchedule(Long id);
    ScheduleDto approveSchedule(Long id);
    List<ScheduleDto> getAllSchedule();
    List<ScheduleDto> getAllUnApprovedSchedule();
}
