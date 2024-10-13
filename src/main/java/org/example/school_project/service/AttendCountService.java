package org.example.school_project.service;

import org.example.school_project.dto.AttendanceDto;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface AttendCountService {
    Map<String, Double> getAttendByQuarterGradeStudent(Integer quarter, Long gradeId, Long studentId);

    Map<String, Double> getAttendByQuarterGrade(Integer quarter, Long gradeId);

    Map<String, Double> getAttendBySubjectGradeStudent(Long subjectId, Long gradeId, Long studentId);

    Map<String, Double> getAttendByGradeStudent(Long gradeId, Long studentId);

    Map<String, Double> getAttendByGrade(Long gradeId);

    Map<String, Double> getAttendAll();

    Double getTrueAttendDtoCount(List<AttendanceDto> attendanceDtoList);

    Double getTrueAttendCount(Collection<Double> doubleList);
}
