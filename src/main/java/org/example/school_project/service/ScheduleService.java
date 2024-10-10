package org.example.school_project.service;

import org.example.school_project.dto.ScheduleDto;
import org.example.school_project.dto.ScheduleDtoRequest;
import org.example.school_project.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule getScheduleByIdEntity(Long id);
    ScheduleDto getScheduleById(Long id);

    ScheduleDto createSchedule(ScheduleDtoRequest scheduleDtoRequest);
    ScheduleDto updateSchedule(ScheduleDtoRequest scheduleDtoRequest);

    ScheduleDto restoreSchedule(Long id);
    ScheduleDto deleteSchedule(Long id);
    ScheduleDto approveSchedule(Long id);
    ScheduleDto disapproveSchedule(Long id);

    List<ScheduleDto> getAllSchedule();
    List<ScheduleDto> getAllUnApprovedSchedule();

    List<ScheduleDto> getAllActiveSchedule();
    List<ScheduleDto> filterActiveSchedule(List<ScheduleDto> scheduleDtoList);

    List<ScheduleDto> getAllScheduleByTeacher(Long teacherId);
    List<ScheduleDto> getAllScheduleByGrade(Long gradeId);
    List<ScheduleDto> getAllScheduleByYear(String year);

    List<ScheduleDto> getAllScheduleByStudent(Long studentId);
}
