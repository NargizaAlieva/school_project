package org.example.school_project.controller;

import lombok.AllArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.service.SecretaryService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/secretary")
@AllArgsConstructor
public class SecretaryController {
    private final SecretaryService secretaryService;

    @PutMapping(value = "/mark-done/{id}")
    public ResponseEntity<Response> markAsDone(@PathVariable Long id) {
        try {
            AssignmentDto updateAssignment = secretaryService.markAsDone(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", updateAssignment));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-assignment")
    public ResponseEntity<Response> getAllAssignment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all assignment", secretaryService.getAllAssignment()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-undone-assignment")
    public ResponseEntity<Response> getAllUndoneAssignment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone assignment", secretaryService.getAllUndoneAssignment()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-message/{id}")
    public ResponseEntity<Response> getMessageById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got message with id", secretaryService.getMessageById(id)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-admission-message")
    public ResponseEntity<Response> getAllAdmissionApplication() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all admission message", secretaryService.getAllAdmissionApplication()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-unread-admission-message")
    public ResponseEntity<Response> getAllUnreadAdmissionApplication() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all admission message", secretaryService.getAllUnreadAdmissionApplication()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-appeal-message")
    public ResponseEntity<Response> getAllAppeal() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all admission message", secretaryService.getAllAppeal()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }


    @GetMapping("/get-all-unread-appeal-message")
    public ResponseEntity<Response> getAllUnreadAppeal() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all admission message", secretaryService.getAllUnreadAppeal()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

}
