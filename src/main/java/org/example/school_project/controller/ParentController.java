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
import org.example.school_project.service.ParentRoleService;

@RestController
@RequestMapping(value = "/parent")
@AllArgsConstructor
@Tag(name = "Parent Management", description = "Operations related to parent role.")
public class ParentController {
    private final ParentRoleService parentRoleService;

    @Operation(summary = "Create a new student",
            description = "Creates a new student based on the provided student details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created Student.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Student is not saved due to validation errors.", content = @Content)
    })
    @PostMapping(value = "/create-student")
    public ResponseEntity<Response> createStudent(@RequestBody StudentDtoRequest request) {
        try {
            StudentDto studentDto = parentRoleService.createStudent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Student.", studentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Student is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get all children",
            description = "Retrieves a list of all children associated with the parent.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all children.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Children not found.", content = @Content)
    })
    @GetMapping("/get-all-child")
    public ResponseEntity<Response> getChildList() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all children.", parentRoleService.getChildList()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Children not found. " + exception.getMessage(), null));
        }
    }

    @Operation(summary = "Get marks for a child in a specific quarter",
            description = "Retrieves all marks for a specific child in the specified quarter.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Marks.", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Marks not found for the specified child.", content = @Content)
    })
    @GetMapping("/get-all-mark-quarter/{quarter}/{childId}")
    public ResponseEntity<Response> getAllMarkBySubjectQuarter(@PathVariable Integer quarter,
                                                               @PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", parentRoleService.getAvgMarkByGradeStudentQuarter(quarter, childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all marks for a child in a specific subject",
            description = "Retrieves all marks for a specific child in the specified subject. " +
                    "This endpoint allows parents to monitor their child's academic performance " +
                    "by providing the child's ID and the subject ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved marks.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Marks not found for the specified child and subject.",
                    content = @Content)
    })
    @GetMapping("/get-all-mark-subject/{subjectId}/{childId}")
    public ResponseEntity<Response> getAllMarkBySubjectQuarter(@PathVariable Long subjectId,
                                                               @PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", parentRoleService.getAvgMarkBySubjectGradeStudent(subjectId, childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get average marks for a child for the entire year",
            description = "Retrieves the average marks for a specific child for the entire academic year. " +
                    "This endpoint is useful for parents to assess their child's overall performance over the year."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved average marks.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Marks not found for the specified child.",
                    content = @Content)
    })
    @GetMapping("/get-all-mark-for-year/{childId}")
    public ResponseEntity<Response> getAvgMarkByGradeStudent(@PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", parentRoleService.getAvgMarkByGradeStudent(childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get attendance for a child in a specific quarter",
            description = "Retrieves attendance records for a specific child in the specified quarter. " +
                    "This endpoint helps parents keep track of their child's attendance in school."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved attendance records.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Attendance records not found for the specified child.",
                    content = @Content)
    })
    @GetMapping("/get-attendance-quarter/{quarter}/{childId}")
    public ResponseEntity<Response> getAttendByQuarterGradeStudent(@PathVariable Integer quarter,
                                                                   @PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendance.", parentRoleService.getAttendByQuarterGradeStudent(quarter, childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Attendance not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get attendance for a child in a specific subject",
            description = "Retrieves attendance records for a specific child in the specified subject. " +
                    "This endpoint allows parents to monitor their child's attendance in specific subjects."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved attendance.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Attendance not found for the specified child and subject.",
                    content = @Content)
    })
    @GetMapping("/get-attendance-subject/{subjectId}/{childId}")
    public ResponseEntity<Response> getAttendBySubjectGradeStudent(@PathVariable Long subjectId,
                                                                   @PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendance.", parentRoleService.getAttendBySubjectGradeStudent(subjectId, childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not Attendance. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get attendance for a child for the entire year",
            description = "Retrieves attendance records for a specific child for the entire academic year. " +
                    "This endpoint helps parents assess their child's overall attendance."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved attendance.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Attendance not found for the specified child.",
                    content = @Content)
    })
    @GetMapping("/get-attendance-for-year/{childId}")
    public ResponseEntity<Response> getAttendByGradeStudent(@PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendance.", parentRoleService.getAttendByGradeStudent(childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not Attendance. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all homework for a child",
            description = "Retrieves all homework assignments for a specific child. " +
                    "This endpoint allows parents to see all homework assigned to their child."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all homeworks.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Homework not found for the specified child.",
                    content = @Content)
    })
    @GetMapping("/get-all-hw/{childId}")
    public ResponseEntity<Response> getAllHomework(@PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Homeworks.", parentRoleService.getAllHomework(childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all undone homework for a child",
            description = "Retrieves all undone homework assignments for a specific child. " +
                    "This endpoint helps parents track homework that has not been completed."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all undone homeworks.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Undone homework not found for the specified child.",
                    content = @Content)
    })
    @GetMapping("/get-all-undone-hw/{childId}")
    public ResponseEntity<Response> getAllUndoneHomework(@PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone Homeworks.", parentRoleService.getAllUndoneHomework(childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all homework by subject for a child",
            description = "Retrieves all homework assignments for a specific child filtered by the specified subject. " +
                    "This endpoint allows parents to monitor their child's homework in specific subjects."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all homework by subject.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Homework not found for the specified child and subject.",
                    content = @Content)
    })
    @GetMapping("/get-all-hw-subject/{subjectId}/{childId}")
    public ResponseEntity<Response> getAllHomeworkSubject(@PathVariable Long subjectId,
                                                          @PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Homework by subject.", parentRoleService.getAllHomeworkSubject(subjectId, childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all undone homework by subject for a child",
            description = "Retrieves all undone homework assignments for a specific child filtered by the specified subject. " +
                    "This endpoint helps parents track homework that has not been completed in specific subjects."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all undone homework by subject.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Undone homework not found for the specified child and subject.",
                    content = @Content)
    })
    @GetMapping("/get-all-undone-hw-subject/{subjectId}/{childId}")
    public ResponseEntity<Response> getAllUndoneHomework(@PathVariable Long subjectId,
                                                         @PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got undone Homework by subject.", parentRoleService.getAllUndoneHomework(subjectId, childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get student's schedule",
            description = "Retrieves the schedule for a specific child. " +
                    "This endpoint allows parents to check their child's daily or weekly schedule."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved student's schedule.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Schedule not found for the specified child.",
                    content = @Content)
    })
    @GetMapping("/get-student-schedule/{childId}")
    public ResponseEntity<Response> getStudentSchedule(@PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Schedules.", parentRoleService.getStudentSchedule(childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all reviews for a child",
            description = "Retrieves all reviews for a specific child. " +
                    "This endpoint allows parents to see feedback from teachers about their child's performance."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all reviews.",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Reviews not found for the specified child.",
                    content = @Content)
    })
    @GetMapping("/get-all-review/{childId}")
    public ResponseEntity<Response> getStudentReview(@PathVariable Long childId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Reviews.", parentRoleService.getStudentReview(childId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Delete a student by ID",
            description = "Deletes a specific student from the school by their ID. " +
                    "This endpoint is used to remove a student from the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the student.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Student not found for the specified ID.",
                    content = @Content)
    })
    @DeleteMapping(value = "/delete-user/{childId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long childId) {
        try {
            parentRoleService.leaveSchool(childId);
            return ResponseEntity.ok("Deleted Student successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete. " + exception.getMessage());
        }
    }
}
