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

    @GetMapping("/get-all-mark-subject-quarter/{year}/{subjectId}/{quarter}")
    public ResponseEntity<Response> getAllMarkBySubjectQuarter(@PathVariable String year,
                                                               @PathVariable Long subjectId,
                                                               @PathVariable Integer quarter) {
        try {
            studentRoleService.getAllMarkByYearSubjectQuarter(year, subjectId, quarter);
            return ResponseEntity.ok(new Response("Successfully got Marks ", studentRoleService.getAllMarkByYearSubjectQuarter(year, subjectId, quarter)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-mark-subject-quarter/{year}/{subjectId}")
    public ResponseEntity<Response> getGradeByYearSubject(@PathVariable String year,
                                                               @PathVariable Long subjectId) {
        try {
            studentRoleService.getGradeByYearSubject(year, subjectId);
            return ResponseEntity.ok(new Response("Successfully got Marks", studentRoleService.getGradeByYearSubject(year, subjectId)));
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
    @GetMapping("/get-student-subject/{year}")
    public ResponseEntity<Response> getSubjectList(@PathVariable String year) {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", studentRoleService.getStudentSubject(year)));
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
