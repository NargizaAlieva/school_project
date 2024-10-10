package org.example.school_project.controller;

import lombok.AllArgsConstructor;
import org.example.school_project.dto.AssignmentDto;
import org.example.school_project.dto.Response;
import org.example.school_project.dto.ReviewDto;
import org.example.school_project.dto.ReviewDtoRequest;
import org.example.school_project.service.ClassRepresentService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/class-represent")
@AllArgsConstructor
public class ClassRepresentController {
    private final ClassRepresentService classRepresentService;

    @PutMapping(value = "/mark-done/{id}")
    public ResponseEntity<Response> markAsDone(@PathVariable Long id) {
        try {
            AssignmentDto updateAssignment = classRepresentService.markAsDone(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", updateAssignment));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-assignment")
    public ResponseEntity<Response> getAllAssignment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all assignment", classRepresentService.getAllAssignment()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-undone-assignment")
    public ResponseEntity<Response> getAllUndoneAssignment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone assignment", classRepresentService.getAllUndoneAssignment()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    //Working
    @PostMapping(value = "/create-review")
    public ResponseEntity<Response> createReview(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = classRepresentService.createReview(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created ", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved " + exception.getMessage(), null));
        }
    }
    //Working
    @PutMapping(value = "/update-review")
    public ResponseEntity<Response> updateReview(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = classRepresentService.createReview(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created ", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved " + exception.getMessage(), null));
        }
    }
}
