package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.entity.Student;
import org.example.school_project.service.*;
import org.example.school_project.util.mapper.HomeworkToDoMapping;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentRoleServiceImpl implements StudentRoleService {
    private final UserService userService;
    private final MarkService markService;
    private final HomeworkService homeworkService;
    private final ScheduleService scheduleService;
    private final LessonService lessonService;
    private final SubjectService subjectService;
    private final StudentService studentService;
    private final HomeworkToDoMapping homeworkToDoMapping;
    private final AttendanceService attendanceService;

    public Student getCurrentStudent() {
        return studentService.getStudentByUserId(userService.getCurrentUser().getId());
    }

    @Override
    public List<MarkDto> getAllMark() {
        return markService.getAllMarkByStudent(getCurrentStudent().getId());
    }

    @Override
    public List<LessonDto> getAllMarkByYearSubjectQuarter(String year, Long subjectId, Integer quarter) {
        List<LessonDto> allLessonByGrade = lessonService.getAllLessonByGradeId(lessonService.getAllLesson(), getCurrentStudent().getGrade().getId());
        List<LessonDto> allLessonByYear = lessonService.getAllLessonByYear(allLessonByGrade, year);
        List<LessonDto> allLessonBySubject = lessonService.getAllLessonBySubjectId(allLessonByYear, subjectId);
        List<LessonDto> allLessonBySubjec = lessonService.getAllLessonByQuarter(allLessonBySubject, quarter);
        return lessonService.getAllLessonByQuarter(allLessonBySubject, quarter);
    }

    @Override
    public List<Double> getGradeByYearSubject(String year, Long subjectId) {
        List<Double> gradeForYearSubject = new ArrayList<>();
        for (int quarter = 1; quarter <= 4; quarter++) {
            gradeForYearSubject.add(
                    markService.getGradeByMarkDto(
                            markService.filterMark(getAllMarkByYearSubjectQuarter(year, subjectId, quarter))));
            gradeForYearSubject.add(
                    homeworkService.getGradeByMarkDto(
                            homeworkService.convertToHw(getAllMarkByYearSubjectQuarter(year, subjectId, quarter))));
        }
        return gradeForYearSubject;
    }

    @Override
    public List<Double> getGradeByQuarterYear(Integer quarter, String year) {
        List<Double> gradeForQuarterYear = new ArrayList<>();
        for (SubjectDto s : getStudentSubject(year)) {
            gradeForQuarterYear.
                    add(markService.getGradeByMarkDto(
                            markService.filterMark(getAllMarkByYearSubjectQuarter(year, s.getId(), quarter))));
            gradeForQuarterYear.
                    add(homeworkService.getGradeByMarkDto(
                            homeworkService.convertToHw(getAllMarkByYearSubjectQuarter(year, s.getId(), quarter))));
        }
        return gradeForQuarterYear;
    }

    @Override
    public List<Double> getGradeByYear(String year) {
        List<Double> gradeForYear = new ArrayList<>();
        for (int quarter = 1; quarter < 5; quarter++) {
            gradeForYear.add(markService.getGradeByDouble(getGradeByQuarterYear(quarter, year)) / 4);
        }
        gradeForYear.add(markService.getGradeByDouble(gradeForYear));
        return gradeForYear;
    }

    @Override
    public HomeworkDto sendHomework(HomeworkDtoRequest homeworkDtoRequest) {
        return homeworkService.createHw(homeworkDtoRequest);
    }

    @Override
    public List<HomeworkDto> getAllHomework() {
        return homeworkService.getHwByStudent(getCurrentStudent().getId());
    }

    @Override
    public List<HomeworkToDoDto> getAllUndoneHomework() {
        return homeworkToDoMapping.entityToDtoList(lessonService.getUndoneHwByStudent(getAllHomework(), getCurrentStudent().getId()));
    }

    @Override
    public List<HomeworkDto> getAllHomeworkSubject(Long subjectId) {
        return homeworkService.getHwByStudentSubject(getCurrentStudent().getId(), subjectId);
    }

    @Override
    public List<HomeworkToDoDto> getAllUndoneHomework(Long subjectId) {
        return homeworkToDoMapping.entityToDtoList(lessonService.getUndoneHwByStudent(getAllHomework(), getCurrentStudent().getId(),subjectId));
    }

    @Override
    public List<AttendanceDto> getAttendance() {
        return attendanceService.getAllAttendanceStudent(lessonService.getAllLesson(), getCurrentStudent().getId());
    }

    @Override
    public List<AttendanceDto> getAttendanceSubject(Long subjectId) {
        return attendanceService.getAllAttendanceStudent(lessonService.getAllLessonBySubjectId(lessonService.getAllLesson(), subjectId), getCurrentStudent().getId());
    }

    @Override
    public List<AttendanceDto> getAttendanceByYearSubject(String year, Long subjectId) {
        List<AttendanceDto> attendanceYearSubject = new ArrayList<>();
        for (int quarter = 1; quarter <= 4; quarter++) {
            attendanceYearSubject.add((AttendanceDto) attendanceService.getAllAttendanceStudent(
                            getAllMarkByYearSubjectQuarter(year, subjectId, quarter),
                    getCurrentStudent().getId()));
        }
        return attendanceYearSubject;
    }

    @Override
    public List<ScheduleDto> getStudentSchedule() {
        return scheduleService.getAllScheduleByStudent(getCurrentStudent().getId());
    }

    @Override
    public List<LessonDto> getAllLessonByGrade() {
        return lessonService.getAllLessonByGradeId(lessonService.getAllLesson(), getCurrentStudent().getGrade().getId());
    }

    @Override
    public List<StudentDto> getClassmates() {
        return studentService.getAllStudentByGrade(getCurrentStudent().getGrade().getId());
    }

    @Override
    public List<SubjectDto> getStudentSubject(String year) {
        return subjectService.getSubjectForGrade(scheduleService.getAllSchedule(), getCurrentStudent().getGrade().getId(), year);
    }
}
