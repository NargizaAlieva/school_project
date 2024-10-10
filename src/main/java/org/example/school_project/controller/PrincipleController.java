package org.example.school_project.controller;

import lombok.AllArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.entity.*;
import org.example.school_project.service.PrincipleService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/principle")
@AllArgsConstructor
public class PrincipleController {
    private PrincipleService principleService;

    @GetMapping("/get-schedule/{id}")
    public ResponseEntity<Response> getScheduleById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Schedule with id", principleService.getScheduleById(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @PutMapping("/approve-schedule/{id}")
    public ResponseEntity<Response> approveSchedule(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully approved Schedule with id", principleService.approveSchedule(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @PutMapping("/disapprove-schedule/{id}")
    public ResponseEntity<Response> disapproveSchedule(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully disapproved Schedule with id", principleService.disapproveSchedule(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @DeleteMapping("/delete-schedule/{id}")
    public ResponseEntity<Response> deleteSchedule(@PathVariable Long id) {
        try {
            principleService.deleteSchedule(id);
            return ResponseEntity.ok(new Response("Successfully deleted Schedule with id", principleService.deleteSchedule(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @PutMapping("/restore-schedule/{id}")
    public ResponseEntity<Response> restoreSchedule(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully restored Schedule with id", principleService.restoreSchedule(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-schedule")
    public ResponseEntity<Response> getAllSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Schedules", principleService.getAllSchedule()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-active-schedule")
    public ResponseEntity<Response> getAllActiveSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Schedules", principleService.getAllActiveSchedule()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-active-schedule-by-year/{year}")
    public ResponseEntity<Response> getAllActiveScheduleByYear(@PathVariable String year) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Schedules by " + year, principleService.getAllActiveScheduleByYear(year)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-unapproved-schedule")
    public ResponseEntity<Response> getAllUnapprovedSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all unapproved Schedules", principleService.getAllUnapprovedSchedule()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @PostMapping(value = "/create-schedule")
    public ResponseEntity<Response> createSchedule(@RequestBody ScheduleDtoRequest request) {
        try {
            ScheduleDto scheduleDto = principleService.createSchedule(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Schedule", scheduleDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Schedule is not saved. " + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/update-schedule")
    public ResponseEntity<Response> updateSchedule(@RequestBody ScheduleDtoRequest request) {
        try {
            ScheduleDto scheduleDto = principleService.updateSchedule(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Updated Schedule successfully", scheduleDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Schedule is not updated. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/hire-employee")
    public ResponseEntity<Response> createEmployee(@RequestBody EmployeeDroRequest request) {
        try {
            principleService.hireEmployee(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added Employee", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-employee")
    public ResponseEntity<Response> updateEmployee(@RequestBody EmployeeDroRequest request) {
        try {
            EmployeeDto createEmployee = principleService.updateEmployee(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Updated Employee successfully", createEmployee));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not updated. " + exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-employee")
    public ResponseEntity<Response> getAllEmployee() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all Employee", principleService.getAllEmployee()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response( exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-active-employee")
    public ResponseEntity<Response> getAllActiveEmployee() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all active Employee", principleService.getAllActiveSubject()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response( exception.getMessage(), null));
        }
    }

    @DeleteMapping(value = "/fire-employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        try {
            principleService.fireEmployee(id);
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping(value = "/create-subject")
    public ResponseEntity<Response> createSubject(@RequestBody SubjectDtoRequest request) {
        try {
            principleService.addSubject(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added Subject", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Subject is not saved" + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/update-subject")
    public ResponseEntity<Response> updateSubject(@RequestBody SubjectDtoRequest request) {
        try {
            SubjectDto createSubject = principleService.updateSubject(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Updated Subject successfully", createSubject));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Subject is not updated." + exception.getMessage(), null));
        }
    }
    @DeleteMapping(value = "/delete-subject/{id}")
    public ResponseEntity<Response> deleteSubject(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Deleted Subject successfully", principleService.deleteSubject(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/restore-subject/{id}")
    public ResponseEntity<Response> restoreSubject(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Restored Subject successfully", principleService.restoreSubject(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-subject")
    public ResponseEntity<Response> getAllSubject() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all Subject", principleService.getAllSubject()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping(value = "/get-all-active-subject")
    public ResponseEntity<Response> getAllActiveSubject() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all active Subject", principleService.getAllActiveSubject()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-charter")
    public ResponseEntity<Response> createCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto charterDto = principleService.createCharter(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully Charter created", charterDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-charter")
    public ResponseEntity<Response> updateCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto createCharter = principleService.updateCharter(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated Charter successfully", createCharter));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @DeleteMapping(value = "/delete-charter/{id}")
    public ResponseEntity<Response> deleteCharter(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Deleted Charter successfully", principleService.deleteCharter(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/restore-charter/{id}")
    public ResponseEntity<Response> restoreCharter(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Restored Charter successfully", principleService.restoreCharter(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-charter")
    public ResponseEntity<Response> getAllCharter() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all Charter", principleService.getAllCharter()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping(value = "/get-all-self-charter")
    public ResponseEntity<Response> getAllCharterByAuthor() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all self Charter", principleService.getAllCharterByAuthor()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }


    @PostMapping(value = "/create-assignment")
    public ResponseEntity<Response> createAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto assignmentDto = principleService.createAssignment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added Assignment", assignmentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Assignment is not saved" + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-assignment")
    public ResponseEntity<Response> updateAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto createAssignment = principleService.updateAssignment(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Assignment successfully updated", createAssignment));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Assignment is not updated. " + exception.getMessage(), null));
        }
    }
    @DeleteMapping(value = "/delete-assignment/{id}")
    public ResponseEntity<Response> deleteAssignment(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Deleted Assignment successfully", principleService.deleteAssignment(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/restore-assignment/{id}")
    public ResponseEntity<Response> restoreAssignment(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Restored Assignment successfully", principleService.restoreAssignment(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-assignment")
    public ResponseEntity<Response> getAllAssignmentByAuthor() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all Assignment", principleService.getAllAssignmentByAuthor()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-undone-assignment")
    public ResponseEntity<Response> getAllUndoneAssigment() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all undone Assignment", principleService.getAllUndoneAssigment()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
}
