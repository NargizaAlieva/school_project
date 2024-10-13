package org.example.school_project.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.example.school_project.dto.*;
import org.example.school_project.service.StudentRoleService;

@RestController
@RequestMapping(value = "/student")
@AllArgsConstructor
public class StudentController {
    private final StudentRoleService studentRoleService;
    @GetMapping("/get-all-mark")
    public ResponseEntity<Response> getAllMark() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Students Marks.", studentRoleService.getAllMark()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-mark-quarter/{quarter}")
    public ResponseEntity<Response> getAllMarkBySubjectQuarter(@PathVariable Integer quarter) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks. ", studentRoleService.getAvgMarkByGradeStudentQuarter(quarter)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-mark-subject/{subjectId}")
    public ResponseEntity<Response> getAllMarkBySubjectQuarter(@PathVariable Long subjectId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks. ", studentRoleService.getAvgMarkBySubjectGradeStudent(subjectId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-mark-for-year")
    public ResponseEntity<Response> getAvgMarkByGradeStudent() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks ", studentRoleService.getAvgMarkByGradeStudent()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-attendance-quarter/{quarter}")
    public ResponseEntity<Response> getAttendByQuarterGradeStudent(@PathVariable Integer quarter) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendance.", studentRoleService.getAttendByQuarterGradeStudent(quarter)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Attendance not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-attendance-subject/{subjectId}")
    public ResponseEntity<Response> getAttendBySubjectGradeStudent(@PathVariable Long subjectId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendance.", studentRoleService.getAttendBySubjectGradeStudent(subjectId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Attendance not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-attendance-for-year")
    public ResponseEntity<Response> getAttendByGradeStudent() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendance.", studentRoleService.getAttendByGradeStudent()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Attendance not found. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/send-hw")
    public ResponseEntity<Response> sendHomework(@RequestBody HomeworkDtoRequest request) {
        try {
            HomeworkDto homeworkDto = studentRoleService.sendHomework(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully sent Homework.", homeworkDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Homework is not saved. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-hw")
    public ResponseEntity<Response> getAllHomework() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Homeworks.", studentRoleService.getAllHomework()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Homework is not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-undone-hw")
    public ResponseEntity<Response> getAllUndoneHomework() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone Homeworks.", studentRoleService.getAllUndoneHomework()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Homework is not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-hw-subject/{subjectId}")
    public ResponseEntity<Response> getAllHomeworkSubject(@PathVariable Long subjectId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Homeworks by subject.", studentRoleService.getAllHomeworkSubject(subjectId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Homework is not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-undone-hw-subject/{subjectId}")
    public ResponseEntity<Response> getAllUndoneHomework(@PathVariable Long subjectId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone Homeworks by subject", studentRoleService.getAllUndoneHomework(subjectId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Homework is not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-student-schedule")
    public ResponseEntity<Response> getStudentSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Schedules.", studentRoleService.getStudentSchedule()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Schedules is not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-lesson-grade")
    public ResponseEntity<Response> getAllLessonByGrade() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Lessons.", studentRoleService.getAllLessonByGrade()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Lessons topic is not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-lesson-topic")
    public ResponseEntity<Response> getLessonsTopics() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Lessons topic.", studentRoleService.getLessonsTopics()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Lessons topic is not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-lesson-topic/{subjectId}")
    public ResponseEntity<Response> getLessonsTopics(@PathVariable Long subjectId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Lesson topics.", studentRoleService.getLessonsTopicsBySubject(subjectId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Lesson topics is not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-student-subject")
    public ResponseEntity<Response> getSubjectList() {
        try {
            return ResponseEntity.ok(new Response("Successfully got student Subjects", studentRoleService.getStudentSubject()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Subjects is not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-classmates")
    public ResponseEntity<Response> getClassmates() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Classmates.", studentRoleService.getClassmates()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Classmates is not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-duty-list")
    public ResponseEntity<Response> getStudentDutyList() {
        try {
            return ResponseEntity.ok(new Response("Successfully got student Duty List.", studentRoleService.getStudentDutyList()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Duty list is not found. " + exception.getMessage(), null));
        }
    }
}
