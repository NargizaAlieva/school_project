package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.ScheduleDto;
import org.example.school_project.dto.ScheduleDtoRequest;
import org.example.school_project.entity.Schedule;
import org.example.school_project.repository.ScheduleRepository;
import org.example.school_project.service.EmployeeService;
import org.example.school_project.service.ScheduleService;
import org.example.school_project.service.StudentService;
import org.example.school_project.util.exception.AlreadyExistException;
import org.example.school_project.util.exception.IncorrectRequestException;
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
    private final EmployeeService employeeService;

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllScheduleEntity() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getScheduleByIdEntity(Long id) {
        if (id == null) throw new IncorrectRequestException("id");
        return scheduleRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Schedule"));
    }

    @Override
    public ScheduleDto getScheduleById(Long id) {
        return scheduleMapper.entityToDto(getScheduleByIdEntity(id));
    }

    @Override
    public ScheduleDto createSchedule(ScheduleDtoRequest scheduleDtoRequest) {
        if(scheduleRepository.existsById(scheduleDtoRequest.getId()))
            throw new AlreadyExistException("Schedule", "'id'");
        return scheduleMapper.entityToDto(save(scheduleMapper.dtoToEntity(scheduleDtoRequest)));
    }

    @Override
    public ScheduleDto updateSchedule(ScheduleDtoRequest scheduleDtoRequest) {
        Schedule oldSchedule = scheduleMapper.dtoToEntity(scheduleDtoRequest);
        Schedule newSchedule = getScheduleByIdEntity(scheduleDtoRequest.getId());

        newSchedule.setDayOfWeek(oldSchedule.getDayOfWeek());
        newSchedule.setDueTime(oldSchedule.getDueTime());
        newSchedule.setQuarter(oldSchedule.getQuarter());
        newSchedule.setYear(oldSchedule.getYear());
        newSchedule.setSubjectSchedule(oldSchedule.getSubjectSchedule());
        newSchedule.setGradeSchedule(oldSchedule.getGradeSchedule());
        newSchedule.setTeacherSchedule(oldSchedule.getTeacherSchedule());
        newSchedule.setIsApprove(oldSchedule.getIsApprove());
        return scheduleMapper.entityToDto(save(newSchedule));
    }

    @Override
    public ScheduleDto changeTeacherSchedule(Long teacherId) {
        Schedule schedule = getScheduleByIdEntity(teacherId);
        schedule.setTeacherSchedule(employeeService.findByIdEntity(teacherId));
        return scheduleMapper.entityToDto(save(schedule));
    }

    @Override
    public ScheduleDto restoreSchedule(Long id) {
        Schedule schedule = getScheduleByIdEntity(id);
        schedule.setIsActive(true);
        return scheduleMapper.entityToDto(save(schedule));
    }

    @Override
    public ScheduleDto deleteSchedule(Long id) {
        Schedule schedule = getScheduleByIdEntity(id);
        schedule.setIsActive(false);
        return scheduleMapper.entityToDto(save(schedule));
    }

    @Override
    public List<ScheduleDto> getAllSchedule() {
        return scheduleMapper.entityToDtoList(getAllScheduleEntity());
    }

    @Override
    public List<ScheduleDto> getAllActiveSchedule() {
        List<Schedule> activeSchedule = new ArrayList<>();
        for (Schedule s : getAllScheduleEntity())
            if (s.getIsActive())
                activeSchedule.add(s);
        return scheduleMapper.entityToDtoList(activeSchedule);
    }

    @Override
    public List<ScheduleDto> filterActiveSchedule(List<ScheduleDto> scheduleDtoList) {
        List<ScheduleDto> activeSchedule = new ArrayList<>();
        for (ScheduleDto s : scheduleDtoList)
            if (s.getIsActive())
                activeSchedule.add(s);
        return activeSchedule;
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
        Schedule schedule = getScheduleByIdEntity(id);
        schedule.setIsApprove(true);
        return scheduleMapper.entityToDto(save(schedule));
    }

    @Override
    public ScheduleDto disapproveSchedule(Long id) {
        Schedule schedule = getScheduleByIdEntity(id);
        schedule.setIsApprove(false);
        return scheduleMapper.entityToDto(save(schedule));
    }

    @Override
    public List<ScheduleDto> getAllUnApprovedSchedule() {
        List<Schedule> allSchedule = getAllScheduleEntity();
        List<Schedule> unapprovedSchedule = new ArrayList<>();
        for (Schedule sch : allSchedule) {
            if(!sch.getIsApprove()) unapprovedSchedule.add(sch);
        }
        return scheduleMapper.entityToDtoList(unapprovedSchedule);
    }
}
