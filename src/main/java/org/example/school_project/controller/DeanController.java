package org.example.school_project.controller;

import java.util.List;

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
import org.example.school_project.service.DeanService;

@RestController
@RequestMapping(value = "/dean")
@AllArgsConstructor
@Tag(name = "Dean Management", description = "Operations related to dean role.")
public class DeanController {
    private final DeanService deanService;

    @Operation(
            summary = "Create a new schedule",
            description = "Creates a new schedule based on the provided ScheduleDtoRequest. " +
                    "This endpoint allows the dean to create a schedule for classes."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created Schedule.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Schedule is not saved due to invalid input.",
                    content = @Content)
    })
    @PostMapping(value = "/create-schedule")
    public ResponseEntity<Response> createSchedule(@RequestBody ScheduleDtoRequest request) {
        try {
            ScheduleDto scheduleDto = deanService.createSchedule(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Schedule.", scheduleDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Schedule is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update an existing schedule",
            description = "Updates an existing schedule based on the provided ScheduleDtoRequest. " +
                    "This endpoint allows the dean to modify the details of an existing schedule."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Schedule successfully.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Schedule not updated due to invalid input.",
                    content = @Content)
    })
    @PutMapping(value = "/update-schedule")
    public ResponseEntity<Response> updateSchedule(@RequestBody ScheduleDtoRequest request) {
        try {
            ScheduleDto scheduleDto = deanService.updateSchedule(request);
            return ResponseEntity.ok(new Response("Updated Schedule successfully.", scheduleDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Schedule not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Delete a schedule by ID",
            description = "Deletes a specific schedule based on the provided schedule ID. " +
                    "This endpoint allows the dean to remove a schedule from the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted Schedule with id.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Schedule not found for the specified ID.",
                    content = @Content)
    })
    @DeleteMapping("/delete-schedule/{scheduleId}")
    public ResponseEntity<Response> deleteSchedule(@PathVariable Long scheduleId) {
        try {
            return ResponseEntity.ok(new Response("Successfully deleted Schedule with id.", deanService.deleteSchedule(scheduleId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Restore a schedule by ID",
            description = "Restores a schedule that was previously deleted based on the provided schedule ID. " +
                    "This endpoint allows the dean to recover a deleted schedule."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully restored Schedule with id.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Schedule not found for the specified ID.",
                    content = @Content)
    })
    @PutMapping("/restore-schedule/{scheduleId}")
    public ResponseEntity<Response> restoreSchedule(@PathVariable Long scheduleId) {
        try {
            return ResponseEntity.ok(new Response("Successfully restored Schedule with id.", deanService.restoreSchedule(scheduleId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Change teacher's schedule by teacher ID",
            description = "Updates the schedule for the specified teacher based on the provided teacher ID. " +
                    "This endpoint allows the dean to modify the schedule of a teacher."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Changed Teacher successfully.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Failed to Change Teacher due to invalid input.",
                    content = @Content)
    })
    @PutMapping(value = "/change-teacher-schedule/{teacherId}")
    public ResponseEntity<Response> updateSchedule(@PathVariable Long teacherId) {
        try {
            ScheduleDto scheduleDto = deanService.changeTeacherSchedule(teacherId);
            return ResponseEntity.ok(new Response("Changed Teacher successfully.", scheduleDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Failed to Change Teacher. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get count of all teacher schedules",
            description = "Retrieves the total number of schedules for all teachers. " +
                    "This endpoint allows the dean to see how many schedules are assigned to teachers."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all teachers schedule count.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find the count due to internal server error.",
                    content = @Content)
    })
    @GetMapping(value = "/get-all-teacher-schedule-count")
    public ResponseEntity<Response> getAllTeacherScheduleNum() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all teachers schedule count.", deanService.getAllTeacherScheduleNum()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all schedules",
            description = "Retrieves a list of all schedules available in the system. " +
                    "This endpoint allows the dean to view all existing schedules."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all Schedules.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find the schedules due to internal server error.",
                    content = @Content)
    })
    @GetMapping("/get-all-schedule")
    public ResponseEntity<Response> getAllSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Schedules.", deanService.getAllSchedule()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all active schedules",
            description = "Retrieves a list of all active schedules in the system. " +
                    "This endpoint allows the dean to view schedules that are currently active."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all active Schedules.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find the active schedules due to internal server error.",
                    content = @Content)
    })
    @GetMapping("/get-all-active-schedule")
    public ResponseEntity<Response> getAllActiveSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Schedules", deanService.getAllActiveSchedule()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Create a new grade",
            description = "Creates a new grade based on the provided grade data. " +
                    "This endpoint allows the dean to add a new grade to the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created Grade",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Grade is not saved due to bad request.",
                    content = @Content)
    })
    @PostMapping(value = "/create-grade")
    public ResponseEntity<Response> createGrade(@RequestBody GradeDtoRequest request) {
        try {
            GradeDto gradeDto = deanService.createGrade(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Grade", gradeDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Grade is not saved" + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update an existing grade",
            description = "Updates an existing grade based on the provided grade data. " +
                    "This endpoint allows the dean to modify an existing grade in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Grade successfully",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Grade is not updated due to bad request.",
                    content = @Content)
    })
    @PutMapping(value = "/update-grade")
    public ResponseEntity<Response> updateGrade(@RequestBody GradeDtoRequest request) {
        try {
            GradeDto gradeDto = deanService.updateGrade(request);
            return ResponseEntity.ok(new Response("Updated Grade successfully", gradeDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Grade is not updated" + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Delete a grade by ID",
            description = "Deletes the grade associated with the specified ID. " +
                    "This endpoint allows the dean to remove a grade from the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted Grade successfully",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Failed to delete due to grade not found.",
                    content = @Content)
    })
    @DeleteMapping(value = "/delete-grade/{gradeId}")
    public ResponseEntity<Response> deleteGrade(@PathVariable Long gradeId) {
        try {
            GradeDto gradeDto = deanService.deleteGrade(gradeId);
            return ResponseEntity.ok(new Response("Deleted Grade successfully", gradeDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Restore a grade by ID",
            description = "Restores the grade associated with the specified ID. " +
                    "This endpoint allows the dean to recover a previously deleted grade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restored Grade successfully.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Failed to restore due to grade not found.",
                    content = @Content)
    })
    @PutMapping(value = "/restore-grade/{gradeId}")
    public ResponseEntity<Response> restoreGrade(@PathVariable Long gradeId) {
        try {
            GradeDto gradeDto = deanService.restoreGrade(gradeId);
            return ResponseEntity.ok(new Response("Restored Grade successfully.", gradeDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all active grades",
            description = "Retrieves a list of all active grades from the system. " +
                    "This endpoint allows the dean to view the grades currently in use."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Got all active Grade successfully.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find due to internal server error.",
                    content = @Content)
    })
    @GetMapping("/get-all-active-grade")
    public ResponseEntity<Response> getAllActiveGrade() {
        try {
            List<GradeDto> gradeDtoList = deanService.getAllActiveGrade();
            return ResponseEntity.ok(new Response("Got all active Grade successfully. ", gradeDtoList));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all grades",
            description = "Retrieves a list of all grades from the system. " +
                    "This endpoint allows the dean to view all grades, regardless of their active status."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Got all Grade successfully.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find due to internal server error.",
                    content = @Content)
    })
    @GetMapping("/get-all-grade")
    public ResponseEntity<Response> getAllGrade() {
        try {
            List<GradeDto> gradeDtoList = deanService.getAllGrade();
            return ResponseEntity.ok(new Response("Got all Grade successfully", gradeDtoList));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Create a new charter",
            description = "Creates a new charter based on the provided charter data. " +
                    "This endpoint allows the dean to add a new charter to the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created Charter.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Charter is not saved due to bad request.",
                    content = @Content)
    })
    @PostMapping(value = "/create-charter")
    public ResponseEntity<Response> createCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto charterDto = deanService.createCharter(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Charter.", charterDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update an existing charter",
            description = "Updates an existing charter based on the provided charter data. " +
                    "This endpoint allows the dean to modify an existing charter in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated successfully Charter",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Charter is not updated due to bad request.",
                    content = @Content)
    })
    @PutMapping(value = "/update-charter")
    public ResponseEntity<Response> updateCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto createCharter = deanService.updateCharter(request);
            return ResponseEntity.ok(new Response("Updated successfully Charter", createCharter));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Delete a charter",
            description = "Deletes a charter from the system based on the provided charter ID. " +
                    "This endpoint allows the dean to remove a charter that is no longer needed."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted Charter successfully",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Failed to delete. Charter not found.",
                    content = @Content)
    })
    @DeleteMapping(value = "/delete-charter/{carterId}")
    public ResponseEntity<Response> deleteCharter(@PathVariable Long carterId) {
        try {
            return ResponseEntity.ok(new Response("Deleted Charter successfully", deanService.deleteCharter(carterId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Restore a charter",
            description = "Restores a previously deleted charter based on the provided charter ID. " +
                    "This endpoint allows the dean to recover a charter that was mistakenly deleted."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restored Charter successfully",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Failed to restore. Charter not found.",
                    content = @Content)
    })
    @PutMapping(value = "/restore-charter/{carterId}")
    public ResponseEntity<Response> restoreCharter(@PathVariable Long carterId) {
        try {
            return ResponseEntity.ok(new Response("Restored Charter successfully", deanService.restoreCharter(carterId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all charters",
            description = "Retrieves a list of all charters from the system. " +
                    "This endpoint allows the dean to view all available charters."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all Charter",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find due to internal server error.",
                    content = @Content)
    })
    @GetMapping(value = "/get-all-charter")
    public ResponseEntity<Response> getAllCharter() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Charter", deanService.getAllCharter()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all self charters",
            description = "Retrieves a list of all charters created by the author. " +
                    "This endpoint allows the dean to view charters that they have personally created."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all self Charter.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find due to internal server error.",
                    content = @Content)
    })
    @GetMapping(value = "/get-all-self-charter")
    public ResponseEntity<Response> getAllCharterByAuthor() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all self Charter.", deanService.getAllCharterByAuthor()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get student by ID",
            description = "Retrieves student details based on the provided student ID. " +
                    "This endpoint allows the dean to access information about a specific student."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Student",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't find. Student not found.",
                    content = @Content)
    })
    @GetMapping("/get-student/{id}")
    public ResponseEntity<Response> getStudentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Student.", deanService.getStudentById(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update student",
            description = "Updates the details of a student based on the provided student data. " +
                    "This endpoint allows the dean to modify student information."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Student successfully",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Couldn't find. Bad request.",
                    content = @Content)
    })
    @PutMapping(value = "/update-student")
    public ResponseEntity<Response> updateStudent(@RequestBody StudentDtoRequest request) {
        try {
            StudentDto studentDto = deanService.updateStudent(request);
            return ResponseEntity.ok(new Response("Updated Student successfully.", studentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Exclude student",
            description = "Excludes a student from the system based on the provided student ID. " +
                    "This endpoint allows the dean to remove a student from the active list."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Excluded Student successfully",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Failed to exclude. Student not found.",
                    content = @Content)
    })
    @DeleteMapping(value = "/exclude-student/{studentId}")
    public ResponseEntity<Response> excludeStudent(@PathVariable Long studentId) {
        try {
            StudentDto studentDto = deanService.excludeStudent(studentId);
            return ResponseEntity.ok(new Response("Excluded Student successfully.", studentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to exclude. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Restore student",
            description = "Restores a previously excluded student based on the provided student ID. " +
                    "This endpoint allows the dean to reinstate a student who was removed."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restored Student successfully",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Failed to restore. Student not found.",
                    content = @Content)
    })
    @PutMapping(value = "/restore-student/{studentId}")
    public ResponseEntity<Response> restoreStudent(@PathVariable Long studentId) {
        try {
            StudentDto studentDto = deanService.restoreStudent(studentId);
            return ResponseEntity.ok(new Response("Restored Student successfully.", studentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all active students",
            description = "Retrieves a list of all active students in the system. " +
                    "This endpoint allows the dean to access information about all currently active students."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Got all active students successfully",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find. Internal server error.",
                    content = @Content)
    })
    @GetMapping("/get-all-active-student")
    public ResponseEntity<Response> getAllActiveStudent() {
        try {
            List<StudentDto> studentDtoList = deanService.getAllActiveStudent();
            return ResponseEntity.ok(new Response("Got all active Student successfully", studentDtoList));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all students",
            description = "Retrieves a list of all students in the system. " +
                    "This endpoint allows the dean to access information about all students."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Got all students successfully",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find. Internal server error.",
                    content = @Content)
    })
    @GetMapping("/get-all-student")
    public ResponseEntity<Response> getAllStudent() {
        try {
            List<StudentDto> studentDtoList = deanService.getAllStudent();
            return ResponseEntity.ok(new Response("Got all Student successfully", studentDtoList));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Create assignment",
            description = "Creates a new assignment based on the provided assignment data. " +
                    "This endpoint allows the dean to add assignments to the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added Assignment",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Assignment is not saved. Bad request.",
                    content = @Content)
    })
    @PostMapping(value = "/create-assignment")
    public ResponseEntity<Response> createAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto assignmentDto = deanService.createAssignment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added Assignment.", assignmentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Assignment is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update assignment",
            description = "Updates an existing assignment based on the provided assignment data. " +
                    "This endpoint allows the dean to modify assignments."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assignment successfully updated",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Assignment is not updated. Bad request.",
                    content = @Content)
    })
    @PutMapping(value = "/update-assignment")
    public ResponseEntity<Response> updateAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto createAssignment = deanService.updateAssignment(request);
            return ResponseEntity.ok(new Response("Assignment successfully updated.", createAssignment));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Assignment is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Delete assignment",
            description = "Deletes an assignment based on the provided assignment ID. " +
                    "This endpoint allows the dean to remove assignments from the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted Assignment successfully.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Failed to delete. Assignment not found.",
                    content = @Content)
    })
    @DeleteMapping(value = "/delete-assignment/{assignmentId}")
    public ResponseEntity<Response> deleteAssignment(@PathVariable Long assignmentId) {
        try {
            return ResponseEntity.ok(new Response("Deleted Assignment successfully.", deanService.deleteAssignment(assignmentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Restore assignment",
            description = "Restores a previously deleted assignment based on the provided assignment ID. " +
                    "This endpoint allows the dean to recover assignments."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restored Assignment successfully.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Failed to restore. Assignment not found.",
                    content = @Content)
    })
    @PutMapping(value = "/restore-assignment/{id}")
    public ResponseEntity<Response> restoreAssignment(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Restored Assignment successfully.", deanService.restoreAssignment(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all assignments by author",
            description = "Retrieves all assignments created by the author. " +
                    "This endpoint allows the dean to access all assignments associated with the current user."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all Assignments.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find. Internal server error.",
                    content = @Content)
    })
    @GetMapping(value = "/get-all-assignment")
    public ResponseEntity<Response> getAllAssignmentByAuthor() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Assignment.", deanService.getAllAssignmentByAuthor()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all undone assignments",
            description = "Retrieves all assignments that have not been completed. " +
                    "This endpoint allows the dean to view assignments that are still pending."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all undone Assignments.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find. Internal server error.",
                    content = @Content)
    })
    @GetMapping(value = "/get-all-undone-assignment")
    public ResponseEntity<Response> getAllUndoneAssigment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone Assignment.", deanService.getAllUndoneAssigment()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all done assignments",
            description = "Retrieves all assignments that have been completed. " +
                    "This endpoint allows the dean to view all completed assignments."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all done Assignments.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find. Internal server error.",
                    content = @Content)
    })
    @GetMapping(value = "/get-all-done-assignment")
    public ResponseEntity<Response> getAllDoneAssigment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all done Assignment.", deanService.getAllDoneAssigment()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get average mark by grade student for a quarter",
            description = "Retrieves the average mark for a student in a specific quarter. " +
                    "This endpoint allows the dean to assess student performance over a specific timeframe."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Mark.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't found. Student or quarter not found.",
                    content = @Content)
    })
    @GetMapping("/get-quarter-student-mark/{quarter}/{studentId}")
    public ResponseEntity<Response> getAvgMarkByGradeStudentQuarter(@PathVariable Integer quarter,
                                                                    @PathVariable Long studentId) {
        try {
            deanService.getAvgMarkByGradeStudentQuarter(quarter, studentId);
            return ResponseEntity.ok(new Response("Successfully got Mark.", deanService.getAvgMarkByGradeStudentQuarter(quarter, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get average mark by subject for a student",
            description = "Retrieves the average mark for a student in a specific subject. " +
                    "This endpoint allows the dean to evaluate student performance in specific subjects."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Mark.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't found. Student or subject not found.",
                    content = @Content)
    })
    @GetMapping("/get-subject-student-mark/{subjectId}/{studentId}")
    public ResponseEntity<Response> getAvgMarkBySubjectGradeStudent(@PathVariable Long subjectId,
                                                                    @PathVariable Long studentId) {
        try {
            deanService.getAvgMarkBySubjectGradeStudent(subjectId, studentId);
            return ResponseEntity.ok(new Response("Successfully got Mark.", deanService.getAvgMarkBySubjectGradeStudent(subjectId, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get average mark by grade for a student",
            description = "Retrieves the average mark for a student across all subjects. " +
                    "This endpoint allows the dean to assess the overall performance of a student."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Mark.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't found. Student not found.",
                    content = @Content)
    })
    @GetMapping("/get-student-mark/{studentId}")
    public ResponseEntity<Response> getAvgMarkByGradeStudent(@PathVariable Long studentId) {
        try {
            deanService.getAvgMarkByGradeStudent(studentId);
            return ResponseEntity.ok(new Response("Successfully got Mark.", deanService.getAvgMarkByGradeStudent(studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get average mark by grade for a quarter",
            description = "Retrieves the average mark for a specific grade during a specific quarter. " +
                    "This endpoint allows the dean to evaluate the performance of a grade in a particular timeframe."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Mark.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't found. Grade or quarter not found.",
                    content = @Content)
    })
    @GetMapping("/get-quarter-grade-mark/{quarter}/{gradeId}")
    public ResponseEntity<Response> getAvgMarkByGradeQuarter(@PathVariable Integer quarter,
                                                             @PathVariable Long gradeId) {
        try {
            deanService.getAvgMarkByGradeQuarter(quarter, gradeId);
            return ResponseEntity.ok(new Response("Successfully got Mark.", deanService.getAvgMarkByGradeQuarter(quarter, gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get average mark by grade",
            description = "Retrieves the average mark for a specific grade. " +
                    "This endpoint allows the dean to assess the overall performance of a grade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Mark.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't found. Grade not found.",
                    content = @Content)
    })
    @GetMapping("/get-grade-mark/{gradeId}")
    public ResponseEntity<Response> getAvgMarkByGrade(@PathVariable Long gradeId) {
        try {
            deanService.getAvgMarkByGrade(gradeId);
            return ResponseEntity.ok(new Response("Successfully got Mark.", deanService.getAvgMarkByGrade(gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get average marks for all grades",
            description = "Retrieves the average marks for all grades. " +
                    "This endpoint allows the dean to see the overall performance across grades."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Marks.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't found. No marks available.",
                    content = @Content)
    })
    @GetMapping("/get-grades-mark")
    public ResponseEntity<Response> getAvgMarks() {
        try {
            deanService.getAvgMarks();
            return ResponseEntity.ok(new Response("Successfully got Marks.", deanService.getAvgMarks()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get average school mark",
            description = "Retrieves the average mark for the entire school. " +
                    "This endpoint allows the dean to assess the overall performance of the school."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Mark.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't found. No average mark available.",
                    content = @Content)
    })
    @GetMapping("/get-school-avg-mark")
    public ResponseEntity<Response> getAvgSchoolMark() {
        try {
            deanService.getAvgSchoolMark();
            return ResponseEntity.ok(new Response("Successfully got Mark.", deanService.getAvgSchoolMark()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get student attendance by quarter and grade",
            description = "Retrieves the attendance records of a specific student in a particular grade for a specified quarter. " +
                    "This endpoint allows the dean to evaluate student attendance during the specified period."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendances.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't found. Attendance records not found.",
                    content = @Content)
    })
    @GetMapping("/get-quarter-grade-student-attend/{quarter}/{gradeId}/{studentId}")
    public ResponseEntity<Response> getAttendByQuarterGradeStudent(@PathVariable Integer quarter,
                                                                   @PathVariable Long gradeId,
                                                                   @PathVariable Long studentId) {
        try {
            deanService.getAttendByQuarterGradeStudent(quarter, gradeId, studentId);
            return ResponseEntity.ok(new Response("Successfully got Attendances.", deanService.getAttendByQuarterGradeStudent(quarter, gradeId, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get student attendance by subject and grade",
            description = "Retrieves the attendance records of a specific student for a specified subject in a particular grade. " +
                    "This endpoint allows the dean to evaluate student attendance for a specific subject."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendances.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't found. Attendance records not found.",
                    content = @Content)
    })
    @GetMapping("/get-subject-grade-student-attend/{subjectId}/{gradeId}/{studentId}")
    public ResponseEntity<Response> getAttendByQuarterGradeStudent(@PathVariable Long subjectId,
                                                                   @PathVariable Long gradeId,
                                                                   @PathVariable Long studentId) {
        try {
            deanService.getAttendBySubjectGradeStudent(subjectId, gradeId, studentId);
            return ResponseEntity.ok(new Response("Successfully got Attendances.", deanService.getAttendBySubjectGradeStudent(subjectId, gradeId, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get student attendance by grade",
            description = "Retrieves the attendance records of a specific student in a particular grade. " +
                    "This endpoint allows the dean to assess student attendance across all subjects in the grade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendances.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't found. Attendance records not found.",
                    content = @Content)
    })
    @GetMapping("/get-grade-student-attend/{gradeId}/{studentId}")
    public ResponseEntity<Response> getAttendByGradeStudent(@PathVariable Long gradeId,
                                                            @PathVariable Long studentId) {
        try {
            deanService.getAttendByGradeStudent(gradeId, studentId);
            return ResponseEntity.ok(new Response("Successfully got Attendances.", deanService.getAttendByGradeStudent(gradeId, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get attendance by quarter and grade",
            description = "Retrieves the attendance records for all students in a specific grade for a specified quarter. " +
                    "This endpoint allows the dean to evaluate the overall attendance performance of the grade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendances.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't found. Attendance records not found.",
                    content = @Content)
    })
    @GetMapping("/get-quarter-grade-attend/{quarter}/{gradeId}")
    public ResponseEntity<Response> getAttendByQuarterGrade(@PathVariable Integer quarter,
                                                            @PathVariable Long gradeId) {
        try {
            deanService.getAttendByQuarterGrade(quarter, gradeId);
            return ResponseEntity.ok(new Response("Successfully got Attendances.", deanService.getAttendByQuarterGrade(quarter, gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get attendance by grade",
            description = "Retrieves the attendance records for all students in a specific grade. " +
                    "This endpoint allows the dean to evaluate the overall attendance performance of the grade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendances.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't found. Attendance records not found.",
                    content = @Content)
    })
    @GetMapping("/get-grade-attend/{gradeId}")
    public ResponseEntity<Response> getAttendByQuarterGrade(@PathVariable Long gradeId) {
        try {
            deanService.getAttendByGrade(gradeId);
            return ResponseEntity.ok(new Response("Successfully got Attendances.", deanService.getAttendByGrade(gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get attendance for all grades",
            description = "Retrieves the attendance records for all grades. " +
                    "This endpoint allows the dean to evaluate the overall attendance performance across all grades."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendances.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Couldn't found. Attendance records not found.",
                    content = @Content)
    })
    @GetMapping("/get-grades-attend")
    public ResponseEntity<Response> getAttendGrades() {
        try {
            deanService.getAttendGrades();
            return ResponseEntity.ok(new Response("Successfully got Attendances.", deanService.getAttendGrades()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Create an announcement",
            description = "Creates a new announcement for students or staff. " +
                    "This endpoint allows the dean to disseminate important information or updates."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created Announcement.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Announcement is not saved. Invalid input.",
                    content = @Content)
    })
    @PostMapping(value = "/create-announcement")
    public ResponseEntity<Response> createAnnouncement(@RequestBody AnnouncementDtoRequest request) {
        try {
            AnnouncementDto announcementDto = deanService.createAnnouncement(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Announcement.", announcementDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Announcement is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update an announcement",
            description = "Updates an existing announcement. " +
                    "This endpoint allows the dean to modify previously created announcements."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Announcement Schedule successfully.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Announcement not updated. Invalid input.",
                    content = @Content)
    })
    @PutMapping(value = "/update-announcement")
    public ResponseEntity<Response> updateAnnouncement(@RequestBody AnnouncementDtoRequest request) {
        try {
            AnnouncementDto announcementDto = deanService.updateAnnouncement(request);
            return ResponseEntity.ok(new Response("Announcement Schedule successfully.", announcementDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Announcement not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Delete an announcement",
            description = "Deletes an existing announcement by its ID. " +
                    "This endpoint allows the dean to remove announcements that are no longer relevant."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted Announcement with id.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Failed to delete. Announcement not found.",
                    content = @Content)
    })
    @DeleteMapping("/delete-announcement/{announcementId}")
    public ResponseEntity<Response> deleteAnnouncement(@PathVariable Long announcementId) {
        try {
            return ResponseEntity.ok(new Response("Successfully deleted Announcement with id.", deanService.deleteAnnouncement(announcementId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Restore an announcement",
            description = "Restores a deleted announcement by its ID. " +
                    "This endpoint allows the dean to recover announcements that were previously deleted."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully restored Announcement with id.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Failed to restore. Announcement not found.",
                    content = @Content)
    })
    @PutMapping("/restore-announcement/{announcementId}")
    public ResponseEntity<Response> restoreAnnouncement(@PathVariable Long announcementId) {
        try {
            return ResponseEntity.ok(new Response("Successfully restored Announcement with id.", deanService.restoreAnnouncement(announcementId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all active announcements",
            description = "Retrieves all currently active announcements. " +
                    "This endpoint provides a list of announcements that are relevant to students and staff."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all active Announcement.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find. An error occurred while retrieving announcements.",
                    content = @Content)
    })
    @GetMapping(value = "/get-all-active-announcement")
    public ResponseEntity<Response> getAllActiveAnnouncement() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Announcement.", deanService.getAllActiveAnnouncement()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all self announcements",
            description = "Retrieves all announcements created by the current user. " +
                    "This endpoint allows the dean to view their own announcements."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all self Announcement.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find. An error occurred while retrieving announcements.",
                    content = @Content)
    })
    @GetMapping(value = "/get-all-self-announcement")
    public ResponseEntity<Response> getAllSelfAnnouncement() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all self Announcement.", deanService.getAllSelfAnnouncement()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
}
