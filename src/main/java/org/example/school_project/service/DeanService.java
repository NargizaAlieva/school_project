package org.example.school_project.service;

import org.example.school_project.dto.*;

import java.util.List;
import java.util.Map;

public interface DeanService {
    ScheduleDto createSchedule(ScheduleDtoRequest scheduleDtoRequest);
    ScheduleDto updateSchedule(ScheduleDtoRequest scheduleDtoRequest);

    ScheduleDto changeTeacherSchedule(Long teacherId);

    ScheduleDto deleteSchedule(Long id);
    ScheduleDto restoreSchedule(Long id);
    List<ScheduleDto> getAllSchedule();
    List<ScheduleDto> getAllActiveSchedule();

    Map<String, Integer> getAllTeacherScheduleNum();

    GradeDto getGradeById(Long id);
    GradeDto createGrade(GradeDtoRequest gradeDtoRequest);
    GradeDto updateGrade(GradeDtoRequest gradeDtoRequest);
    GradeDto deleteGrade(Long id);

    GradeDto restoreGrade(Long id);

    List<GradeDto> getAllActiveGrade();
    List<GradeDto> getAllGrade();

    CharterDto createCharter (CharterDtoRequest charterDtoR);
    CharterDto updateCharter (CharterDtoRequest charterDtoR);
    CharterDto deleteCharter(Long id);
    CharterDto restoreCharter(Long id);
    List<CharterDto> getAllCharter();
    List<CharterDto> getAllCharterByAuthor();

    StudentDto getStudentById(Long id);
    StudentDto updateStudent(StudentDtoRequest studentDtoRequest);
    StudentDto excludeStudent(Long id);

    StudentDto restoreStudent(Long id);

    List<StudentDto> getAllStudent();
    List<StudentDto> getAllActiveStudent();
    List<StudentDto> getAllActiveStudentByGrade(Long gradeId);

    AssignmentDto createAssignment(AssignmentDtoRequest assignmentDtoRequest);
    AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest);
    AssignmentDto deleteAssignment(Long id);
    AssignmentDto restoreAssignment(Long id);
    List<AssignmentDto> getAllAssignmentByAuthor();
    List<AssignmentDto> getAllUndoneAssigment();

    List<AssignmentDto> getAllDoneAssigment();

    Map<String, Double> getAvgMarkByGradeStudentQuarter(Integer quarter, Long studentId);

    Map<String, Double> getAvgMarkBySubjectGradeStudent(Long subjectId, Long studentId);

    Map<String, Double> getAvgMarkByGradeStudent(Long studentId);

    Map<String, Double> getAvgMarkByGradeQuarter(Integer quarter, Long gradeId);

    Map<String, Double> getAvgMarkByGrade(Long gradeId);

    Map<String, Double> getAvgMarks();

    Map<String, Double> getAvgSchoolMark();

    Map<String, Double> getAttendByQuarterGradeStudent(Integer quarter, Long gradeId, Long studentId);

    Map<String, Double> getAttendBySubjectGradeStudent(Long subjectId, Long gradeId, Long studentId);

    Map<String, Double> getAttendByGradeStudent(Long gradeId, Long studentId);

    Map<String, Double> getAttendByQuarterGrade(Integer quarter, Long gradeId);

    Map<String, Double> getAttendByGrade(Long gradeId);

    Map<String, Double> getAttendGrades();
}
