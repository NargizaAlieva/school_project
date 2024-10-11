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
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Mark as done successfully", updateAssignment));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-assignment")
    public ResponseEntity<Response> getAllAssignment() {
        try {
            secretaryService.getAllAssignment();
            return ResponseEntity.ok(new Response("Successfully got all Assignment", secretaryService.getAllAssignment()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-undone-assignment")
    public ResponseEntity<Response> getAllUndoneAssignment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone Assignment", secretaryService.getAllUndoneAssignment()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-message/{id}")
    public ResponseEntity<Response> getMessageById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Message with id", secretaryService.getMessageById(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-admission-message")
    public ResponseEntity<Response> getAllAdmissionApplication() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Admission message", secretaryService.getAllAdmissionApplication()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-unread-admission-message")
    public ResponseEntity<Response> getAllUnreadAdmissionApplication() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all unread Admission message", secretaryService.getAllUnreadAdmissionApplication()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-appeal-message")
    public ResponseEntity<Response> getAllAppeal() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Appeal message", secretaryService.getAllAppeal()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }


    @GetMapping("/get-all-unread-appeal-message")
    public ResponseEntity<Response> getAllUnreadAppeal() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all unread Appeal message", secretaryService.getAllUnreadAppeal()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-charter")
    public ResponseEntity<Response> createCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto charterDto = secretaryService.createCharter(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully Charter created", charterDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-charter")
    public ResponseEntity<Response> updateCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto createCharter = secretaryService.updateCharter(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated Charter successfully", createCharter));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @DeleteMapping(value = "/delete-charter/{id}")
    public ResponseEntity<Response> deleteCharter(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Deleted Charter successfully", secretaryService.deleteCharter(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/restore-charter/{id}")
    public ResponseEntity<Response> restoreCharter(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Restored Charter successfully", secretaryService.restoreCharter(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-self-charter")
    public ResponseEntity<Response> getAllCharterByAuthor() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all self Charter", secretaryService.getAllCharterByAuthor()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

}
