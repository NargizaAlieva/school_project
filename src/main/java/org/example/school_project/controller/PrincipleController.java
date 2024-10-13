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
import org.example.school_project.service.PrincipleService;

@RestController
@RequestMapping(value = "/principle")
@AllArgsConstructor
@Tag(name = "Principle Management", description = "Operations related to principle role.")
public class PrincipleController {
    private PrincipleService principleService;

    @Operation(
            summary = "Get schedule by ID",
            description = "Retrieves the schedule associated with the specified ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got the schedule", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Schedule not found", content = @Content)
    })
    @GetMapping("/get-schedule/{scheduleId}")
    public ResponseEntity<Response> getScheduleById(@PathVariable Long scheduleId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Schedule with id", principleService.getScheduleById(scheduleId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Approve schedule by ID",
            description = "Approves the schedule associated with the specified ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully approved the schedule", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Schedule not found", content = @Content)
    })
    @PutMapping("/approve-schedule/{scheduleId}")
    public ResponseEntity<Response> approveSchedule(@PathVariable Long scheduleId) {
        try {
            return ResponseEntity.ok(new Response("Successfully approved Schedule with id", principleService.approveSchedule(scheduleId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to approve. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Disapprove schedule by ID",
            description = "Disapproves the schedule associated with the specified ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully disapproved the schedule", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Schedule not found", content = @Content)
    })
    @PutMapping("/disapprove-schedule/{scheduleId}")
    public ResponseEntity<Response> disapproveSchedule(@PathVariable Long scheduleId) {
        try {
            return ResponseEntity.ok(new Response("Successfully disapproved Schedule with id", principleService.disapproveSchedule(scheduleId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to disapprove. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Delete schedule by ID",
            description = "Deletes the schedule associated with the specified ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the schedule", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Schedule not found", content = @Content)
    })
    @DeleteMapping("/delete-schedule/{scheduleId}")
    public ResponseEntity<Response> deleteSchedule(@PathVariable Long scheduleId) {
        try {
            return ResponseEntity.ok(new Response("Successfully deleted Schedule with id", principleService.deleteSchedule(scheduleId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Restore schedule by ID",
            description = "Restores the schedule associated with the specified ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully restored the schedule", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Schedule not found", content = @Content)
    })
    @PutMapping("/restore-schedule/{scheduleId}")
    public ResponseEntity<Response> restoreSchedule(@PathVariable Long scheduleId) {
        try {
            return ResponseEntity.ok(new Response("Successfully restored Schedule with id", principleService.restoreSchedule(scheduleId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all schedules",
            description = "Retrieves all schedules."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all schedules", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Schedules not found", content = @Content)
    })
    @GetMapping("/get-all-schedule")
    public ResponseEntity<Response> getAllSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Schedules", principleService.getAllSchedule()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all active schedules",
            description = "Retrieves all schedules that are currently active."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all active schedules", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "No active schedules found", content = @Content)
    })
    @GetMapping("/get-all-active-schedule")
    public ResponseEntity<Response> getAllActiveSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Schedules", principleService.getAllActiveSchedule()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all active schedules by year",
            description = "Retrieves all active schedules for the specified year."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all active schedules for the year", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "No active schedules found for the specified year", content = @Content)
    })
    @GetMapping("/get-all-active-schedule-by-year/{year}")
    public ResponseEntity<Response> getAllActiveScheduleByYear(@PathVariable String year) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Schedules by " + year, principleService.getAllActiveScheduleByYear(year)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all unapproved schedules",
            description = "Retrieves all schedules that have not been approved."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all unapproved schedules", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "No unapproved schedules found", content = @Content)
    })
    @GetMapping("/get-all-unapproved-schedule")
    public ResponseEntity<Response> getAllUnapprovedSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all unapproved Schedules", principleService.getAllUnapprovedSchedule()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Create a new schedule",
            description = "Creates a new schedule based on the provided data."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created Schedule", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Schedule is not saved", content = @Content)
    })
    @PostMapping(value = "/create-schedule")
    public ResponseEntity<Response> createSchedule(@RequestBody ScheduleDtoRequest request) {
        try {
            ScheduleDto scheduleDto = principleService.createSchedule(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Schedule.", scheduleDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Schedule is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update an existing schedule",
            description = "Updates the schedule with the provided data."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Schedule successfully", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Schedule is not updated", content = @Content)
    })
    @PutMapping(value = "/update-schedule")
    public ResponseEntity<Response> updateSchedule(@RequestBody ScheduleDtoRequest request) {
        try {
            ScheduleDto scheduleDto = principleService.updateSchedule(request);
            return ResponseEntity.ok(new Response("Updated Schedule successfully.", scheduleDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Schedule is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Hire a new employee",
            description = "Adds a new employee based on the provided data."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added Employee", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Employee is not saved", content = @Content)
    })
    @PostMapping(value = "/hire-employee")
    public ResponseEntity<Response> createEmployee(@RequestBody EmployeeDroRequest request) {
        try {
            principleService.hireEmployee(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added Employee", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Update an existing employee",
            description = "Updates the details of an existing employee based on the provided EmployeeDroRequest object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Failed to update employee due to bad request.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee not found.", content = @Content)
    })
    @PutMapping(value = "/update-employee")
    public ResponseEntity<Response> updateEmployee(@RequestBody EmployeeDroRequest request) {
        try {
            EmployeeDto createEmployee = principleService.updateEmployee(request);
            return ResponseEntity.ok(new Response("Updated Employee successfully.", createEmployee));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Retrieve all employees",
            description = "Retrieves a list of all employees in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all employees.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "No employees found.", content = @Content)
    })
    @GetMapping("/get-all-employee")
    public ResponseEntity<Response> getAllEmployee() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Employee.", principleService.getAllEmployee()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Retrieve all active employees",
            description = "Retrieves a list of all active employees in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all active employees.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "No active employees found.", content = @Content)
    })
    @GetMapping("/get-all-active-employee")
    public ResponseEntity<Response> getAllActiveEmployee() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Employee.", principleService.getAllActiveSubject()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Fire an employee",
            description = "Deletes an employee from the system based on the provided employee ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee deleted successfully.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Employee not found.", content = @Content)
    })
    @DeleteMapping(value = "/fire-employee/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId) {
        try {
            principleService.fireEmployee(employeeId);
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to fire. " + exception.getMessage());
        }
    }

    @Operation(summary = "Create a new subject",
            description = "Adds a new subject to the system based on the provided SubjectDtoRequest object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subject created successfully.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Failed to save subject due to bad request.", content = @Content)
    })
    @PostMapping(value = "/create-subject")
    public ResponseEntity<Response> createSubject(@RequestBody SubjectDtoRequest request) {
        try {
            principleService.addSubject(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added Subject.", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Subject is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Update an existing subject",
            description = "Updates the details of an existing subject based on the provided SubjectDtoRequest object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subject updated successfully.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Failed to update subject due to bad request.", content = @Content)
    })
    @PutMapping(value = "/update-subject")
    public ResponseEntity<Response> updateSubject(@RequestBody SubjectDtoRequest request) {
        try {
            SubjectDto createSubject = principleService.updateSubject(request);
            return ResponseEntity.ok(new Response("Updated Subject successfully.", createSubject));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Subject is not updated." + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Delete a subject",
            description = "Deletes a subject from the system based on the provided subject ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subject deleted successfully.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Subject not found.", content = @Content)
    })
    @DeleteMapping(value = "/delete-subject/{id}")
    public ResponseEntity<Response> deleteSubject(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Deleted Subject successfully.", principleService.deleteSubject(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Restore a deleted subject",
            description = "Restores a previously deleted subject based on the provided subject ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subject restored successfully.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Subject not found.", content = @Content)
    })
    @PutMapping(value = "/restore-subject/{subjectId}")
    public ResponseEntity<Response> restoreSubject(@PathVariable Long subjectId) {
        try {
            return ResponseEntity.ok(new Response("Restored Subject successfully.", principleService.restoreSubject(subjectId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get all subjects",
            description = "Retrieves a list of all subjects in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all subjects.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "No subjects found.", content = @Content)
    })
    @GetMapping(value = "/get-all-subject")
    public ResponseEntity<Response> getAllSubject() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Subject.", principleService.getAllSubject()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get all active subjects",
            description = "Retrieves a list of all active subjects currently available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all active subjects.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "No active subjects found.", content = @Content)
    })
    @GetMapping(value = "/get-all-active-subject")
    public ResponseEntity<Response> getAllActiveSubject() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Subject.", principleService.getAllActiveSubject()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Create a new charter",
            description = "Creates a new charter based on the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created charter.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Charter is not saved due to invalid data.", content = @Content)
    })
    @PostMapping(value = "/create-charter")
    public ResponseEntity<Response> createCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto charterDto = principleService.createCharter(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully Charter created.", charterDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Update an existing charter",
            description = "Updates an existing charter with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated charter successfully.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Charter is not updated due to invalid data.", content = @Content)
    })
    @PutMapping(value = "/update-charter")
    public ResponseEntity<Response> updateCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto createCharter = principleService.updateCharter(request);
            return ResponseEntity.ok(new Response("Updated Charter successfully.", createCharter));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Delete a charter",
            description = "Deletes a charter based on the provided charter ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted Charter successfully.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Charter not found with the given ID.", content = @Content)
    })
    @DeleteMapping(value = "/delete-charter/{charterId}")
    public ResponseEntity<Response> deleteCharter(@PathVariable Long charterId) {
        try {
            return ResponseEntity.ok(new Response("Deleted Charter successfully", principleService.deleteCharter(charterId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Restore a charter",
            description = "Restores a previously deleted charter based on the provided charter ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restored Charter successfully.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Charter not found with the given ID.", content = @Content)
    })
    @PutMapping(value = "/restore-charter/{charterId}")
    public ResponseEntity<Response> restoreCharter(@PathVariable Long charterId) {
        try {
            return ResponseEntity.ok(new Response("Restored Charter successfully.", principleService.restoreCharter(charterId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get all charters",
            description = "Retrieves a list of all charters available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all Charters.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "No charters found.", content = @Content)
    })
    @GetMapping(value = "/get-all-charter")
    public ResponseEntity<Response> getAllCharter() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Charter.", principleService.getAllCharter()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get all self-authored charters",
            description = "Retrieves a list of charters authored by the current user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all self Charters.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "No self charters found.", content = @Content)
    })
    @GetMapping(value = "/get-all-self-charter")
    public ResponseEntity<Response> getAllCharterByAuthor() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all self Charter.", principleService.getAllCharterByAuthor()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Create a new assignment",
            description = "Creates a new assignment based on the provided request data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added Assignment.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Assignment is not saved due to validation errors or other issues.", content = @Content)
    })
    @PostMapping(value = "/create-assignment")
    public ResponseEntity<Response> createAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto assignmentDto = principleService.createAssignment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added Assignment.", assignmentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Assignment is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Update an existing assignment",
            description = "Updates an existing assignment based on the provided request data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assignment successfully updated.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Assignment is not updated due to validation errors or other issues.", content = @Content)
    })
    @PutMapping(value = "/update-assignment")
    public ResponseEntity<Response> updateAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto createAssignment = principleService.updateAssignment(request);
            return ResponseEntity.ok(new Response("Assignment successfully updated.", createAssignment));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Assignment is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Delete an assignment",
            description = "Deletes an assignment based on the provided assignment ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted Assignment successfully.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Assignment not found with the given ID.", content = @Content)
    })
    @DeleteMapping(value = "/delete-assignment/{assignmentId}")
    public ResponseEntity<Response> deleteAssignment(@PathVariable Long assignmentId) {
        try {
            return ResponseEntity.ok(new Response("Deleted Assignment successfully.", principleService.deleteAssignment(assignmentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Restore a deleted assignment",
            description = "Restores a previously deleted assignment based on the provided assignment ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restored Assignment successfully.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Assignment not found with the given ID.", content = @Content)
    })
    @PutMapping(value = "/restore-assignment/{assignmentId}")
    public ResponseEntity<Response> restoreAssignment(@PathVariable Long assignmentId) {
        try {
            return ResponseEntity.ok(new Response("Restored Assignment successfully.", principleService.restoreAssignment(assignmentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get all assignments by the author",
            description = "Retrieves all assignments created by the currently authenticated author.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all Assignment.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find any assignments.", content = @Content)
    })
    @GetMapping(value = "/get-all-assignment")
    public ResponseEntity<Response> getAllAssignmentByAuthor() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Assignment.", principleService.getAllAssignmentByAuthor()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get all undone assignments",
            description = "Retrieves all assignments that are not yet completed.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all undone Assignment.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find any undone assignments.", content = @Content)
    })
    @GetMapping(value = "/get-all-undone-assignment")
    public ResponseEntity<Response> getAllUndoneAssigment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone Assignment.", principleService.getAllUndoneAssigment()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get average mark for a student in a specific quarter",
            description = "Retrieves the average mark for a given student in the specified quarter.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Mark.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find marks for the given student in the specified quarter.", content = @Content)
    })
    @GetMapping("/get-quarter-student-mark/{quarter}/{studentId}")
    public ResponseEntity<Response> getAvgMarkByGradeStudentQuarter(@PathVariable Integer quarter,
                                                                    @PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Mark.", principleService.getAvgMarkByGradeStudentQuarter(quarter, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get average mark for a student in a specific subject",
            description = "Retrieves the average mark of a specific student in a specified subject.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Mark.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find marks for the given subject and student.", content = @Content)
    })
    @GetMapping("/get-subject-student-mark/{subjectId}/{studentId}")
    public ResponseEntity<Response> getAvgMarkBySubjectGradeStudent(@PathVariable Long subjectId,
                                                                    @PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Mark.", principleService.getAvgMarkBySubjectGradeStudent(subjectId, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get average mark for a student",
            description = "Retrieves the average mark of a specified student.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Mark.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find marks for the given student.", content = @Content)
    })
    @GetMapping("/get-student-mark/{studentId}")
    public ResponseEntity<Response> getAvgMarkByGradeStudent(@PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Mark.", principleService.getAvgMarkByGradeStudent(studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get average mark for a grade in a specific quarter",
            description = "Retrieves the average mark of a specified grade in a given quarter.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Mark.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find marks for the given grade in the specified quarter.", content = @Content)
    })
    @GetMapping("/get-quarter-grade-mark/{quarter}/{gradeId}")
    public ResponseEntity<Response> getAvgMarkByGradeQuarter(@PathVariable Integer quarter,
                                                             @PathVariable Long gradeId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Mark.", principleService.getAvgMarkByGradeQuarter(quarter, gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get average mark for a specific grade",
            description = "Retrieves the average mark of a specified grade.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Mark.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find marks for the given grade.", content = @Content)
    })
    @GetMapping("/get-grade-mark/{gradeId}")
    public ResponseEntity<Response> getAvgMarkByGrade(@PathVariable Long gradeId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Mark.", principleService.getAvgMarkByGrade(gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get average marks for all grades",
            description = "Retrieves the average marks for all grades.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Marks.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find marks.", content = @Content)
    })
    @GetMapping("/get-grades-mark")
    public ResponseEntity<Response> getAvgMarks() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", principleService.getAvgMarks()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get average mark for the school",
            description = "Retrieves the average mark for the entire school.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Mark.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find school mark.", content = @Content)
    })
    @GetMapping("/get-school-avg-mark")
    public ResponseEntity<Response> getAvgSchoolMark() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Mark.", principleService.getAvgSchoolMark()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get student attendance for a specific quarter and grade",
            description = "Retrieves the attendance records for a student in a specific grade during a particular quarter.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendances.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find attendance records.", content = @Content)
    })
    @GetMapping("/get-quarter-grade-student-attend/{quarter}/{gradeId}/{studentId}")
    public ResponseEntity<Response> getAttendByQuarterGradeStudent(@PathVariable Integer quarter,
                                                                   @PathVariable Long gradeId,
                                                                   @PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendances.", principleService.getAttendByQuarterGradeStudent(quarter, gradeId, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get student attendance for a specific subject and grade",
            description = "Retrieves the attendance records for a student in a specific grade for a particular subject.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendances.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find attendance records.", content = @Content)
    })
    @GetMapping("/get-subject-grade-student-attend/{subjectId}/{gradeId}/{studentId}")
    public ResponseEntity<Response> getAttendByQuarterGradeStudent(@PathVariable Long subjectId,
                                                                   @PathVariable Long gradeId,
                                                                   @PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendances.", principleService.getAttendBySubjectGradeStudent(subjectId, gradeId, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get overall student attendance for a specific grade",
            description = "Retrieves the overall attendance records for a student in a specified grade.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendances.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find attendance records.", content = @Content)
    })
    @GetMapping("/get-grade-student-attend/{gradeId}/{studentId}")
    public ResponseEntity<Response> getAttendByGradeStudent(@PathVariable Long gradeId,
                                                            @PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendances.", principleService.getAttendByGradeStudent(gradeId, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get attendance records for a specific quarter and grade",
            description = "Retrieves attendance records for a specified quarter and grade.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendances.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find attendance records.", content = @Content)
    })
    @GetMapping("/get-quarter-grade-attend/{quarter}/{gradeId}")
    public ResponseEntity<Response> getAttendByQuarterGrade(@PathVariable Integer quarter,
                                                            @PathVariable Long gradeId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendances.", principleService.getAttendByQuarterGrade(quarter, gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get overall attendance records for a specific grade",
            description = "Retrieves overall attendance records for a specified grade.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendances.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find attendance records.", content = @Content)
    })
    @GetMapping("/get-grade-attend/{gradeId}")
    public ResponseEntity<Response> getAttendByQuarterGrade(@PathVariable Long gradeId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendances.", principleService.getAttendByGrade(gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get attendance records for all grades",
            description = "Retrieves attendance records for all grades.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendances.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find attendance records.", content = @Content)
    })
    @GetMapping("/get-grades-attend")
    public ResponseEntity<Response> getAttendGrades() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendances.", principleService.getAttendGrades()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Create a new announcement",
            description = "Creates a new announcement with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created Announcement.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Announcement is not saved due to validation errors.", content = @Content)
    })
    @PostMapping(value = "/create-announcement")
    public ResponseEntity<Response> createAnnouncement(@RequestBody AnnouncementDtoRequest request) {
        try {
            AnnouncementDto announcementDto = principleService.createAnnouncement(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Announcement.", announcementDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Announcement is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Update an existing announcement",
            description = "Updates the details of an existing announcement based on the provided request.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Announcement updated successfully.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Announcement not updated due to validation errors.", content = @Content)
    })
    @PutMapping(value = "/update-announcement")
    public ResponseEntity<Response> updateAnnouncement(@RequestBody AnnouncementDtoRequest request) {
        try {
            AnnouncementDto announcementDto = principleService.updateAnnouncement(request);
            return ResponseEntity.ok(new Response("Announcement Schedule successfully.", announcementDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Announcement not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Delete an announcement",
            description = "Deletes an announcement based on the provided announcement ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted Announcement with id.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Announcement not found for the given ID.", content = @Content)
    })
    @DeleteMapping("/delete-announcement/{announcementId}")
    public ResponseEntity<Response> deleteAnnouncement(@PathVariable Long announcementId) {
        try {
            return ResponseEntity.ok(new Response("Successfully deleted Announcement with id.", principleService.deleteAnnouncement(announcementId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Restore a deleted announcement",
            description = "Restores a deleted announcement based on the provided announcement ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully restored Announcement with id.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Announcement not found for the given ID.", content = @Content)
    })
    @PutMapping("/restore-announcement/{announcementId}")
    public ResponseEntity<Response> restoreAnnouncement(@PathVariable Long announcementId) {
        try {
            return ResponseEntity.ok(new Response("Successfully restored Announcement with id.", principleService.restoreAnnouncement(announcementId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get all active announcements",
            description = "Retrieves a list of all active announcements.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all active Announcements.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error while fetching announcements.", content = @Content)
    })
    @GetMapping(value = "/get-all-active-announcement")
    public ResponseEntity<Response> getAllActiveAnnouncement() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Announcement.", principleService.getAllActiveAnnouncement()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get all self-created announcements",
            description = "Retrieves a list of all announcements created by the authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all self Announcements.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error while fetching announcements.", content = @Content)
    })
    @GetMapping(value = "/get-all-self-announcement")
    public ResponseEntity<Response> getAllSelfAnnouncement() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all self Announcement.", principleService.getAllSelfAnnouncement()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
}
