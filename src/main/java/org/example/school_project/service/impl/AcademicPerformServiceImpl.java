package org.example.school_project.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.service.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcademicPerformServiceImpl implements AverageMarkService, AttendCountService {
    private final MarkService markService;
    private final SubjectService subjectService;
    private final StudentService studentService;
    private final GradeService gradeService;
    private final HomeworkService homeworkService;
    private final AttendanceService attendanceService;
    private final ScheduleService scheduleService;

    @Override
    public Map<String, Double> getAvgMarkByGradeStudentQuarter(Integer quarter, Long gradeId, Long studentId) {
        Map<String, Double> markMap = new HashMap<>();
        for (SubjectDto s : subjectService.getSubjectForGrade(gradeId, scheduleService.getAllActiveSchedule())) {
            Double avgMark = 0.0;
            String subjectName = "Subject: " + s.getTitle();
            if (!(markService.getStudentMarksBySubjectGrade(s.getId(), quarter, gradeId, studentId)).isEmpty()) {
                avgMark = countAvgByMarkDto(
                        markService.getStudentMarksBySubjectGrade(s.getId(), quarter, gradeId, studentId));
            }
            if (!(homeworkService.getStudentHwBySubjectGrade(s.getId(), quarter, gradeId, studentId)).isEmpty()) {
                avgMark += countAvgByHwDto(
                        homeworkService.getStudentHwBySubjectGrade(s.getId(), quarter, gradeId, studentId));
            }
            markMap.put(subjectName, avgMark/2);
        }
        return markMap;
    }

    @Override
    public Map<String, Double> getAvgMarkByGradeQuarter(Integer quarter, Long gradeId) {
        Map<String, Double> markMap = new HashMap<>();
        for (StudentDto s : studentService.getAllStudentByGrade(gradeId)) {
            Collection<Double> avgMarkBySubjectGrade = getAvgMarkByGradeStudentQuarter(quarter, gradeId, s.getId()).values();

            String studentName = "Student: " + s.getUser().getFirstName() + " " + s.getUser().getLastName();
            Double avgMark = countAvgByDouble(avgMarkBySubjectGrade);
            markMap.put(studentName, avgMark);
        }
        return markMap;
    }

    public Map<String, Double> getAvgMarkBySubjectGradeStudent(Long subjectId, Long gradeId, Long studentId) {
        Map<String, Double> markMap = new HashMap<>();
        for (int quarter = 1; quarter <= 4; quarter++) {
            Double avgMark = 0.0;
            String quarterName = "Quarter: " + quarter;
            if (!(markService.getStudentMarksBySubjectGrade(subjectId, quarter, gradeId, studentId)).isEmpty()) {
                avgMark = countAvgByMarkDto(
                        markService.getStudentMarksBySubjectGrade(subjectId, quarter, gradeId, studentId));
            }
            if (!(homeworkService.getStudentHwBySubjectGrade(subjectId, quarter, gradeId, studentId)).isEmpty()) {
                avgMark += countAvgByHwDto(
                        homeworkService.getStudentHwBySubjectGrade(subjectId, quarter, gradeId, studentId));
            }
            markMap.put(quarterName, avgMark/2);
        }
        return markMap;
    }

    @Override
    public Map<String, Double> getAvgMarkByGradeStudent(Long gradeId, Long studentId) {
        Map<String, Double> markMap = new HashMap<>();
        for (SubjectDto s : subjectService.getSubjectForGrade(gradeId, scheduleService.getAllActiveSchedule())) {
            Collection<Double> avgMarkBySubjectGradeStudent = getAvgMarkBySubjectGradeStudent(s.getId(), gradeId, studentId).values();

            String subjectName = "Subject: " + s.getTitle();
            Double avgMark = countAvgByDouble(avgMarkBySubjectGradeStudent);
            markMap.put(subjectName, avgMark);
        }
        return markMap;
    }

    @Override
    public Map<String, Double> getAvgMarkByGrade(Long gradeId) {
        Map<String, Double> markMap = new HashMap<>();
        for (StudentDto s : studentService.getAllStudentByGrade(gradeId)) {
            Collection<Double> avgMarkBySubjectGrade = getAvgMarkByGradeStudent(gradeId, s.getId()).values();

            String studentName = "Student: " + s.getUser().getFirstName() + " " + s.getUser().getLastName();
            Double avgMark = countAvgByDouble(avgMarkBySubjectGrade);
            markMap.put(studentName, avgMark);
        }
        return markMap;
    }

    @Override
    public Map<String, Double> getAvgMarkAll() {
        Map<String, Double> markMap = new HashMap<>();
        for (GradeDto g : gradeService.getAllActiveGrade()) {
            List<Double> avgMarkByGrade = (List<Double>) getAvgMarkByGrade(g.getId()).values();

            String gradeName = "Grade: " + g.getTitle();
            Double avgMark = countAvgByDouble(avgMarkByGrade);
            markMap.put(gradeName, avgMark);
        }
        return markMap;
    }

    @Override
    public Double getSchoolAvgMark() {
        List<Double> avgMarkByGrade = (List<Double>) getAvgMarkAll().values();
        return countAvgByDouble(avgMarkByGrade);
    }

    @Override
    public Map<String, Double> getAvgMarkBySubjectGradeQuarter(Long subjectId, Long gradeId, Integer quarter) {
        Map<String, Double> markMap = new HashMap<>();
        for (StudentDto s : studentService.getAllStudentByGrade(gradeId)) {
            Double avgMark = 0.0;
            String studentName = "Student: " + s.getUser().getFirstName() + " " + s.getUser().getLastName();
            if (!(markService.getStudentMarksBySubjectGrade(subjectId, quarter, gradeId, s.getId())).isEmpty()) {
                avgMark = countAvgByMarkDto(
                        markService.getStudentMarksBySubjectGrade(subjectId, quarter, gradeId, s.getId()));
            }
            if (!(homeworkService.getStudentHwBySubjectGrade(subjectId, quarter, gradeId, s.getId())).isEmpty()) {
                avgMark += countAvgByHwDto(
                        homeworkService.getStudentHwBySubjectGrade(subjectId, quarter, gradeId, s.getId()));
            }
            markMap.put(studentName, avgMark/2);
        }
        return markMap;
    }

    @Override
    public Map<String, Double> getAvgMarkBySubjectGrade(Long subjectId, Long gradeId) {
        Map<String, Double> markMap = new HashMap<>();
        for (StudentDto s : studentService.getAllStudentByGrade(gradeId)) {
            Collection<Double> avgMarkBySubjectGrade = getAvgMarkBySubjectGradeStudent(subjectId, gradeId, s.getId()).values();

            String studentName = "Student: " + s.getUser().getFirstName() + " " + s.getUser().getLastName();
            Double avgMark = countAvgByDouble(avgMarkBySubjectGrade);
            markMap.put(studentName, avgMark);
        }
        return markMap;
    }

    @Override
    public Map<String, Double> getAvgMarkBySubject(Long subjectId) {
        Map<String, Double> markMap = new HashMap<>();
        for (GradeDto g : gradeService.getAllActiveGrade()) {
            Collection<Double> avgMarkByGrade = getAvgMarkBySubjectGrade(subjectId, g.getId()).values();

            String gradeName = "Grade: " + g.getTitle();
            Double avgMark = countAvgByDouble(avgMarkByGrade);
            markMap.put(gradeName, avgMark);
        }
        return markMap;
    }

    @Override
    public Double countAvgByMarkDto(List<MarkDto> markDtoList) {
        Double sum = 0.0;
        for (MarkDto m : markDtoList) sum += m.getMark();
        return sum/markDtoList.size();
    }

    @Override
    public Double countAvgByHwDto(List<HomeworkDto> markDtoList) {
        Double sum = 0.0;
        for (HomeworkDto h : markDtoList) sum += h.getMark();
        return Math.round(sum / markDtoList.size() * 100) / 100.;
    }

    @Override
    public Double countAvgByDouble(Collection<Double> markList) {
        Double sum = 0.0;
        for (Double m : markList)
            sum += m;
        return Math.round(sum / markList.size() * 100) / 100.;
    }

    @Override
    public Map<String, Double> getAttendByQuarterGradeStudent(Integer quarter, Long gradeId, Long studentId) {
        Map<String, Double> attendMap = new HashMap<>();
        for (SubjectDto s : subjectService.getSubjectForGrade(gradeId, scheduleService.getAllActiveSchedule())) {
            Double trueAttend = 0.0;
            String subjectName = "Subject: " + s.getTitle();
            if (!(attendanceService.getStudentAttendBySubjectGrade(s.getId(), quarter, gradeId, studentId).isEmpty())) {
                trueAttend = getTrueAttendDtoCount(
                        attendanceService.getStudentAttendBySubjectGrade(s.getId(), quarter, gradeId, studentId));
            }
            attendMap.put(subjectName, trueAttend);
        }
        return attendMap;
    }

    @Override
    public Map<String, Double> getAttendByQuarterGrade(Integer quarter, Long gradeId) {
        Map<String, Double> attendMap = new HashMap<>();
        for (StudentDto s : studentService.getAllStudentByGrade(gradeId)) {
            Collection<Double> attendSubjectGrade = getAttendByQuarterGradeStudent(quarter, gradeId, s.getId()).values();

            String studentName = "Student: " + s.getUser().getFirstName() + " " + s.getUser().getLastName();
            Double trueAttend = getTrueAttendCount(attendSubjectGrade);
            attendMap.put(studentName, trueAttend);
        }
        return attendMap;
    }

    @Override
    public Map<String, Double> getAttendBySubjectGradeStudent(Long subjectId, Long gradeId, Long studentId) {
        Map<String, Double> attendMap = new HashMap<>();
        for (int quarter = 1; quarter <= 4; quarter++) {
            Double trueAttend = 0.0;
            String quarterName = "Quarter: " + quarter;
            if (!(attendanceService.getStudentAttendBySubjectGrade(subjectId, quarter, gradeId, studentId).isEmpty())) {
                trueAttend = getTrueAttendDtoCount(
                        attendanceService.getStudentAttendBySubjectGrade(subjectId, quarter, gradeId, studentId));
            }
            attendMap.put(quarterName, trueAttend);
        }
        return attendMap;
    }

    @Override
    public Map<String, Double> getAttendByGradeStudent(Long gradeId, Long studentId) {
        Map<String, Double> attendMap = new HashMap<>();
        for (SubjectDto s : subjectService.getSubjectForGrade(gradeId, scheduleService.getAllActiveSchedule())) {
            Collection<Double> attendSubjectGradeStudent = getAttendBySubjectGradeStudent(s.getId(), gradeId, studentId).values();

            String subjectName = "Subject: " + s.getTitle();
            Double trueAttend = getTrueAttendCount(attendSubjectGradeStudent);
            attendMap.put(subjectName, trueAttend);
        }
        return attendMap;
    }

    @Override
    public Map<String, Double> getAttendByGrade(Long gradeId) {
        Map<String, Double> attendMap = new HashMap<>();
        for (StudentDto s : studentService.getAllStudentByGrade(gradeId)) {
            Collection<Double> attendSubjectGrade = getAttendByGradeStudent(gradeId, s.getId()).values();

            String studentName = "Student: " + s.getUser().getFirstName() + " " + s.getUser().getLastName();
            Double trueAttend = getTrueAttendCount(attendSubjectGrade);
            attendMap.put(studentName, trueAttend);
        }
        return attendMap;
    }

    @Override
    public Map<String, Double> getAttendAll() {
        Map<String, Double> markMap = new HashMap<>();
        for (GradeDto g : gradeService.getAllActiveGrade()) {
            List<Double> attendByGrade = (List<Double>) getAttendByGrade(g.getId()).values();

            String gradeName = "Grade: " + g.getTitle();
            Double trueAttend = getTrueAttendCount(attendByGrade);
            markMap.put(gradeName, trueAttend);
        }
        return markMap;
    }

    @Override
    public Double getTrueAttendDtoCount(List<AttendanceDto> attendanceDtoList) {
        int count = 0;
        for (AttendanceDto a : attendanceDtoList)
            if (a.getIsAttended())
                count++;
        return Math.round(count * 1000.0 / attendanceDtoList.size()) / 10.0;
    }

    @Override
    public Double getTrueAttendCount(Collection<Double> doubleList) {
        Double count = 0.0;
        for (Double d : doubleList)
                count += d;
        return Math.round(count * 10.0 / doubleList.size()) / 10.0;
    }
}
