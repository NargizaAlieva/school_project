package org.example.school_project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.example.school_project.dto.*;
import org.example.school_project.service.ClassRepresentService;

@RestController
@RequestMapping(value = "/class-represent")
@AllArgsConstructor
public class ClassRepresentController {
    private final ClassRepresentService classRepresentService;

    @Operation(
            summary = "Create a review",
            description = "Creates a new review and returns the created review object."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review successfully created", content = @Content(schema = @Schema(implementation = ReviewDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
    })
    @PostMapping(value = "/create-review")
    public ResponseEntity<Response> createReview(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = classRepresentService.createReview(request);
            return ResponseEntity.ok(new Response("Successfully created Review.", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Review is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update a review",
            description = "Updates an existing review and returns the updated review object."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review successfully updated", content = @Content(schema = @Schema(implementation = ReviewDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
    })
    @PutMapping(value = "/update-review")
    public ResponseEntity<Response> updateReview(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = classRepresentService.updateReview(request);
            return ResponseEntity.ok(new Response("Successfully updated Review.", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Review is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Delete a review",
            description = "Deletes a review by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review successfully deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Review not found", content = @Content)
    })
    @DeleteMapping(value = "/delete-review/{reviewId}")
    public ResponseEntity<Response> deleteReview(@PathVariable Long reviewId) {
        try {
            return ResponseEntity.ok(new Response("successfully Review deleted. ", classRepresentService.deleteReview(reviewId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Restore a review",
            description = "Restores a deleted review by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review successfully restored", content = @Content(schema = @Schema(implementation = ReviewDto.class))),
            @ApiResponse(responseCode = "404", description = "Review not found", content = @Content)
    })
    @PutMapping(value = "/restore-review/{reviewId}")
    public ResponseEntity<Response> restoreReview(@PathVariable Long reviewId) {
        try {
            return ResponseEntity.ok(new Response("Restored Review successfully", classRepresentService.restoreReview(reviewId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Mark assignment as done",
            description = "Marks an assignment as completed by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assignment successfully marked as done", content = @Content(schema = @Schema(implementation = AssignmentDto.class))),
            @ApiResponse(responseCode = "404", description = "Assignment not found", content = @Content)
    })
    @PutMapping(value = "/mark-done/{assignmentId}")
    public ResponseEntity<Response> markAsDone(@PathVariable Long assignmentId) {
        try {
            AssignmentDto updateAssignment = classRepresentService.markAsDone(assignmentId);
            return ResponseEntity.ok(new Response("Marked as done successfully.", updateAssignment));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to mark as done. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all assignments",
            description = "Retrieves all assignments."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all assignments", content = @Content(schema = @Schema(implementation = AssignmentDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/get-all-assignment")
    public ResponseEntity<Response> getAllAssignment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Assignment", classRepresentService.getAllAssignment()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all undone assignments",
            description = "Retrieves all assignments that are not yet completed."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all undone assignments", content = @Content(schema = @Schema(implementation = AssignmentDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/get-all-undone-assignment")
    public ResponseEntity<Response> getAllUndoneAssignment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone Assignment", classRepresentService.getAllUndoneAssignment()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Send message to home grade students",
            description = "Sends a message to students of a specified grade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message sent successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Failed to send message", content = @Content)
    })
    @PostMapping(value = "/send-message-to-home-grade-students")
    public ResponseEntity<Response> sendMessageForGradeStudents(@RequestBody MessageDtoRequest request) {
        try {
            classRepresentService.sendMessageForGradeStudents(request);
            return ResponseEntity.ok(new Response("Sent successfully", null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Failed to send. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Create a duty list",
            description = "Creates a new duty list with the provided details."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created duty list", content = @Content(schema = @Schema(implementation = DutyListDto.class))),
            @ApiResponse(responseCode = "400", description = "Failed to save duty list", content = @Content)
    })
    @PostMapping(value = "/create-duty-list")
    public ResponseEntity<Response> createDutyList(@RequestBody DutyListDtoRequest request) {
        try {
            DutyListDto dutyListDto = classRepresentService.createDutyList(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Duty List.", dutyListDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Duty List is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update a duty list",
            description = "Updates an existing duty list with the provided details."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated duty list", content = @Content(schema = @Schema(implementation = DutyListDto.class))),
            @ApiResponse(responseCode = "400", description = "Failed to update duty list", content = @Content)
    })
    @PutMapping(value = "/update-duty-list")
    public ResponseEntity<Response> updateDutyList(@RequestBody DutyListDtoRequest request) {
        try {
            DutyListDto dutyListDto = classRepresentService.updateDutyList(request);
            return ResponseEntity.ok(new Response("Successfully updated Duty List.", dutyListDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Duty List is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all duty lists by grade",
            description = "Retrieves all duty lists grouped by grade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all grade duty lists", content = @Content(schema = @Schema(implementation = DutyListDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/get-all-duty-list-grade")
    public ResponseEntity<Response> getAllDutyListByGrade() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all grade Duty List", classRepresentService.getAllDutyListByGrade()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
}
