package org.example.school_project.service;

import org.example.school_project.dto.AttendanceDto;
import org.example.school_project.dto.AttendanceDtoRequest;
import org.example.school_project.dto.LessonDto;
import org.example.school_project.entity.Attendance;

import java.util.List;

public interface AttendanceService {
    Attendance getAttendanceByIdEntity(Long id);
    AttendanceDto getAttendanceById(Long id);
    AttendanceDto createAttendance(AttendanceDtoRequest attendanceDtoRequest);
    AttendanceDto updateAttendance(AttendanceDtoRequest attendanceDtoRequest);
    List<AttendanceDto> filterAttendance(List<LessonDto> lessonDtoList);

    List<AttendanceDto> filterTrueAttendance(List<LessonDto> lessonDtoList, Long studentId);

    List<AttendanceDto> getAllAttendanceStudent(List<LessonDto> lessonDtoList, Long studentId);

    List<AttendanceDto> countAttendanceStudent(List<LessonDto> lessonDtoList, Long studentId);

    Integer countAttend(List<AttendanceDto> attendanceDtoList);

}
