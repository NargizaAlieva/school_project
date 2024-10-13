package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.entity.Employee;
import org.example.school_project.entity.Student;
import org.example.school_project.service.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PrincipleServiceImpl implements PrincipleService {
    private final UserService userService;
    private final StudentService studentService;
    private final ScheduleService scheduleService;
    private final CharterService charterService;
    private final EmployeeService employeeService;
    private final SubjectService subjectService;
    private final AssignmentService assignmentService;
    private final AverageMarkService averageMarkService;
    private final AttendCountService attendCountService;
    private final AnnouncementService announcementService;

    private Employee getPrinciple() {
        return employeeService.getByUserId(userService.getCurrentUser().getId());
    }

    @Override
    public ScheduleDto getScheduleById(Long id) {
        return scheduleService.getScheduleById(id);
    }

    @Override
    public ScheduleDto approveSchedule(Long id) {
        return scheduleService.approveSchedule(id);
    }
    @Override
    public ScheduleDto disapproveSchedule(Long id) {
        return scheduleService.disapproveSchedule(id);
    }

    @Override
    public ScheduleDto deleteSchedule(Long id) {
        return scheduleService.deleteSchedule(id);
    }

    @Override
    public ScheduleDto restoreSchedule(Long id) {
        return scheduleService.restoreSchedule(id);
    }


    @Override
    public List<ScheduleDto> getAllSchedule() {
        return scheduleService.getAllSchedule();
    }

    @Override
    public List<ScheduleDto> getAllActiveSchedule() {
        return scheduleService.getAllActiveSchedule();
    }

    @Override
    public List<ScheduleDto> getAllActiveScheduleByYear(String year) {
        return scheduleService.filterActiveSchedule(scheduleService.getAllScheduleByYear(year));
    }

    @Override
    public List<ScheduleDto> getAllUnapprovedSchedule() {
        return scheduleService.getAllUnApprovedSchedule();
    }

    @Override
    public ScheduleDto createSchedule(ScheduleDtoRequest scheduleDtoRequest) {
        return scheduleService.createSchedule(scheduleDtoRequest);
    }

    @Override
    public ScheduleDto updateSchedule(ScheduleDtoRequest scheduleDtoRequest) {
        return scheduleService.updateSchedule(scheduleDtoRequest);
    }


    @Override
    public EmployeeDto hireEmployee(EmployeeDroRequest employeeDtoR) {
        return employeeService.createEmployee(employeeDtoR);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDroRequest employeeDtoR) {
        return employeeService.updateEmployee(employeeDtoR);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @Override
    public List<EmployeeDto> getAllActiveEmployee() {
        return employeeService.getAllActiveEmployee();
    }

    @Override
    public void fireEmployee(Long id) {
        employeeService.deleteEmployee(id);
    }

    @Override
    public SubjectDto addSubject(SubjectDtoRequest subjectDtoRequest) {
        return subjectService.addSubject(subjectDtoRequest);
    }

    @Override
    public SubjectDto updateSubject(SubjectDtoRequest subjectDtoRequest) {
        return subjectService.updateSubject(subjectDtoRequest);
    }

    @Override
    public SubjectDto deleteSubject(Long id) {
        return subjectService.deleteSubject(id);
    }

    @Override
    public SubjectDto restoreSubject(Long id) {
        return subjectService.restoreSubject(id);
    }

    @Override
    public List<SubjectDto> getAllSubject() {
        return subjectService.getAllSubject();
    }

    @Override
    public List<SubjectDto> getAllActiveSubject() {
        return subjectService.getAllActiveSubject();
    }

    @Override
    public CharterDto createCharter(CharterDtoRequest charterDtoR) {
        return charterService.createCharter(charterDtoR, getPrinciple().getId());
    }

    @Override
    public CharterDto updateCharter(CharterDtoRequest charterDtoR) {
        return charterService.updateCharter(charterDtoR, getPrinciple().getId());
    }

    @Override
    public CharterDto deleteCharter(Long id) {
        return charterService.deleteCharter(id);
    }

    @Override
    public CharterDto restoreCharter(Long id) {
        return charterService.restoreCharter(id);
    }

    @Override
    public List<CharterDto> getAllCharter(){
        return charterService.getAllCharter();
    }

    @Override
    public List<CharterDto> getAllCharterByAuthor(){
        return charterService.getAllCharterByAuthor(getPrinciple().getId());
    }

    @Override
    public AssignmentDto createAssignment(AssignmentDtoRequest assignmentDtoRequest) {
        return assignmentService.createAssigment(assignmentDtoRequest, getPrinciple().getId());
    }

    @Override
    public AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest) {
        return assignmentService.updateAssignment(assignmentDtoRequest, getPrinciple().getId());
    }

    @Override
    public AssignmentDto deleteAssignment(Long id) {
        return assignmentService.deleteAssignment(id, getPrinciple().getId());
    }

    @Override
    public AssignmentDto restoreAssignment(Long id) {
        return assignmentService.restoreAssignment(id, getPrinciple().getId());
    }

    @Override
    public List<AssignmentDto> getAllAssignmentByAuthor(){
        return assignmentService.getAllAssignmentByAuthor(getPrinciple().getId());
    }

    @Override
    public List<AssignmentDto> getAllUndoneAssigment(){
        return assignmentService.getAllUndoneAssignment(getAllAssignmentByAuthor());
    }

    @Override
    public Map<String, Double> getAvgMarkByGradeStudentQuarter(Integer quarter, Long studentId) {
        Student student = studentService.getStudentByIdEntity(studentId);
        return averageMarkService.getAvgMarkByGradeStudentQuarter(quarter, student.getGrade().getId(), studentId);
    }

    @Override
    public Map<String, Double> getAvgMarkBySubjectGradeStudent(Long subjectId, Long studentId) {
        Student student = studentService.getStudentByIdEntity(studentId);
        return averageMarkService.getAvgMarkBySubjectGradeStudent(subjectId, student.getGrade().getId(), studentId);
    }

    @Override
    public Map<String, Double> getAvgMarkByGradeStudent(Long studentId) {
        Student student = studentService.getStudentByIdEntity(studentId);
        return averageMarkService.getAvgMarkByGradeStudent(student.getGrade().getId(), studentId);
    }

    @Override
    public Map<String, Double> getAvgMarkByGradeQuarter(Integer quarter, Long gradeId) {
        return averageMarkService.getAvgMarkByGradeQuarter(quarter, gradeId);
    }

    @Override
    public Map<String, Double> getAvgMarkByGrade(Long gradeId) {
        return averageMarkService.getAvgMarkByGrade(gradeId);
    }

    @Override
    public Map<String, Double> getAvgMarks() {
        return averageMarkService.getAvgMarkAll();
    }
    @Override
    public Map<String, Double> getAvgSchoolMark() {
        Map<String, Double> markMap = new HashMap<>();
        String title = "School average mark:";
        Double avgMark = averageMarkService.getSchoolAvgMark();
        markMap.put(title, avgMark);
        return markMap;
    }

    @Override
    public Map<String, Double> getAttendByQuarterGradeStudent(Integer quarter, Long gradeId, Long studentId) {
        return attendCountService.getAttendByQuarterGradeStudent(quarter, gradeId, studentId);
    }

    @Override
    public Map<String, Double> getAttendBySubjectGradeStudent(Long subjectId, Long gradeId, Long studentId) {
        return attendCountService.getAttendBySubjectGradeStudent(subjectId, gradeId, studentId);
    }

    @Override
    public Map<String, Double> getAttendByGradeStudent(Long gradeId, Long studentId) {
        return attendCountService.getAttendByGradeStudent(gradeId, studentId);
    }

    @Override
    public Map<String, Double> getAttendByQuarterGrade(Integer quarter, Long gradeId) {
        return attendCountService.getAttendByQuarterGrade(quarter, gradeId);
    }

    @Override
    public Map<String, Double> getAttendByGrade(Long gradeId) {
        return attendCountService.getAttendByGrade(gradeId);
    }

    @Override
    public Map<String, Double> getAttendGrades() {
        return attendCountService.getAttendAll();
    }

    @Override
    public AnnouncementDto createAnnouncement(AnnouncementDtoRequest announcementDtoRequest) {
        return announcementService.createAnnouncement(announcementDtoRequest);
    }

    @Override
    public AnnouncementDto updateAnnouncement(AnnouncementDtoRequest announcementDtoRequest) {
        return announcementService.updateAnnouncement(announcementDtoRequest);
    }

    @Override
    public AnnouncementDto deleteAnnouncement(Long id) {
        return announcementService.deleteAnnouncement(id);
    }

    @Override
    public AnnouncementDto restoreAnnouncement(Long id) {
        return announcementService.restoreAnnouncement(id);
    }

    @Override
    public List<AnnouncementDto> getAllAnnouncement() {
        return announcementService.getAllAnnouncement();
    }

    @Override
    public List<AnnouncementDto> getAllActiveAnnouncement() {
        return announcementService.getAllActiveAnnouncement();
    }

    @Override
    public List<AnnouncementDto> getAllSelfAnnouncement() {
        return announcementService.getAllAnnouncementByAuthor(getPrinciple().getId());
    }
}
