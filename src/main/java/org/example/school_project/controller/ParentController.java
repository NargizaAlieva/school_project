package org.example.school_project.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.example.school_project.dto.*;
import org.example.school_project.service.ParentRoleService;

@RestController
@RequestMapping(value = "/parent")
@AllArgsConstructor
public class ParentController {
    private final ParentRoleService parentRoleService;

    @PostMapping(value = "/create-student")
    public ResponseEntity<Response> createStudent(@RequestBody StudentDtoRequest request) {
        try {
            StudentDto studentDto = parentRoleService.createStudent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Student.", studentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Student is not saved. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-child")
    public ResponseEntity<Response> getChildList() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all children.", parentRoleService.getChildList()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Children not found. " + exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-mark-quarter/{quarter}/{childId}")
    public ResponseEntity<Response> getAllMarkBySubjectQuarter(@PathVariable Integer quarter,
                                                               @PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", parentRoleService.getAvgMarkByGradeStudentQuarter(quarter, childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-mark-subject/{subjectId}/{childId}")
    public ResponseEntity<Response> getAllMarkBySubjectQuarter(@PathVariable Long subjectId,
                                                               @PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", parentRoleService.getAvgMarkBySubjectGradeStudent(subjectId, childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-mark-for-year/{childId}")
    public ResponseEntity<Response> getAvgMarkByGradeStudent(@PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", parentRoleService.getAvgMarkByGradeStudent(childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-attendance-quarter/{quarter}/{childId}")
    public ResponseEntity<Response> getAttendByQuarterGradeStudent(@PathVariable Integer quarter,
                                                                   @PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendance.", parentRoleService.getAttendByQuarterGradeStudent(quarter, childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Attendance not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-attendance-subject/{subjectId}/{childId}")
    public ResponseEntity<Response> getAttendBySubjectGradeStudent(@PathVariable Long subjectId,
                                                                   @PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendance.", parentRoleService.getAttendBySubjectGradeStudent(subjectId, childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not Attendance. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-attendance-for-year/{childId}")
    public ResponseEntity<Response> getAttendByGradeStudent(@PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendance.", parentRoleService.getAttendByGradeStudent(childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not Attendance. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-hw/{childId}")
    public ResponseEntity<Response> getAllHomework(@PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Homeworks.", parentRoleService.getAllHomework(childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-undone-hw/{childId}")
    public ResponseEntity<Response> getAllUndoneHomework(@PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone Homeworks.", parentRoleService.getAllUndoneHomework(childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-hw-subject/{subjectId}/{childId}")
    public ResponseEntity<Response> getAllHomeworkSubject(@PathVariable Long subjectId,
                                                          @PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Homework by subject.", parentRoleService.getAllHomeworkSubject(subjectId, childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-undone-hw-subject/{subjectId}/{childId}")
    public ResponseEntity<Response> getAllUndoneHomework(@PathVariable Long subjectId,
                                                         @PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got undone Homework by subject.", parentRoleService.getAllUndoneHomework(subjectId, childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-student-schedule/{childId}")
    public ResponseEntity<Response> getStudentSchedule(@PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Schedules.", parentRoleService.getStudentSchedule(childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-review/{childId}")
    public ResponseEntity<Response> getStudentReview(@PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Reviews.", parentRoleService.getStudentReview(childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @DeleteMapping(value = "/delete-user/{childId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long childId) {
        try {
            parentRoleService.leaveSchool(childId);
            return ResponseEntity.ok("Deleted Student successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete. " + exception.getMessage());
        }
    }
}
