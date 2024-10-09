package org.example.school_project.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.school_project.dto.*;
import org.example.school_project.service.ClassTeacherService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/class-teacher")
@AllArgsConstructor
@Slf4j
public class ClassTeacherController {
    private final ClassTeacherService classTeacherService;

    @PostMapping(value = "/create-assignment")
    public ResponseEntity<Response> createAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            classTeacherService.createAssignment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not saved" + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-assignment")
    public ResponseEntity<Response> updateAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto createAssignment = classTeacherService.updateAssignment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", createAssignment));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/mark-done/{id}")
    public ResponseEntity<Response> markAsDone(@PathVariable Long id) {
        try {
            AssignmentDto updateAssignment = classTeacherService.markAsDone(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", updateAssignment));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-assignment")
    public ResponseEntity<Response> getAllAssignment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all assignment", classTeacherService.getAllAssignment()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-undone-assignment")
    public ResponseEntity<Response> getAllUndoneAssignment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone assignment", classTeacherService.getAllUndoneAssignment()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-done-assignment-from")
    public ResponseEntity<Response> getAllDoneAssignmentFrom() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone assignment", classTeacherService.getAllUndoneAssignment()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }


    @PutMapping(value = "/choose-grade-represent/{id}")
    public ResponseEntity<Response> updateGrade(@PathVariable Long id) {
        try {
            StudentDto studentDto = classTeacherService.chooseClassRepresentative(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", studentDto));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-class-student")
    public ResponseEntity<Response> studentsFromClass() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all class student", classTeacherService.getAllUndoneAssignment()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-review-student/{id}")
    public ResponseEntity<Response> getReviewByStudentId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all class student", classTeacherService.getReviewByStudentId(id)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-review-author/{id}")
    public ResponseEntity<Response> getReviewByAuthorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all class student", classTeacherService.getReviewByAuthorId(id)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-review-class-represent")
    public ResponseEntity<Response> getReviewByClassRepresent() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all class student", classTeacherService.getReviewByClassRepresent()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-review")
    public ResponseEntity<Response> createGrade(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = classTeacherService.createReview(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved" + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/update-review")
    public ResponseEntity<Response> updateReview(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = classTeacherService.updateReview(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully updated", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved" + exception.getMessage(), null));
        }
    }
}
