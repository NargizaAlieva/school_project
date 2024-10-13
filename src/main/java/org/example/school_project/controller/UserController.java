package org.example.school_project.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.example.school_project.dto.*;
import org.example.school_project.service.UserRoleService;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController {
    private final UserRoleService userRoleService;

    @PostMapping(value = "/create-message")
    public ResponseEntity<Response> createMessage(@RequestBody MessageDtoRequest request) {
        try {
            MessageDto messageDto = userRoleService.createMessage(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully sent Message.", messageDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Message is not saved. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-message-by-id/{id}")
    public ResponseEntity<Response> getMessageById(@PathVariable Long id) {
        try {
            MessageDto messageDto = userRoleService.getMessageById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Message successfully updated.", messageDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Message is not updated. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/mark-as-read-message/{id}")
    public ResponseEntity<Response> markAsUnread(@PathVariable Long id) {
        try {
            MessageDto messageDto = userRoleService.markAsUnread(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Message successfully updated.", messageDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Message is not updated. " + exception.getMessage(), null));
        }
    }

    @DeleteMapping(value = "/delete-message/{id}")
    public ResponseEntity<Response> deleteMessage(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Deleted Message successfully.", userRoleService.deleteMessage(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/restore-message/{id}")
    public ResponseEntity<Response> restoreAssignment(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Restored Message successfully.", userRoleService.restoreMessage(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-message")
    public ResponseEntity<Response> getAllMessages() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all Messages.", userRoleService.getAllMessages()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-receiver-messages")
    public ResponseEntity<Response> getAllReceiverMessages() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all receive Messages.", userRoleService.getAllReceiverMessages()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-author-messages")
    public ResponseEntity<Response> getAllAuthorMessages() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all author Messages.", userRoleService.getAllAuthorMessages()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-receiver-unread-messages")
    public ResponseEntity<Response> getAllUnreadReceiverMessages() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all receive unread Messages.", userRoleService.getAllUnreadReceiverMessages()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-author-unread-messages")
    public ResponseEntity<Response> getAllUnreadAuthorMessages() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all author unread Messages.", userRoleService.getAllUnreadAuthorMessages()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
}
