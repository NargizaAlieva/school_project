package org.example.school_project.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.example.school_project.dto.*;
import org.example.school_project.service.SecretaryService;

@RestController
@RequestMapping(value = "/secretary")
@AllArgsConstructor
public class SecretaryController {
    private final SecretaryService secretaryService;

    @PutMapping(value = "/mark-done/{assignmentId}")
    public ResponseEntity<Response> markAsDone(@PathVariable Long assignmentId) {
        try {
            AssignmentDto updateAssignment = secretaryService.markAsDone(assignmentId);
            return ResponseEntity.ok(new Response("Mark as done successfully", updateAssignment));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to mark as done. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-assignment")
    public ResponseEntity<Response> getAllAssignment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Assignment", secretaryService.getAllAssignment()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-undone-assignment")
    public ResponseEntity<Response> getAllUndoneAssignment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone Assignment", secretaryService.getAllUndoneAssignment()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }
    @GetMapping("/get-message/{messageId}")
    public ResponseEntity<Response> getMessageById(@PathVariable Long messageId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Message with id", secretaryService.getMessageById(messageId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-admission-message")
    public ResponseEntity<Response> getAllAdmissionApplication() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Admission message", secretaryService.getAllAdmissionApplication()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-unread-admission-message")
    public ResponseEntity<Response> getAllUnreadAdmissionApplication() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all unread Admission message", secretaryService.getAllUnreadAdmissionApplication()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-appeal-message")
    public ResponseEntity<Response> getAllAppeal() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Appeal message", secretaryService.getAllAppeal()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }


    @GetMapping("/get-all-unread-appeal-message")
    public ResponseEntity<Response> getAllUnreadAppeal() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all unread Appeal message", secretaryService.getAllUnreadAppeal()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-charter")
    public ResponseEntity<Response> createCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto charterDto = secretaryService.createCharter(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully Charter created.", charterDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-charter")
    public ResponseEntity<Response> updateCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto createCharter = secretaryService.updateCharter(request);
            return ResponseEntity.ok(new Response("Successfully Charter updated.", createCharter));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not updated. " + exception.getMessage(), null));
        }
    }

    @DeleteMapping(value = "/delete-charter/{charterId}")
    public ResponseEntity<Response> deleteCharter(@PathVariable Long charterId) {
        try {
            return ResponseEntity.ok(new Response("Deleted Charter successfully", secretaryService.deleteCharter(charterId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/restore-charter/{charterId}")
    public ResponseEntity<Response> restoreCharter(@PathVariable Long charterId) {
        try {
            return ResponseEntity.ok(new Response("Restored Charter successfully", secretaryService.restoreCharter(charterId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-self-charter")
    public ResponseEntity<Response> getAllCharterByAuthor() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all self Charter", secretaryService.getAllCharterByAuthor()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-announcement")
    public ResponseEntity<Response> createAnnouncement(@RequestBody AnnouncementDtoRequest request) {
        try {
            AnnouncementDto announcementDto = secretaryService.createAnnouncement(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Announcement.", announcementDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Announcement is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-announcement")
    public ResponseEntity<Response> updateAnnouncement(@RequestBody AnnouncementDtoRequest request) {
        try {
            AnnouncementDto announcementDto = secretaryService.updateAnnouncement(request);
            return ResponseEntity.ok(new Response("Announcement Schedule successfully.", announcementDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Announcement not updated. " + exception.getMessage(), null));
        }
    }

    @DeleteMapping("/delete-announcement/{announcementId}")
    public ResponseEntity<Response> deleteAnnouncement(@PathVariable Long announcementId) {
        try {
            return ResponseEntity.ok(new Response("Successfully deleted Announcement with id.", secretaryService.deleteAnnouncement(announcementId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }
    @PutMapping("/restore-announcement/{announcementId}")
    public ResponseEntity<Response> restoreAnnouncement(@PathVariable Long announcementId) {
        try {
            return ResponseEntity.ok(new Response("Successfully restored Announcement with id.", secretaryService.restoreAnnouncement(announcementId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-active-announcement")
    public ResponseEntity<Response> getAllActiveAnnouncement() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Announcement.", secretaryService.getAllActiveAnnouncement()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-self-announcement")
    public ResponseEntity<Response> getAllSelfAnnouncement() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all self Announcement.", secretaryService.getAllSelfAnnouncement()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
}
