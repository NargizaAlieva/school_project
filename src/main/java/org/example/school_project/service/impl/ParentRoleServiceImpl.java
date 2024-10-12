package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.entity.Parent;
import org.example.school_project.entity.Student;
import org.example.school_project.service.*;
import org.example.school_project.util.mapper.HomeworkToDoMapping;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ParentRoleServiceImpl implements ParentRoleService {
    private final UserService userService;
    private final MarkService markService;
    private final HomeworkService homeworkService;
    private final ScheduleService scheduleService;
    private final LessonService lessonService;
    private final ReviewService reviewService;
    private final StudentService studentService;
    private final HomeworkToDoMapping homeworkToDoMapping;
    private final AverageMarkService averageMarkService;
    private final AttendCountService attendCountService;
    private final ParentService parentService;

    public Parent getCurrentStudent() {
        return parentService.getByIdEntity(userService.getCurrentUser().getId());
    }
    @Override
    public StudentDto createStudent(StudentDtoRequest studentDtoRequest) {
        return studentService.createStudent(studentDtoRequest);
    }

    @Override
    public List<MarkDto> getAllMark(Long childId) {
        Student child = getCurrentStudent().getChildrenList().get(Math.toIntExact(childId));
        return markService.getAllMarkByStudent(child.getId());    }

    @Override
    public Map<String, Double> getAvgMarkByGradeStudentQuarter(Integer quarter, Long childId) {
        Student child = getCurrentStudent().getChildrenList().get(Math.toIntExact(childId));
        return averageMarkService.getAvgMarkByGradeStudentQuarter(quarter, child.getGrade().getId(), child.getId());
    }

    @Override
    public Map<String, Double> getAvgMarkBySubjectGradeStudent(Long subjectId, Long childId) {
        Student child = getCurrentStudent().getChildrenList().get(Math.toIntExact(childId));
        return averageMarkService.getAvgMarkBySubjectGradeStudent(subjectId, child.getGrade().getId(), child.getId());
    }

    @Override
    public Map<String, Double> getAvgMarkByGradeStudent(Long childId) {
        Student child = getCurrentStudent().getChildrenList().get(Math.toIntExact(childId));
        return averageMarkService.getAvgMarkByGradeStudent(child.getGrade().getId(), child.getId());
    }

    @Override
    public Map<String, Double> getAttendByQuarterGradeStudent(Integer quarter, Long childId) {
        Student child = getCurrentStudent().getChildrenList().get(Math.toIntExact(childId));
        return attendCountService.getAttendByQuarterGradeStudent(quarter, child.getGrade().getId(), child.getId());
    }

    @Override
    public Map<String, Double> getAttendBySubjectGradeStudent(Long subjectId, Long childId) {
        Student child = getCurrentStudent().getChildrenList().get(Math.toIntExact(childId));
        return attendCountService.getAttendBySubjectGradeStudent(subjectId, child.getGrade().getId(), child.getId());
    }

    @Override
    public Map<String, Double> getAttendByGradeStudent(Long childId) {
        Student child = getCurrentStudent().getChildrenList().get(Math.toIntExact(childId));
        return attendCountService.getAttendByGradeStudent(child.getGrade().getId(), child.getId());
    }

    @Override
    public List<ScheduleDto> getStudentSchedule(Long studentId) {
        return scheduleService.getAllScheduleByStudent(studentId);
    }

    @Override
    public List<HomeworkDto> getAllHomework(Long childId) {
        Student child = getCurrentStudent().getChildrenList().get(Math.toIntExact(childId));
        return homeworkService.getHwByStudent(child.getId());
    }

    @Override
    public List<HomeworkToDoDto> getAllUndoneHomework(Long childId) {
        Student child = getCurrentStudent().getChildrenList().get(Math.toIntExact(childId));
        return homeworkToDoMapping.entityToDtoList(lessonService.getUndoneHwByStudent(getAllHomework(childId), child.getId()));
    }

    @Override
    public List<HomeworkDto> getAllHomeworkSubject(Long childId, Long subjectId) {
        Student child = getCurrentStudent().getChildrenList().get(Math.toIntExact(childId));
        return homeworkService.getHwByStudentSubject(child.getId(), subjectId);
    }

    @Override
    public List<HomeworkToDoDto> getAllUndoneHomework(Long childId, Long subjectId) {
        Student child = getCurrentStudent().getChildrenList().get(Math.toIntExact(childId));
        return homeworkToDoMapping.entityToDtoList(lessonService.getUndoneHwByStudent(getAllHomework(child.getId()), subjectId));
    }

    @Override
    public List<ReviewDto> getStudentReview(Long childId) {
        Student child = getCurrentStudent().getChildrenList().get(Math.toIntExact(childId));
        return reviewService.getReviewByStudentId(child.getId());
    }

    @Override
    public void leaveSchool(Long childId) {
        Student child = getCurrentStudent().getChildrenList().get(Math.toIntExact(childId));
        userService.deleteUser(child.getUser().getId());
    }
}
