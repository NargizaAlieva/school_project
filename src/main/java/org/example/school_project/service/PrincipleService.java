package org.example.school_project.service;

import org.example.school_project.dto.*;
import org.example.school_project.entity.*;

import java.util.List;
import java.util.Map;

public interface PrincipleService {
    // methods connected with schedule
    ScheduleDto getScheduleById(Long id);
    ScheduleDto approveSchedule(Long id);
    ScheduleDto disapproveSchedule(Long id);
    ScheduleDto deleteSchedule(Long id);
    ScheduleDto restoreSchedule(Long id);

    List<ScheduleDto> getAllSchedule();
    List<ScheduleDto> getAllActiveSchedule();
    List<ScheduleDto> getAllActiveScheduleByYear(String year);
    List<ScheduleDto> getAllUnapprovedSchedule();
    ScheduleDto createSchedule(ScheduleDtoRequest scheduleDtoRequest);
    ScheduleDto updateSchedule(ScheduleDtoRequest scheduleDtoRequest);

    //methods connected with employee
    EmployeeDto hireEmployee(EmployeeDroRequest employeeDtoR);
    EmployeeDto updateEmployee(EmployeeDroRequest employeeDtoR);
    List<EmployeeDto> getAllEmployee();
    List<EmployeeDto> getAllActiveEmployee();
    void fireEmployee(Long id);

    // methods connected with subject
    SubjectDto addSubject(SubjectDtoRequest subjectDtoRequest);
    SubjectDto updateSubject(SubjectDtoRequest subjectDtoRequest);
    SubjectDto deleteSubject(Long id);
    SubjectDto restoreSubject(Long id);

    List<SubjectDto> getAllSubject();

    List<SubjectDto> getAllActiveSubject();

    // methods connected with charter
    CharterDto createCharter(CharterDtoRequest charterDtoR);
    CharterDto updateCharter(CharterDtoRequest charterDtoR);

    CharterDto deleteCharter(Long id);

    CharterDto restoreCharter(Long id);

    List<CharterDto> getAllCharter();

    List<CharterDto> getAllCharterByAuthor();

    AssignmentDto createAssignment(AssignmentDtoRequest assignmentDtoRequest);
    AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest);

    AssignmentDto deleteAssignment(Long id);
    AssignmentDto restoreAssignment(Long id);
    List<AssignmentDto> getAllAssignmentByAuthor();
    List<AssignmentDto> getAllUndoneAssigment();


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

    AnnouncementDto createAnnouncement(AnnouncementDtoRequest announcementDtoRequest);

    AnnouncementDto updateAnnouncement(AnnouncementDtoRequest announcementDtoRequest);

    AnnouncementDto deleteAnnouncement(Long id);

    AnnouncementDto restoreAnnouncement(Long id);

    List<AnnouncementDto> getAllAnnouncement();

    List<AnnouncementDto> getAllActiveAnnouncement();

    List<AnnouncementDto> getAllSelfAnnouncement();
}
