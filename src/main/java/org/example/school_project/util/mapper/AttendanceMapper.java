package org.example.school_project.util.mapper;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.AttendanceDto;
import org.example.school_project.entity.Attendance;
import org.example.school_project.service.LessonService;
import org.example.school_project.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AttendanceMapper {
    private final LessonService lessonService;
    private final StudentService studentService;

    public AttendanceDto entityToDto (Attendance attendance) {
        String teacherName = attendance.getLessonAttendance().getSchedule().getTeacherSchedule().getUser().getFirstName() + " " + attendance.getLessonAttendance().getSchedule().getTeacherSchedule().getUser().getLastName();
        if(attendance.getLessonAttendance().getSchedule().getTeacherSchedule().getUser().getMiddleName() != null) teacherName += " " + attendance.getLessonAttendance().getSchedule().getTeacherSchedule().getUser().getMiddleName();

        String studentName = attendance.getStudentAttendance().getUser().getFirstName() + attendance.getStudentAttendance().getUser().getLastName();
        if(attendance.getStudentAttendance().getUser().getMiddleName() != null) studentName += " " + attendance.getStudentAttendance().getUser().getMiddleName();

        AttendanceDto attendanceDto = new AttendanceDto();
        attendanceDto.setId(attendance.getId());
        attendanceDto.setIsAttended(attendance.getIsAttended());
        attendanceDto.setLessonId(attendance.getLessonAttendance().getId());
        attendanceDto.setLessonTopic(attendance.getLessonAttendance().getTopic());
        attendanceDto.setSubjectId(attendance.getLessonAttendance().getSchedule().getSubjectSchedule().getId());
        attendanceDto.setSubjectTitle(attendance.getLessonAttendance().getSchedule().getSubjectSchedule().getTitle());
        attendanceDto.setTeacherId(attendance.getLessonAttendance().getSchedule().getTeacherSchedule().getId());
        attendanceDto.setTeacherName(teacherName);
        attendanceDto.setStudentId(attendance.getLessonAttendance().getSchedule().getId());
        attendanceDto.setStudentId(attendance.getStudentAttendance().getId());
        attendanceDto.setStudentName(studentName);
        return attendanceDto;
    }

    public List<AttendanceDto> entityToDtoList(List<Attendance> attendances) {
        return attendances.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Attendance dtoToEntity(AttendanceDto attendanceDto) {
        Attendance attendance = new Attendance();
        attendance.setId(attendanceDto.getId());
        attendance.setIsAttended(attendanceDto.getIsAttended());
        attendance.setStudentAttendance(studentService.getStudentByIdEntity(attendanceDto.getStudentId()));
        attendance.setLessonAttendance(lessonService.getLessonByIdEntity(attendanceDto.getLessonId()));
        return attendance;
    }
}
