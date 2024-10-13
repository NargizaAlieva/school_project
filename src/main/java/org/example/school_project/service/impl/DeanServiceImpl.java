package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.entity.Employee;
import org.example.school_project.entity.Student;
import org.example.school_project.service.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DeanServiceImpl implements DeanService{
    private final ScheduleService scheduleService;
    private final GradeService gradeService;
    private final CharterService charterService;
    private final StudentService studentService;
    private final AssignmentService assignmentService;
    private final EmployeeService employeeService;
    private final UserService userService;
    private final AverageMarkService averageMarkService;
    private final AttendCountService attendCountService;

    private Employee getCurrentEmployee() {
        return employeeService.getByUserId(userService.getCurrentUser().getId());
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
    public ScheduleDto changeTeacherSchedule(Long teacherId) {
        return scheduleService.changeTeacherSchedule(teacherId);
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
    public Map<String, Integer> getAllTeacherScheduleNum() {
        Map<String, Integer> teacherScheduleNum = new HashMap<>();
        for (EmployeeDto e : employeeService.getAllEmployee()) {
            String teacherName = e.getUserDto().getFirstName() + " " + e.getUserDto().getLastName() + " (id = " + e.getId() + ")";
            Integer scheduleCount = scheduleService.getAllScheduleByTeacher(e.getId()).size();
            teacherScheduleNum.put(teacherName, scheduleCount);
        }
        return teacherScheduleNum;
    }

    @Override
    public GradeDto getGradeById(Long id) {
        return gradeService.getById(id);
    }
    @Override
    public GradeDto createGrade(GradeDtoRequest gradeDtoRequest) {
        return gradeService.createGrade(gradeDtoRequest);
    }
    @Override
    public GradeDto updateGrade(GradeDtoRequest gradeDtoRequest) {
        return gradeService.updateGrade(gradeDtoRequest);
    }
    @Override
    public GradeDto deleteGrade(Long id) {
        return gradeService.deleteGrade(id);
    }
    @Override
    public GradeDto restoreGrade(Long id) {
        return gradeService.restoreGrade(id);
    }

    @Override
    public List<GradeDto> getAllActiveGrade() {
        return gradeService.getAllActiveGrade();
    }
    @Override
    public List<GradeDto> getAllGrade() {
        return gradeService.getAllGrade();
    }

    @Override
    public CharterDto createCharter(CharterDtoRequest charterDtoR) {
        return charterService.createCharter(charterDtoR, getCurrentEmployee().getId());
    }
    @Override
    public CharterDto updateCharter(CharterDtoRequest charterDtoR) {
        return charterService.updateCharter(charterDtoR, getCurrentEmployee().getId());
    }
    @Override
    public CharterDto deleteCharter(Long id) {
        return charterService.deleteCharter(id, getCurrentEmployee().getId());
    }
    @Override
    public CharterDto restoreCharter(Long id) {
        return charterService.restoreCharter(id, getCurrentEmployee().getId());
    }
    @Override
    public List<CharterDto> getAllCharter(){
        return charterService.getAllCharter();
    }
    @Override
    public List<CharterDto> getAllCharterByAuthor(){
        return charterService.getAllCharterByAuthor(getCurrentEmployee().getId());
    }

    @Override
    public StudentDto getStudentById(Long id) {
        return studentService.getStudentById(id);
    }
    @Override
    public StudentDto updateStudent(StudentDtoRequest studentDtoRequest) {
        return studentService.updateStudent(studentDtoRequest);
    }
    @Override
    public StudentDto excludeStudent(Long id) {
        return studentService.excludeStudent(id);
    }
    @Override
    public StudentDto restoreStudent(Long id) {
        return studentService.restoreStudent(id);
    }
    @Override
    public List<StudentDto> getAllStudent() {
        return studentService.getAllStudent();
    }

    @Override
    public List<StudentDto> getAllActiveStudent() {
        return studentService.getAllActiveStudent();
    }
    @Override
    public List<StudentDto> getAllActiveStudentByGrade(Long gradeId) {
        return studentService.getAllStudentByGrade(gradeId);
    }

    @Override
    public AssignmentDto createAssignment(AssignmentDtoRequest assignmentDtoRequest) {
        return assignmentService.createAssigment(assignmentDtoRequest, getCurrentEmployee().getId());
    }
    @Override
    public AssignmentDto updateAssignment(AssignmentDtoRequest assignmentDtoRequest) {
        return assignmentService.updateAssignment(assignmentDtoRequest, getCurrentEmployee().getId());
    }
    @Override
    public AssignmentDto deleteAssignment(Long id) {
        return assignmentService.deleteAssignment(id, getCurrentEmployee().getId());
    }
    @Override
    public AssignmentDto restoreAssignment(Long id) {
        return assignmentService.restoreAssignment(id, getCurrentEmployee().getId());
    }
    @Override
    public List<AssignmentDto> getAllAssignmentByAuthor(){
        return assignmentService.getAllAssignmentByAuthor(getCurrentEmployee().getId());
    }
    @Override
    public List<AssignmentDto> getAllUndoneAssigment(){
        return assignmentService.getAllUndoneAssignment(getAllAssignmentByAuthor());
    }

    @Override
    public List<AssignmentDto> getAllDoneAssigment(){
        return assignmentService.getAllDoneAssignment(getAllAssignmentByAuthor());
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
}
