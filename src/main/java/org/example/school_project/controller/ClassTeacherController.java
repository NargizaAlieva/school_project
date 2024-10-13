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
import org.example.school_project.service.ClassTeacherService;

@RestController
@RequestMapping(value = "/class-teacher")
@AllArgsConstructor
@Tag(name = "Class Teacher Management", description = "Operations related to class teacher role.")
public class ClassTeacherController {
    private final ClassTeacherService classTeacherService;

    @Operation(
            summary = "Create a new assignment",
            description = "Adds a new assignment and returns the created assignment object."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Assignment successfully added", content = @Content(schema = @Schema(implementation = AssignmentDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
    })
    @PostMapping(value = "/create-assignment")
    public ResponseEntity<Response> createAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto assignmentDto = classTeacherService.createAssignment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added Assignment.", assignmentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Assignment is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update an existing assignment",
            description = "Updates an existing assignment and returns the updated assignment object."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assignment successfully updated", content = @Content(schema = @Schema(implementation = AssignmentDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content)
    })
    @PutMapping(value = "/update-assignment")
    public ResponseEntity<Response> updateAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto createAssignment = classTeacherService.updateAssignment(request);
            return ResponseEntity.ok(new Response("Successfully updated Assignment.", createAssignment));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Assignment is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Delete an assignment",
            description = "Deletes an assignment by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assignment successfully deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Assignment not found", content = @Content)
    })
    @DeleteMapping(value = "/delete-assignment/{assignmentId}")
    public ResponseEntity<Response> deleteAssignment(@PathVariable Long assignmentId) {
        try {
            return ResponseEntity.ok(new Response("Deleted Assignment successfully", classTeacherService.deleteAssignment(assignmentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Restore an assignment",
            description = "Restores a previously deleted assignment by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assignment successfully restored", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Assignment not found", content = @Content)
    })
    @PutMapping(value = "/restore-assignment/{assignmentId}")
    public ResponseEntity<Response> restoreAssignment(@PathVariable Long assignmentId) {
        try {
            return ResponseEntity.ok(new Response("Restored Assignment successfully", classTeacherService.restoreAssignment(assignmentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all assignments for class representative",
            description = "Retrieves all assignments associated with the class representative."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all assignments", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value = "/get-all-assignment-to-class-represent")
    public ResponseEntity<Response> getAllAssignmentByAuthor() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Assignment for class representative. ",
                    classTeacherService.getAllAssignmentFromClassRepresent()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all undone assignments for class representative",
            description = "Retrieves all undone assignments associated with the class representative."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all undone assignments", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value = "/get-all-undone-assignment-to-class-represent")
    public ResponseEntity<Response> getAllUndoneAssigment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone Assignment for class representative. ",
                    classTeacherService.getAllUndoneAssFromClassRepresent()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all done assignments for class representative",
            description = "Retrieves all assignments that have been marked as done by the class representative."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all done assignments", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/get-all-done-assignment-to-class-represent")
    public ResponseEntity<Response> getAllDoneAssignmentFrom() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all done Assignment for class representative. ",
                    classTeacherService.getAllDoneAssFromClassRepresent()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Mark an assignment as done",
            description = "Marks an assignment as done by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully marked as done", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Assignment not found", content = @Content)
    })
    @PutMapping(value = "/mark-done/{assignmentId}")
    public ResponseEntity<Response> markAsDone(@PathVariable Long assignmentId) {
        try {
            AssignmentDto updateAssignment = classTeacherService.markAsDone(assignmentId);
            return ResponseEntity.ok(new Response("Successfully mark as done.", updateAssignment));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to mark as done. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all assignments from dean",
            description = "Retrieves all assignments that have been issued by the dean."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all assignments from dean", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/get-all-assignment-from-dean")
    public ResponseEntity<Response> getAllAssignment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Assignment from dean.", classTeacherService.getAllAssFromDean()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all undone assignments from dean",
            description = "Retrieves all assignments that have not been completed from the dean."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all undone assignments", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/get-all-undone-assignment-from-dean")
    public ResponseEntity<Response> getAllUndoneAssignment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone Assignment from dean.", classTeacherService.getAllUndoneAssFromDean()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Choose a class representative",
            description = "Updates the chosen class representative by student ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chosen successfully", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content)
    })
    @PutMapping(value = "/choose-grade-represent/{id}")
    public ResponseEntity<Response> updateGrade(@PathVariable Long id) {
        try {
            StudentDto studentDto = classTeacherService.chooseClassRepresentative(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Chosen successfully. ", studentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to choose. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all students from class",
            description = "Retrieves all students from the class."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all class students", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/get-all-class-student")
    public ResponseEntity<Response> studentsFromClass() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all class Student from home grades.", classTeacherService.getAllStudentsFromClass()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get reviews by student ID",
            description = "Retrieves all reviews associated with a given student ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved reviews by student ID", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/get-review-student/{studentId}")
    public ResponseEntity<Response> getReviewByStudentId(@PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Reviews by Student", classTeacherService.getReviewByStudentId(studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get reviews by author ID",
            description = "Retrieves all reviews associated with a given author ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved reviews by author ID", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/get-review-author/{studentId}")
    public ResponseEntity<Response> getReviewByAuthorId(@PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Reviews by author", classTeacherService.getReviewByAuthorId(studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get reviews by class representatives",
            description = "Retrieves all reviews submitted by class representatives."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all class representatives' reviews", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/get-review-class-represent")
    public ResponseEntity<Response> getReviewByClassRepresent() {
        try {
            classTeacherService.getReviewByClassRepresent();
            return ResponseEntity.ok(new Response("Successfully got all class representatives's Reviews.", classTeacherService.getReviewByClassRepresent()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Create a new review",
            description = "Creates a new review based on the provided review data."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created Review", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Invalid review data", content = @Content)
    })
    @PostMapping(value = "/create-review")
    public ResponseEntity<Response> createGrade(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = classTeacherService.createReview(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Review", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Review is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update an existing review",
            description = "Updates an existing review based on the provided review data."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated Review", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Invalid review data", content = @Content)
    })
    @PutMapping(value = "/update-review")
    public ResponseEntity<Response> updateReview(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = classTeacherService.updateReview(request);
            return ResponseEntity.ok(new Response("Successfully Review updated", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Review is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Delete a review by ID",
            description = "Deletes a review based on the given review ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted Review successfully", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Review not found", content = @Content)
    })
    @DeleteMapping(value = "/delete-review/{reviewId}")
    public ResponseEntity<Response> deleteReview(@PathVariable Long reviewId) {
        try {
            return ResponseEntity.ok(new Response("Deleted Review successfully", classTeacherService.deleteReview(reviewId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Restore a review by ID",
            description = "Restores a review based on the given review ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restored Review successfully", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Review not found", content = @Content)
    })
    @PutMapping(value = "/restore-review/{reviewId}")
    public ResponseEntity<Response> restoreReview(@PathVariable Long reviewId) {
        try {
            return ResponseEntity.ok(new Response("Restored Review successfully", classTeacherService.restoreReview(reviewId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Create a new mark",
            description = "Creates a new mark based on the provided mark data."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created Mark", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Invalid mark data", content = @Content)
    })
    @PostMapping(value = "/create-mark")
    public ResponseEntity<Response> createMark(@RequestBody MarkDtoRequest request) {
        try {
            MarkDto markDto = classTeacherService.createMark(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Mark.", markDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Mark is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update an existing mark",
            description = "Updates an existing mark based on the provided mark data."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated Mark", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Invalid mark data", content = @Content)
    })
    @PutMapping(value = "/update-mark")
    public ResponseEntity<Response> updateMark(@RequestBody MarkDtoRequest request) {
        try {
            MarkDto markDto = classTeacherService.updateMark(request);
            return ResponseEntity.ok(new Response("Successfully updated Mark.", markDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Mark is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get average mark by quarter for a student",
            description = "Retrieves the average mark for a specific student in a given quarter."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Marks", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find the marks", content = @Content)
    })
    @GetMapping("/get-quarter-student-mark/{quarter}/{studentId}")
    public ResponseEntity<Response> getAvgMarkByGradeStudentQuarter(@PathVariable Integer quarter,
                                                                    @PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", classTeacherService.getAvgMarkByGradeStudentQuarter(quarter, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get average mark by subject for a student",
            description = "Retrieves the average mark for a specific student in a given subject."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Marks", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find the marks", content = @Content)
    })
    @GetMapping("/get-subject-student-mark/{subjectId}/{studentId}")
    public ResponseEntity<Response> getAvgMarkBySubjectGradeStudent(@PathVariable Long subjectId,
                                                                    @PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", classTeacherService.getAvgMarkBySubjectGradeStudent(subjectId, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get average mark for a student",
            description = "Retrieves the average mark for a specific student."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Marks", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find the marks", content = @Content)
    })
    @GetMapping("/get-student-mark/{studentId}")
    public ResponseEntity<Response> getAvgMarkByGradeStudent(@PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", classTeacherService.getAvgMarkByGradeStudent(studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get average mark by quarter for a grade",
            description = "Retrieves the average mark for a specific grade in a given quarter."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Marks", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find the marks", content = @Content)
    })
    @GetMapping("/get-quarter-grade-mark/{quarter}/{gradeId}")
    public ResponseEntity<Response> getAvgMarkByGradeQuarter(@PathVariable Integer quarter,
                                                             @PathVariable Long gradeId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", classTeacherService.getAvgMarkByGradeQuarter(quarter, gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get average mark for a grade",
            description = "Retrieves the average mark for a specific grade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Marks", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find the marks", content = @Content)
    })
    @GetMapping("/get-grade-mark/{gradeId}")
    public ResponseEntity<Response> getAvgMarkByGrade(@PathVariable Long gradeId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", classTeacherService.getAvgMarkByGrade(gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get average marks for all grades",
            description = "Retrieves the average marks for all grades."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Marks", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find the marks", content = @Content)
    })
    @GetMapping("/get-grades-mark")
    public ResponseEntity<Response> getAvgMarks() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", classTeacherService.getAvgMarks()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Create attendance record",
            description = "Creates a new attendance record for a student."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created Attendance", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Attendance is not saved", content = @Content)
    })
    @PostMapping(value = "/create-attendance")
    public ResponseEntity<Response> createAttendance(@RequestBody AttendanceDtoRequest request) {
        try {
            AttendanceDto attendanceDto = classTeacherService.createAttendance(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Attendance.", attendanceDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Attendance is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update attendance record",
            description = "Updates an existing attendance record."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated Attendance", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Attendance is not updated", content = @Content)
    })
    @PutMapping(value = "/update-attendance")
    public ResponseEntity<Response> updateAttendance(@RequestBody AttendanceDtoRequest request) {
        try {
            AttendanceDto attendanceDto = classTeacherService.updateAttendance(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully updated Attendance.", attendanceDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Attendance is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get attendance by quarter for a student in a grade",
            description = "Retrieves attendance records for a specific student in a specific grade for a given quarter."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendance", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find attendance", content = @Content)
    })
    @GetMapping("/get-quarter-grade-student-attend/{quarter}/{gradeId}/{studentId}")
    public ResponseEntity<Response> getAttendByQuarterGradeStudent(@PathVariable Integer quarter,
                                                                   @PathVariable Long gradeId,
                                                                   @PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendance.", classTeacherService.getAttendByQuarterGradeStudent(quarter, gradeId, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get attendance by subject for a student in a grade",
            description = "Retrieves attendance records for a specific student in a specific grade for a given subject."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendance", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find attendance", content = @Content)
    })
    @GetMapping("/get-subject-grade-student-attend/{subjectId}/{gradeId}/{studentId}")
    public ResponseEntity<Response> getAttendByQuarterGradeStudent(@PathVariable Long subjectId,
                                                                   @PathVariable Long gradeId,
                                                                   @PathVariable Long studentId) {
        try {
            classTeacherService.getAttendBySubjectGradeStudent(subjectId, gradeId, studentId);
            return ResponseEntity.ok(new Response("Successfully got Attendance.", classTeacherService.getAttendBySubjectGradeStudent(subjectId, gradeId, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get attendance for a student in a grade",
            description = "Retrieves attendance records for a specific student in a specific grade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendance", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find attendance", content = @Content)
    })
    @GetMapping("/get-grade-student-attend/{gradeId}/{studentId}")
    public ResponseEntity<Response> getAttendByGradeStudent(@PathVariable Long gradeId,
                                                            @PathVariable Long studentId) {
        try {
            classTeacherService.getAttendByGradeStudent(gradeId, studentId);
            return ResponseEntity.ok(new Response("Successfully got Attendance.", classTeacherService.getAttendByGradeStudent(gradeId, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get attendance by quarter for a grade",
            description = "Retrieves attendance records for a specific grade for a given quarter."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendance", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find attendance", content = @Content)
    })
    @GetMapping("/get-quarter-grade-attend/{quarter}/{gradeId}")
    public ResponseEntity<Response> getAttendByQuarterGrade(@PathVariable Integer quarter,
                                                            @PathVariable Long gradeId) {
        try {
            classTeacherService.getAttendByQuarterGrade(quarter, gradeId);
            return ResponseEntity.ok(new Response("Successfully got Attendance.", classTeacherService.getAttendByQuarterGrade(quarter, gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get attendance for a grade",
            description = "Retrieves attendance records for a specific grade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendance", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find attendance", content = @Content)
    })
    @GetMapping("/get-grade-attend/{gradeId}")
    public ResponseEntity<Response> getAttendByQuarterGrade(@PathVariable Long gradeId) {
        try {
            classTeacherService.getAttendByGrade(gradeId);
            return ResponseEntity.ok(new Response("Successfully got Attendance.", classTeacherService.getAttendByGrade(gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get attendance records for all grades",
            description = "Retrieves attendance records for all grades."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendance", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Couldn't find attendance", content = @Content)
    })
    @GetMapping("/get-grades-attend")
    public ResponseEntity<Response> getAttendGrades() {
        try {
            classTeacherService.getAttendGrades();
            return ResponseEntity.ok(new Response("Successfully got Attendance.", classTeacherService.getAttendGrades()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Send message to parent by student ID",
            description = "Sends a message to the parent of a specific student."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sent successfully", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Failed to send message", content = @Content)
    })
    @PostMapping(value = "/send-to-parent-by-student/{studentId}")
    public ResponseEntity<Response> sendToParentByStudentId(@PathVariable Long studentId,
                                                            @RequestBody MessageDtoRequest request) {
        try {
            classTeacherService.sendToParentByStudentId(studentId, request);
            return ResponseEntity.ok(new Response("Sent successfully.", null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Failed to sent. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Send message to home grade students by grade",
            description = "Sends a message to all students in the home grade based on the student ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sent successfully", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Failed to send message", content = @Content)
    })
    @PostMapping(value = "/send-message-to-home-grade-students-by-grade/{studentId}")
    public ResponseEntity<Response> sendMessageForGradeStudentsByGrade(@PathVariable Long studentId,
                                                                       @RequestBody MessageDtoRequest request) {
        try {
            classTeacherService.sendMessageForGradeStudentsByGrade(studentId, request);
            return ResponseEntity.ok(new Response("Sent successfully.", null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to sent. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Send message to home grade parents by grade",
            description = "Sends a message to all parents of students in the home grade based on the student ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sent successfully", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Failed to send message", content = @Content)
    })
    @PostMapping(value = "/send-message-to-home-grade-parents-by-grade/{studentId}")
    public ResponseEntity<Response> sendMessageForGradeParentsByGrade(@PathVariable Long studentId,
                                                                @RequestBody MessageDtoRequest request) {
        try {
            classTeacherService.sendMessageForGradeParentsByGrade(studentId, request);
            return ResponseEntity.ok(new Response("Sent successfully. ", null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to sent. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Send message to all home grade students",
            description = "Sends a message to all students in the home grade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sent successfully", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Failed to send message", content = @Content)
    })
    @PostMapping(value = "/send-message-to-home-grade-students")
    public ResponseEntity<Response> sendMessageForGradeStudents(@RequestBody MessageDtoRequest request) {
        try {
            classTeacherService.sendMessageForGradeStudents(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Sent successfully. ", null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Failed to sent. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Send message to home grade parent",
            description = "Sends a message to the parent of a student in the home grade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sent successfully", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "500", description = "Failed to send message", content = @Content)
    })
    @PostMapping(value = "/send-message-to-home-grade-parent")
    public ResponseEntity<Response> sendMessageForGradeParent(@RequestBody MessageDtoRequest request) {
        try {
            classTeacherService.sendMessageForGradeParent(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Sent successfully. ", null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Failed to sent. " + exception.getMessage(), null));
        }
    }
}
