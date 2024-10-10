package org.example.school_project.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.school_project.dto.*;
import org.example.school_project.entity.User;
import org.example.school_project.service.UserRoleService;
import org.example.school_project.service.UserService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.example.school_project.util.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserRoleService userRoleService;

    @PostMapping(value = "/create-message")
    public ResponseEntity<Response> createMessage(@RequestBody MessageDtoRequest request) {
        try {
            MessageDto messageDto = userRoleService.createMessage(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added Message", messageDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Message is not saved" + exception.getMessage(), null));
        }
    }
    @GetMapping(value = "/get-message-by-id/{id}")
    public ResponseEntity<Response> getMessageById(@PathVariable Long id) {
        try {
            MessageDto messageDto = userRoleService.getMessageById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Message successfully updated", messageDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Message is not updated. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/mark-as-read-message/{id}")
    public ResponseEntity<Response> markAsUnread(@PathVariable Long id) {
        try {
            MessageDto messageDto = userRoleService.markAsUnread(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Message successfully updated", messageDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Message is not updated. " + exception.getMessage(), null));
        }
    }
    @DeleteMapping(value = "/delete-message/{id}")
    public ResponseEntity<Response> deleteMessage(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Deleted Message successfully", userRoleService.deleteMessage(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/restore-message/{id}")
    public ResponseEntity<Response> restoreAssignment(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Restored Message successfully", userRoleService.restoreMessage(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-message")
    public ResponseEntity<Response> getAllMessages() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all Message", userRoleService.getAllMessages()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-receiver-assignment")
    public ResponseEntity<Response> getAllReceiverMessages() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all receive Message", userRoleService.getAllReceiverMessages()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-author-assignment")
    public ResponseEntity<Response> getAllAuthorMessages() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all author Message", userRoleService.getAllAuthorMessages()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping(value = "/get-all-receiver-unread-assignment")
    public ResponseEntity<Response> getAllUnreadReceiverMessages() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all receive unread Message", userRoleService.getAllUnreadReceiverMessages()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping(value = "/get-all-author-unread-assignment")
    public ResponseEntity<Response> getAllUnreadAuthorMessages() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all author unread Message", userRoleService.getAllUnreadAuthorMessages()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
}
