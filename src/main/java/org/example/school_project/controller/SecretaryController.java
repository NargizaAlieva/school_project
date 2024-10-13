package org.example.school_project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.example.school_project.dto.*;
import org.example.school_project.service.SecretaryService;

@RestController
@RequestMapping(value = "/secretary")
@AllArgsConstructor
@Tag(name = "Secretary Management", description = "Operations related to secretary role.")
public class SecretaryController {
    private final SecretaryService secretaryService;

    @Operation(
            summary = "Mark assignment as done",
            description = "Marks the specified assignment as done."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marked as done successfully", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Assignment not found", content = @Content)
    })
    @PutMapping(value = "/mark-done/{assignmentId}")
    public ResponseEntity<Response> markAsDone(@PathVariable Long assignmentId) {
        try {
            AssignmentDto updateAssignment = secretaryService.markAsDone(assignmentId);
            return ResponseEntity.ok(new Response("Mark as done successfully", updateAssignment));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to mark as done. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all assignments",
            description = "Retrieves all assignments."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all Assignments", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Assignments not found", content = @Content)
    })
    @GetMapping("/get-all-assignment")
    public ResponseEntity<Response> getAllAssignment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Assignment", secretaryService.getAllAssignment()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all undone assignments",
            description = "Retrieves all undone assignments."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all undone Assignments", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Undone Assignments not found", content = @Content)
    })
    @GetMapping("/get-all-undone-assignment")
    public ResponseEntity<Response> getAllUndoneAssignment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone Assignment", secretaryService.getAllUndoneAssignment()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get message by ID",
            description = "Retrieves a specific message by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Message", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Message not found", content = @Content)
    })
    @GetMapping("/get-message/{messageId}")
    public ResponseEntity<Response> getMessageById(@PathVariable Long messageId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Message with id", secretaryService.getMessageById(messageId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all admission messages",
            description = "Retrieves all admission application messages."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all Admission messages", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Admission messages not found", content = @Content)
    })
    @GetMapping("/get-all-admission-message")
    public ResponseEntity<Response> getAllAdmissionApplication() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Admission message", secretaryService.getAllAdmissionApplication()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all unread admission messages",
            description = "Retrieves all unread admission application messages."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all unread Admission messages", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Unread Admission messages not found", content = @Content)
    })
    @GetMapping("/get-all-unread-admission-message")
    public ResponseEntity<Response> getAllUnreadAdmissionApplication() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all unread Admission message", secretaryService.getAllUnreadAdmissionApplication()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all appeal messages",
            description = "Retrieves all appeal messages."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all Appeal messages", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Appeal messages not found", content = @Content)
    })
    @GetMapping("/get-all-appeal-message")
    public ResponseEntity<Response> getAllAppeal() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Appeal message", secretaryService.getAllAppeal()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all unread appeal messages",
            description = "Retrieves all unread appeal messages."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all unread Appeal messages", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Unread Appeal messages not found", content = @Content)
    })
    @GetMapping("/get-all-unread-appeal-message")
    public ResponseEntity<Response> getAllUnreadAppeal() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all unread Appeal message", secretaryService.getAllUnreadAppeal()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Create a charter",
            description = "Creates a new charter based on the provided details."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created Charter", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Charter not saved", content = @Content)
    })
    @PostMapping(value = "/create-charter")
    public ResponseEntity<Response> createCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto charterDto = secretaryService.createCharter(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully Charter created.", charterDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update a charter",
            description = "Updates an existing charter based on the provided details."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated Charter", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Charter not updated", content = @Content)
    })
    @PutMapping(value = "/update-charter")
    public ResponseEntity<Response> updateCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto createCharter = secretaryService.updateCharter(request);
            return ResponseEntity.ok(new Response("Successfully Charter updated.", createCharter));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Delete a charter",
            description = "Deletes the charter with the specified ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted Charter successfully", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Charter not found", content = @Content)
    })
    @DeleteMapping(value = "/delete-charter/{charterId}")
    public ResponseEntity<Response> deleteCharter(@PathVariable Long charterId) {
        try {
            return ResponseEntity.ok(new Response("Deleted Charter successfully", secretaryService.deleteCharter(charterId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Restore a charter",
            description = "Restores the charter with the specified ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restored Charter successfully", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Charter not found", content = @Content)
    })
    @PutMapping(value = "/restore-charter/{charterId}")
    public ResponseEntity<Response> restoreCharter(@PathVariable Long charterId) {
        try {
            return ResponseEntity.ok(new Response("Restored Charter successfully", secretaryService.restoreCharter(charterId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all self charters",
            description = "Retrieves all charters created by the current user."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all self Charters", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Charters not found", content = @Content)
    })
    @GetMapping(value = "/get-all-self-charter")
    public ResponseEntity<Response> getAllCharterByAuthor() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all self Charter", secretaryService.getAllCharterByAuthor()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Create an announcement",
            description = "Creates a new announcement with the provided details."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created Announcement", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Announcement not saved", content = @Content)
    })
    @PostMapping(value = "/create-announcement")
    public ResponseEntity<Response> createAnnouncement(@RequestBody AnnouncementDtoRequest request) {
        try {
            AnnouncementDto announcementDto = secretaryService.createAnnouncement(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Announcement.", announcementDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Announcement is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update an announcement",
            description = "Updates an existing announcement with the provided details."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Announcement updated successfully", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Announcement not updated", content = @Content)
    })
    @PutMapping(value = "/update-announcement")
    public ResponseEntity<Response> updateAnnouncement(@RequestBody AnnouncementDtoRequest request) {
        try {
            AnnouncementDto announcementDto = secretaryService.updateAnnouncement(request);
            return ResponseEntity.ok(new Response("Announcement Schedule successfully.", announcementDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Announcement not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Delete an announcement",
            description = "Deletes the announcement with the given ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted Announcement", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Announcement not found", content = @Content)
    })
    @DeleteMapping("/delete-announcement/{announcementId}")
    public ResponseEntity<Response> deleteAnnouncement(@PathVariable Long announcementId) {
        try {
            return ResponseEntity.ok(new Response("Successfully deleted Announcement with id.", secretaryService.deleteAnnouncement(announcementId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Restore an announcement",
            description = "Restores the announcement with the given ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully restored Announcement", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Announcement not found", content = @Content)
    })
    @PutMapping("/restore-announcement/{announcementId}")
    public ResponseEntity<Response> restoreAnnouncement(@PathVariable Long announcementId) {
        try {
            return ResponseEntity.ok(new Response("Successfully restored Announcement with id.", secretaryService.restoreAnnouncement(announcementId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all active announcements",
            description = "Retrieves all active announcements."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all active Announcements", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value = "/get-all-active-announcement")
    public ResponseEntity<Response> getAllActiveAnnouncement() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Announcement.", secretaryService.getAllActiveAnnouncement()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all self announcements",
            description = "Retrieves all announcements created by the current user."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all self Announcements", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value = "/get-all-self-announcement")
    public ResponseEntity<Response> getAllSelfAnnouncement() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all self Announcement.", secretaryService.getAllSelfAnnouncement()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
}
