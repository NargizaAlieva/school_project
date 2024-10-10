package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.ScheduleDto;
import org.example.school_project.dto.ScheduleDtoRequest;
import org.example.school_project.entity.Schedule;
import org.example.school_project.repository.ScheduleRepository;
import org.example.school_project.service.ScheduleService;
import org.example.school_project.service.StudentService;
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
    private final StudentService studentService;
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

    public List<Schedule> getAllScheduleEntity() {
        return scheduleRepository.findAll();
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
    public List<ScheduleDto> getAllScheduleByTeacher(Long teacherId) {
        List<Schedule> scheduleDtoList = new ArrayList<>();
        for (Schedule s : getAllScheduleEntity())
            if (s.getTeacherSchedule().getId().equals(teacherId))
                scheduleDtoList.add(s);
        return scheduleMapper.entityToDtoList(scheduleDtoList);
    }

    @Override
    public List<ScheduleDto> getAllScheduleByGrade(Long gradeId) {
        List<Schedule> scheduleDtoList = new ArrayList<>();
        for (Schedule s : getAllScheduleEntity())
            if (s.getGradeSchedule().getId().equals(gradeId))
                scheduleDtoList.add(s);
        return scheduleMapper.entityToDtoList(scheduleDtoList);
    }

    @Override
    public List<ScheduleDto> getAllScheduleByYear(String year) {
        List<Schedule> scheduleDtoList = new ArrayList<>();
        for (Schedule s : getAllScheduleEntity())
            if (s.getYear().equals(year))
                scheduleDtoList.add(s);
        return scheduleMapper.entityToDtoList(scheduleDtoList);
    }

    @Override
    public List<ScheduleDto> getAllScheduleByStudent(Long studentId) {
        List<Schedule> scheduleDtoList = new ArrayList<>();
        for (Schedule s : getAllScheduleEntity())
            if (s.getGradeSchedule().getId().equals(studentService.getStudentByIdEntity(studentId).getGrade().getId()))
                scheduleDtoList.add(s);
        return scheduleMapper.entityToDtoList(scheduleDtoList);
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
