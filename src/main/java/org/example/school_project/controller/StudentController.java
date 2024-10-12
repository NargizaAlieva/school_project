package org.example.school_project.controller;


import lombok.AllArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.service.StudentRoleService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/student")
@AllArgsConstructor
public class StudentController {
    private final StudentRoleService studentRoleService;
    @GetMapping("/get-all-mark")
    public ResponseEntity<Response> getAllMark() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Students Marks", studentRoleService.getAllMark()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-mark-quarter/{quarter}")
    public ResponseEntity<Response> getAllMarkBySubjectQuarter(@PathVariable Integer quarter) {
        try {
            studentRoleService.getAvgMarkByGradeStudentQuarter(quarter);
            return ResponseEntity.ok(new Response("Successfully got Marks ", studentRoleService.getAvgMarkByGradeStudentQuarter(quarter)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-mark-subject/{subjectId}")
    public ResponseEntity<Response> getAllMarkBySubjectQuarter(@PathVariable Long subjectId) {
        try {
            studentRoleService.getAvgMarkBySubjectGradeStudent(subjectId);
            return ResponseEntity.ok(new Response("Successfully got Marks ", studentRoleService.getAvgMarkBySubjectGradeStudent(subjectId)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-mark-for-year")
    public ResponseEntity<Response> getAvgMarkByGradeStudent() {
        try {
            studentRoleService.getAvgMarkByGradeStudent();
            return ResponseEntity.ok(new Response("Successfully got Marks ", studentRoleService.getAvgMarkByGradeStudent()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-attendance-quarter/{quarter}")
    public ResponseEntity<Response> getAttendByQuarterGradeStudent(@PathVariable Integer quarter) {
        try {
            studentRoleService.getAttendByQuarterGradeStudent(quarter);
            return ResponseEntity.ok(new Response("Successfully got Marks ", studentRoleService.getAttendByQuarterGradeStudent(quarter)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-attendance-subject/{subjectId}")
    public ResponseEntity<Response> getAttendBySubjectGradeStudent(@PathVariable Long subjectId) {
        try {
            studentRoleService.getAttendBySubjectGradeStudent(subjectId);
            return ResponseEntity.ok(new Response("Successfully got Marks ", studentRoleService.getAttendBySubjectGradeStudent(subjectId)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-attendance-for-year")
    public ResponseEntity<Response> getAttendByGradeStudent() {
        try {
            studentRoleService.getAttendByGradeStudent();
            return ResponseEntity.ok(new Response("Successfully got Marks ", studentRoleService.getAttendByGradeStudent()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/send-hw")
    public ResponseEntity<Response> sendHomework(@RequestBody HomeworkDtoRequest request) {
        try {
            HomeworkDto homeworkDto = studentRoleService.sendHomework(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created ", homeworkDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved " + exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-hw")
    public ResponseEntity<Response> getAllHomework() {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", studentRoleService.getAllHomework()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-undone-hw")
    public ResponseEntity<Response> getAllUndoneHomework() {
        try {
            studentRoleService.getAllUndoneHomework();
            return ResponseEntity.ok(new Response("Successfully got schedules ", studentRoleService.getAllUndoneHomework()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-hw-subject/{id}")
    public ResponseEntity<Response> getAllHomeworkSubject(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", studentRoleService.getAllHomeworkSubject(id)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-undone-hw-subject/{id}")
    public ResponseEntity<Response> getAllUndoneHomework(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", studentRoleService.getAllUndoneHomework(id)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-student-schedule")
    public ResponseEntity<Response> getStudentSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", studentRoleService.getStudentSchedule()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-lesson-grade")
    public ResponseEntity<Response> getAllLessonByGrade() {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", studentRoleService.getAllLessonByGrade()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-lesson-topic")
    public ResponseEntity<Response> getLessonsTopics() {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", studentRoleService.getLessonsTopics()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-lesson-topic/{subjectId}")
    public ResponseEntity<Response> getLessonsTopics(@PathVariable Long subjectId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", studentRoleService.getLessonsTopicsBySubject(subjectId)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-student-subject")
    public ResponseEntity<Response> getSubjectList() {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", studentRoleService.getStudentSubject()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-classmates")
    public ResponseEntity<Response> getClassmates() {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", studentRoleService.getClassmates()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
}
