package org.example.school_project.controller;

import lombok.AllArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.service.ParentRoleService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/parent")
@AllArgsConstructor
public class ParentController {
    private final ParentRoleService parentRoleService;

    @PostMapping(value = "/create-student")
    public ResponseEntity<Response> createStudent(@RequestBody StudentDtoRequest request) {
        try {
            StudentDto studentDto = parentRoleService.createStudent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Student", studentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Student is not saved " + exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-mark-quarter/{quarter}/{childId}")
    public ResponseEntity<Response> getAllMarkBySubjectQuarter(@PathVariable Integer quarter,
                                                               @PathVariable Long childId) {
        try {
            parentRoleService.getAvgMarkByGradeStudentQuarter(quarter, childId);
            return ResponseEntity.ok(new Response("Successfully got Marks ", parentRoleService.getAvgMarkByGradeStudentQuarter(quarter, childId)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-mark-subject/{subjectId}/{childId}")
    public ResponseEntity<Response> getAllMarkBySubjectQuarter(@PathVariable Long subjectId,
                                                               @PathVariable Long childId) {
        try {
            parentRoleService.getAvgMarkBySubjectGradeStudent(subjectId, childId);
            return ResponseEntity.ok(new Response("Successfully got Marks ", parentRoleService.getAvgMarkBySubjectGradeStudent(subjectId, childId)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-mark-for-year/{childId}")
    public ResponseEntity<Response> getAvgMarkByGradeStudent(@PathVariable Long childId) {
        try {
            parentRoleService.getAvgMarkByGradeStudent(childId);
            return ResponseEntity.ok(new Response("Successfully got Marks ", parentRoleService.getAvgMarkByGradeStudent(childId)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-attendance-quarter/{quarter}/{childId}")
    public ResponseEntity<Response> getAttendByQuarterGradeStudent(@PathVariable Integer quarter,
                                                                   @PathVariable Long childId) {
        try {
            parentRoleService.getAttendByQuarterGradeStudent(quarter, childId);
            return ResponseEntity.ok(new Response("Successfully got Marks ", parentRoleService.getAttendByQuarterGradeStudent(quarter, childId)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-attendance-subject/{subjectId}/{childId}")
    public ResponseEntity<Response> getAttendBySubjectGradeStudent(@PathVariable Long subjectId,
                                                                   @PathVariable Long childId) {
        try {
            parentRoleService.getAttendBySubjectGradeStudent(subjectId, childId);
            return ResponseEntity.ok(new Response("Successfully got Marks ", parentRoleService.getAttendBySubjectGradeStudent(subjectId, childId)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-attendance-for-year/{childId}")
    public ResponseEntity<Response> getAttendByGradeStudent(@PathVariable Long childId) {
        try {
            parentRoleService.getAttendByGradeStudent(childId);
            return ResponseEntity.ok(new Response("Successfully got Marks ", parentRoleService.getAttendByGradeStudent(childId)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-hw/{childId}")
    public ResponseEntity<Response> getAllHomework(@PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", parentRoleService.getAllHomework(childId)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-undone-hw/{childId}")
    public ResponseEntity<Response> getAllUndoneHomework(@PathVariable Long childId) {
        try {
            parentRoleService.getAllUndoneHomework(childId);
            return ResponseEntity.ok(new Response("Successfully got schedules ", parentRoleService.getAllUndoneHomework(childId)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-hw-subject/{subjectId}/{childId}")
    public ResponseEntity<Response> getAllHomeworkSubject(@PathVariable Long subjectId,
                                                          @PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", parentRoleService.getAllHomeworkSubject(subjectId, childId)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-undone-hw-subject/{subjectId}/{childId}")
    public ResponseEntity<Response> getAllUndoneHomework(@PathVariable Long subjectId,
                                                         @PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", parentRoleService.getAllUndoneHomework(subjectId, childId)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-student-schedule/{childId}")
    public ResponseEntity<Response> getStudentSchedule(@PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", parentRoleService.getStudentSchedule(childId)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-review/{childId}")
    public ResponseEntity<Response> getStudentReview(@PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", parentRoleService.getStudentReview(childId)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @DeleteMapping(value = "/delete-user/{childId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long childId) {
        try {
            parentRoleService.leaveSchool(childId);
            return ResponseEntity.ok("Deleted User successfully");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
