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
import org.example.school_project.service.UserRoleService;

@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor
@Tag(name = "User Management", description = "Operations related to user.")
public class UserController {
    private final UserRoleService userRoleService;

    @Operation(
            summary = "Create a message",
            description = "Creates a new message and sends it."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully sent Message", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Message is not saved", content = @Content)
    })
    @PostMapping(value = "/create-message")
    public ResponseEntity<Response> createMessage(@RequestBody MessageDtoRequest request) {
        try {
            MessageDto messageDto = userRoleService.createMessage(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully sent Message.", messageDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Message is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get message by ID",
            description = "Retrieves a message using its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message successfully retrieved", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Message not found", content = @Content)
    })
    @GetMapping(value = "/get-message-by-id/{id}")
    public ResponseEntity<Response> getMessageById(@PathVariable Long id) {
        try {
            MessageDto messageDto = userRoleService.getMessageById(id);
            return ResponseEntity.ok(new Response("Message successfully updated.", messageDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Message is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Mark message as unread",
            description = "Marks a message as unread."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message successfully updated", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Message not found", content = @Content)
    })
    @PutMapping(value = "/mark-as-read-message/{id}")
    public ResponseEntity<Response> markAsUnread(@PathVariable Long id) {
        try {
            MessageDto messageDto = userRoleService.markAsUnread(id);
            return ResponseEntity.ok(new Response("Message successfully updated.", messageDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Message is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Delete a message",
            description = "Deletes a message by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted Message successfully", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Failed to delete message", content = @Content)
    })
    @DeleteMapping(value = "/delete-message/{id}")
    public ResponseEntity<Response> deleteMessage(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Deleted Message successfully.", userRoleService.deleteMessage(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Restore a deleted message",
            description = "Restores a deleted message by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restored Message successfully", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Failed to restore message", content = @Content)
    })
    @PutMapping(value = "/restore-message/{id}")
    public ResponseEntity<Response> restoreAssignment(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Restored Message successfully.", userRoleService.restoreMessage(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all messages",
            description = "Retrieves all messages."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all Messages", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find messages", content = @Content)
    })
    @GetMapping(value = "/get-all-message")
    public ResponseEntity<Response> getAllMessages() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Messages.", userRoleService.getAllMessages()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all receiver messages",
            description = "Retrieves all messages received."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all receiver Messages", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find receiver messages", content = @Content)
    })
    @GetMapping(value = "/get-all-receiver-messages")
    public ResponseEntity<Response> getAllReceiverMessages() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all receive Messages.", userRoleService.getAllReceiverMessages()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all author messages",
            description = "Retrieves all messages authored."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all author Messages", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find author messages", content = @Content)
    })
    @GetMapping(value = "/get-all-author-messages")
    public ResponseEntity<Response> getAllAuthorMessages() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all author Messages.", userRoleService.getAllAuthorMessages()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all unread receiver messages",
            description = "Retrieves all unread messages received."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all receive unread Messages", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find unread receiver messages", content = @Content)
    })
    @GetMapping(value = "/get-all-receiver-unread-messages")
    public ResponseEntity<Response> getAllUnreadReceiverMessages() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all receive unread Messages.", userRoleService.getAllUnreadReceiverMessages()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all unread author messages",
            description = "Retrieves all unread messages authored."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all author unread Messages", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find unread author messages", content = @Content)
    })
    @GetMapping(value = "/get-all-author-unread-messages")
    public ResponseEntity<Response> getAllUnreadAuthorMessages() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all author unread Messages.", userRoleService.getAllUnreadAuthorMessages()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get filtered announcements",
            description = "Retrieves filtered announcements."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got filtered Announcements", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find announcements", content = @Content)
    })
    @GetMapping(value = "/get-announcements")
    public ResponseEntity<Response> getFilteredAnnouncement() {
        try {
            return ResponseEntity.ok(new Response("Successfully got filtered Announcements.", userRoleService.getFilteredAnnouncement()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all active charters",
            description = "Retrieves all active charters."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all active Charter", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find active charters", content = @Content)
    })
    @GetMapping(value = "/get-charter")
    public ResponseEntity<Response> getAllActiveCharter() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Charter.", userRoleService.getAllActiveCharter()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
}
