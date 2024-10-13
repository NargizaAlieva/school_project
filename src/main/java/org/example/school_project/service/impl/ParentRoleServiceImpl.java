package org.example.school_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.entity.Parent;
import org.example.school_project.entity.Student;
import org.example.school_project.service.*;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.HomeworkToDoMapping;
import org.example.school_project.util.mapper.StudentMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
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
    private final StudentMapper studentMapper;

    public Parent getCurrentStudent() {
        return parentService.getParentByUserId(userService.getCurrentUser().getId());
    }
    public Student getChild(Long childId) {
        if (getCurrentStudent().getChildrenList().size() < childId)
            throw new ObjectNotFoundException("Child");
        return getCurrentStudent().getChildrenList().get(Math.toIntExact(childId-1));
    }
    @Override
    public Map<String, StudentDto> getChildList() {
        Map<String, StudentDto> studentDtoMap = new HashMap<>();
        for (int i = 1; i <= getCurrentStudent().getChildrenList().size(); i++) {
            String childIndex = "Child " + i;
            StudentDto childDto = studentMapper.entityToDto(getChild((long) i));
            studentDtoMap.put(childIndex, childDto);
        }
        return studentDtoMap;
    }
    @Override
    public StudentDto createStudent(StudentDtoRequest studentDtoRequest) {
        return studentService.createStudent(studentDtoRequest);
    }

    @Override
    public List<MarkDto> getAllMark(Long childId) {
        Student child = getChild(childId);
        return markService.getAllMarkByStudent(child.getId());    }

    @Override
    public Map<String, Double> getAvgMarkByGradeStudentQuarter(Integer quarter, Long childId) {
        Student child = getChild(childId);
        return averageMarkService.getAvgMarkByGradeStudentQuarter(quarter, child.getGrade().getId(), child.getId());
    }

    @Override
    public Map<String, Double> getAvgMarkBySubjectGradeStudent(Long subjectId, Long childId) {
        Student child = getChild(childId);
        return averageMarkService.getAvgMarkBySubjectGradeStudent(subjectId, child.getGrade().getId(), child.getId());
    }

    @Override
    public Map<String, Double> getAvgMarkByGradeStudent(Long childId) {
        Student child = getChild(childId);
        return averageMarkService.getAvgMarkByGradeStudent(child.getGrade().getId(), child.getId());
    }

    @Override
    public Map<String, Double> getAttendByQuarterGradeStudent(Integer quarter, Long childId) {
        Student child = getChild(childId);
        return attendCountService.getAttendByQuarterGradeStudent(quarter, child.getGrade().getId(), child.getId());
    }

    @Override
    public Map<String, Double> getAttendBySubjectGradeStudent(Long subjectId, Long childId) {
        Student child = getChild(childId);
        return attendCountService.getAttendBySubjectGradeStudent(subjectId, child.getGrade().getId(), child.getId());
    }

    @Override
    public Map<String, Double> getAttendByGradeStudent(Long childId) {
        Student child = getChild(childId);
        return attendCountService.getAttendByGradeStudent(child.getGrade().getId(), child.getId());
    }

    @Override
    public List<ScheduleDto> getStudentSchedule(Long childId) {
        Student child = getChild(childId);
        return scheduleService.getAllScheduleByStudent(child.getId());
    }

    @Override
    public List<HomeworkDto> getAllHomework(Long childId) {
        Student child = getChild(childId);
        return homeworkService.getHwByStudent(child.getId());
    }

    @Override
    public List<HomeworkToDoDto> getAllUndoneHomework(Long childId) {
        Student child = getChild(childId);
        return homeworkToDoMapping.entityToDtoList(lessonService.getUndoneHwByStudent(getAllHomework(childId), child.getId()));
    }

    @Override
    public List<HomeworkDto> getAllHomeworkSubject(Long subjectId, Long childId) {
        Student child = getChild(childId);
        return homeworkService.getHwByStudentSubject(child.getId(), subjectId);
    }

    @Override
    public List<HomeworkToDoDto> getAllUndoneHomework(Long subjectId, Long childId) {
        Student child = getChild(childId);
        return homeworkToDoMapping.entityToDtoList(lessonService.getUndoneHwByStudent(getAllHomework(childId), child.getId(), subjectId));
    }

    @Override
    public List<ReviewDto> getStudentReview(Long childId) {
        Student child = getChild(childId);
        return reviewService.getReviewByStudentId(child.getId());
    }

    @Override
    public void leaveSchool(Long childId) {
        Student child = getChild(childId);
        userService.deleteUser(child.getUser().getId());
    }
}
