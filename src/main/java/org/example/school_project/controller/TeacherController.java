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
import org.example.school_project.service.TeacherService;

@RestController
@RequestMapping(value = "/teacher")
@AllArgsConstructor
@Tag(name = "Teacher Management", description = "Operations related to teacher role.")
public class TeacherController {
    private final TeacherService teacherService;

    @Operation(
            summary = "Get teacher schedule",
            description = "Retrieves the schedule for a specific teacher."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got schedules", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Schedules not found", content = @Content)
    })
    @GetMapping("/get-schedule")
    public ResponseEntity<Response> getTeacherSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Schedules.", teacherService.getTeacherSchedule()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get teacher subjects",
            description = "Retrieves the subjects taught by a specific teacher."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got teacher's subjects", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Subjects not found", content = @Content)
    })
    @GetMapping("/get-teacher-subjects")
    public ResponseEntity<Response> getTeacherSubjectList() {
        try {
            return ResponseEntity.ok(new Response("Successfully got teacher's subjects.", teacherService.getTeacherSubjectList()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Create a review",
            description = "Creates a review for a specific teacher."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created review", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Review is not saved", content = @Content)
    })
    @PostMapping(value = "/create-review")
    public ResponseEntity<Response> createReview(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = teacherService.createReview(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Review.", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Review is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update a review",
            description = "Updates an existing review for a specific teacher."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated review", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Review is not updated", content = @Content)
    })
    @PutMapping(value = "/update-review")
    public ResponseEntity<Response> updateReview(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = teacherService.updateReview(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully updated Review.", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Review is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Create a lesson",
            description = "Creates a new lesson for a specific subject."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created lesson", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Lesson is not saved", content = @Content)
    })
    @PostMapping(value = "/create-lesson")
    public ResponseEntity<Response> createLesson(@RequestBody LessonDtoRequest request) {
        try {
            LessonDto lessonDto = teacherService.createLesson(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Lesson.", lessonDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Lesson is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update a lesson",
            description = "Updates an existing lesson for a specific subject."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated lesson", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Lesson is not updated", content = @Content)
    })
    @PutMapping(value = "/update-lesson")
    public ResponseEntity<Response> updateLesson(@RequestBody LessonDtoRequest request) {
        try {
            LessonDto lessonDto = teacherService.updateLesson(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully updated Lesson.", lessonDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Lesson is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Create a mark",
            description = "Creates a new mark for a specific student."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created mark", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Mark is not saved", content = @Content)
    })
    @PostMapping(value = "/create-mark")
    public ResponseEntity<Response> createMark(@RequestBody MarkDtoRequest request) {
        try {
            MarkDto markDto = teacherService.createMark(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Mark.", markDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Mark is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update a mark",
            description = "Updates an existing mark for a specific student."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated mark", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Mark is not updated", content = @Content)
    })
    @PutMapping(value = "/update-mark")
    public ResponseEntity<Response> updateMark(@RequestBody MarkDtoRequest request) {
        try {
            MarkDto markDto = teacherService.updateMark(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully updated Mark.", markDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Mark is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Create attendance",
            description = "Creates a new attendance record for a specific student."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created attendance", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Attendance is not saved", content = @Content)
    })
    @PostMapping(value = "/create-attendance")
    public ResponseEntity<Response> createAttendance(@RequestBody AttendanceDtoRequest request) {
        try {
            AttendanceDto attendanceDto = teacherService.createAttendance(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Attendance.", attendanceDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Attendance is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update attendance",
            description = "Updates an existing attendance record for a specific student."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated attendance", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Attendance is not updated", content = @Content)
    })
    @PutMapping(value = "/update-attendance")
    public ResponseEntity<Response> updateAttendance(@RequestBody AttendanceDtoRequest request) {
        try {
            AttendanceDto attendanceDto = teacherService.updateAttendance(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully updated Attendance.", attendanceDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Attendance is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get marks of teacher",
            description = "Retrieves marks assigned by the teacher."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got marks", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Marks not found", content = @Content)
    })
    @GetMapping("/get-teacher-mark")
    public ResponseEntity<Response> getMarkTeacher() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", teacherService.getMarkTeacher()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get marks of teacher by grade",
            description = "Retrieves marks assigned by the teacher for a specific grade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got marks", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Marks not found", content = @Content)
    })
    @GetMapping("/get-teacher-mark/{id}")
    public ResponseEntity<Response> getMarkTeacherByGrade(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", teacherService.getMarkTeacherByGrade(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get average mark by subject, grade, and quarter",
            description = "Retrieves the average mark for a specific subject in a specific grade for a specific quarter."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Marks", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Marks not found", content = @Content)
    })
    @GetMapping("/get-teacher-subjects-mark/{gradeId}/{subjectId}/{quarter}")
    public ResponseEntity<Response> getAvgMarkBySubjectGradeQuarter(@PathVariable Long gradeId,
                                                                    @PathVariable Long subjectId,
                                                                    @PathVariable Integer quarter) {
        try {
            teacherService.getAvgMarkBySubjectGradeQuarter(gradeId, quarter, subjectId);
            return ResponseEntity.ok(new Response("Successfully got Marks.", teacherService.getAvgMarkBySubjectGradeQuarter(gradeId, quarter, subjectId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get average mark by subject and grade",
            description = "Retrieves the average mark for a specific subject in a specific grade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Marks", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Marks not found", content = @Content)
    })
    @GetMapping("/get-teacher-subjects-mark/{gradeId}/{subjectId}")
    public ResponseEntity<Response> getAvgMarkBySubjectGrade(@PathVariable Long gradeId,
                                                             @PathVariable Long subjectId) {
        try {
            teacherService.getAvgMarkBySubjectGrade(gradeId, subjectId);
            return ResponseEntity.ok(new Response("Successfully got Mark.s", teacherService.getAvgMarkBySubjectGrade(gradeId, subjectId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-teacher-subjects-mark/{subjectId}")
    public ResponseEntity<Response> getAvgMarkBySubjectGrade(@PathVariable Long subjectId) {
        try {
            teacherService.getAvgMarkBySubject(subjectId);
            return ResponseEntity.ok(new Response("Successfully got Marks.", teacherService.getAvgMarkBySubject(subjectId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get average mark by subject",
            description = "Retrieves the average mark for a specific subject."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Marks", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Marks not found", content = @Content)
    })
    @GetMapping("/get-all-grade")
    public ResponseEntity<Response> getAllGrade() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Grades.", teacherService.getAllGrade()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all teacher grades",
            description = "Retrieves all grades that the teacher is associated with."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Grades", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Grades not found", content = @Content)
    })
    @GetMapping("/get-all-teacher-grade")
    public ResponseEntity<Response> getTeacherGrade() {
        try {
            teacherService.getTeacherGrade();
            return ResponseEntity.ok(new Response("Successfully got Grades.", teacherService.getTeacherGrade()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all students by grade",
            description = "Retrieves all students from a specified grade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Students from grade", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Students not found", content = @Content)
    })
    @GetMapping("/get-all-student-grade/{gradeId}")
    public ResponseEntity<Response> getAllStudentByGrade(@PathVariable Long gradeId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Students from grade.", teacherService.getAllStudentByGrade(gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all homework by teacher",
            description = "Retrieves all homework assigned by the teacher."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all Homework", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Homework not found", content = @Content)
    })
    @GetMapping("/get-all-hw-teacher")
    public ResponseEntity<Response> getAllHwByTeacher() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Homework.", teacherService.getAllHwByTeacher()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all unchecked homework",
            description = "Retrieves all unchecked homework assigned by the teacher."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all unchecked Homework", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Unchecked Homework not found", content = @Content)
    })
    @GetMapping("/get-all-unchecked-hw-teacher")
    public ResponseEntity<Response> getUncheckedHw() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all unchecked Homework.", teacherService.getUncheckedHw()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all unchecked homework by lesson",
            description = "Retrieves all unchecked homework assigned by the teacher for a specific lesson."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all unchecked Homework by lesson", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Unchecked Homework for lesson not found", content = @Content)
    })
    @GetMapping("/get-all-unchecked-hw-lesson/{lessonId}")
    public ResponseEntity<Response> getAllHwByTeacher(@PathVariable Long lessonId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all unchecked Homework by lesson.", teacherService.getAllUncheckedHwByTeacherLesson(lessonId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Mark homework",
            description = "Updates the status of the specified homework with a mark and review."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated Homework", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Homework not updated", content = @Content)
    })
    @PutMapping(value = "/mark-hw/{hwId}/{mark}/{hwReview}")
    public ResponseEntity<Response> markHw(@PathVariable Long hwId,
                                           @PathVariable Integer mark,
                                           @PathVariable String hwReview) {
        try {
            HomeworkDto homeworkDto = teacherService.markHw(hwId, mark, hwReview);
            return ResponseEntity.ok(new Response("Successfully updated Homework. ", homeworkDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Homework is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Send message to parent by student ID",
            description = "Sends a message to the parent of the specified student."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sent successfully", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content)
    })
    @PostMapping(value = "/send-to-parent-by-student/{studentId}")
    public ResponseEntity<Response> sendToParentByStudentId(@PathVariable Long studentId,
                                                            @RequestBody MessageDtoRequest request) {
        try {
            teacherService.sendToParentByStudentId(studentId, request);
            return ResponseEntity.ok(new Response("Sent successfully.", null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }
}
