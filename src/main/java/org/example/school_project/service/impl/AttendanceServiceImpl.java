package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.AttendanceDto;
import org.example.school_project.dto.AttendanceDtoRequest;
import org.example.school_project.dto.LessonDto;
import org.example.school_project.entity.Attendance;
import org.example.school_project.repository.AttendanceRepository;
import org.example.school_project.service.AttendanceService;
import org.example.school_project.service.LessonService;
import org.example.school_project.util.exception.AlreadyExistException;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.AttendanceMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final LessonService lessonService;

    public Attendance save(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }
    public List<AttendanceDto> getAllAttendance() {
        return attendanceMapper.entityToDtoList(attendanceRepository.findAll());
    }
    @Override
    public Attendance getAttendanceByIdEntity(Long id) {
        return attendanceRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Attendance"));
    }

    @Override
    public AttendanceDto getAttendanceById(Long id) {
        return attendanceMapper.entityToDto(getAttendanceByIdEntity(id));
    }

    @Override
    public AttendanceDto createAttendance(AttendanceDtoRequest attendanceDtoRequest) {
        if (attendanceRepository.existsById(attendanceDtoRequest.getId()))
            throw new AlreadyExistException("Attendance", "'id'");
        return attendanceMapper.entityToDto(save(attendanceMapper.dtoToEntity(attendanceDtoRequest)));
    }

    @Override
    public AttendanceDto updateAttendance(AttendanceDtoRequest attendanceDtoRequest) {
        Attendance oldAttendance = attendanceMapper.dtoToEntity(attendanceDtoRequest);
        Attendance newAttendance = getAttendanceByIdEntity(attendanceDtoRequest.getId());

        newAttendance.setId(oldAttendance.getId());
        newAttendance.setIsAttended(oldAttendance.getIsAttended());
        newAttendance.setStudentAttendance(oldAttendance.getStudentAttendance());
        newAttendance.setLessonAttendance(oldAttendance.getLessonAttendance());
        return attendanceMapper.entityToDto(newAttendance);
    }

    @Override
    public List<AttendanceDto> getStudentAttendBySubjectGrade(Long subjectId, Integer quarter, Long gradeId, Long studentId) {
        List<AttendanceDto> allHw = new ArrayList<>();
        List<LessonDto> lessonDtoList = lessonService.getAllLessonBySubjectQuarter(subjectId, quarter, gradeId);
        for (LessonDto l : lessonDtoList) {
            for (AttendanceDto a : getAllAttendance())
                if (l.getId().equals(a.getLessonId()))
                    if (a.getStudentId().equals(studentId))
                        allHw.add(a);
        }
        return allHw;
    }
}
