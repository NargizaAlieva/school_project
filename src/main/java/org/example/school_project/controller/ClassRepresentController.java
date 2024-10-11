package org.example.school_project.controller;

import lombok.AllArgsConstructor;
import org.example.school_project.dto.*;
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

    @PostMapping(value = "/create-review")
    public ResponseEntity<Response> createReview(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = classRepresentService.createReview(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Review", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Review is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-review")
    public ResponseEntity<Response> updateReview(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = classRepresentService.updateReview(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully Review updated", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Review is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/mark-done/{id}")
    public ResponseEntity<Response> markAsDone(@PathVariable Long id) {
        try {
            AssignmentDto updateAssignment = classRepresentService.markAsDone(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Marked as done successfully", updateAssignment));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to mark as done " + exception.getMessage(), null));
        }
    }

    @DeleteMapping(value = "/delete-review/{id}")
    public ResponseEntity<Response> deleteReview(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Deleted Review successfully", classRepresentService.deleteReview(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/restore-review/{id}")
    public ResponseEntity<Response> restoreReview(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Restored Review successfully", classRepresentService.restoreReview(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-assignment")
    public ResponseEntity<Response> getAllAssignment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Assignment", classRepresentService.getAllAssignment()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-undone-assignment")
    public ResponseEntity<Response> getAllUndoneAssignment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone Assignment", classRepresentService.getAllUndoneAssignment()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/send-message-to-home-grade-students")
    public ResponseEntity<Response> sendMessageForGradeStudents(@RequestBody MessageDtoRequest request) {
        try {
            classRepresentService.sendMessageForGradeStudents(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Sent successfully", null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Failed to send. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-duty-list")
    public ResponseEntity<Response> createDutyList(@RequestBody DutyListDtoRequest request) {
        try {
            DutyListDto dutyListDto = classRepresentService.createDutyList(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Duty List", dutyListDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Duty List is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-duty-list")
    public ResponseEntity<Response> updateDutyList(@RequestBody DutyListDtoRequest request) {
        try {
            DutyListDto dutyListDto = classRepresentService.updateDutyList(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully Duty List updated", dutyListDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Duty List is not saved. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-duty-list-grade")
    public ResponseEntity<Response> getAllDutyListByGrade() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all grade Duty List", classRepresentService.getAllDutyListByGrade()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
}
