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
import org.example.school_project.service.StudentRoleService;

@RestController
@RequestMapping(value = "/student")
@AllArgsConstructor
@Tag(name = "Student Management", description = "Operations related to student role.")
public class StudentController {
    private final StudentRoleService studentRoleService;

    @Operation(
            summary = "Get all students' marks",
            description = "Retrieves all students' marks."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all Students Marks", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Marks not found", content = @Content)
    })
    @GetMapping("/get-all-mark")
    public ResponseEntity<Response> getAllMark() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Students Marks.", studentRoleService.getAllMark()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all marks by quarter",
            description = "Retrieves all marks for the specified quarter."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Marks", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Marks not found", content = @Content)
    })
    @GetMapping("/get-all-mark-quarter/{quarter}")
    public ResponseEntity<Response> getAllMarkBySubjectQuarter(@PathVariable Integer quarter) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks. ", studentRoleService.getAvgMarkByGradeStudentQuarter(quarter)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all marks by subject",
            description = "Retrieves all marks for the specified subject."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Marks", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Marks not found", content = @Content)
    })
    @GetMapping("/get-all-mark-subject/{subjectId}")
    public ResponseEntity<Response> getAllMarkBySubjectQuarter(@PathVariable Long subjectId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks. ", studentRoleService.getAvgMarkBySubjectGradeStudent(subjectId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get average marks for the year",
            description = "Retrieves average marks for all students for the year."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got average marks", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Marks not found", content = @Content)
    })
    @GetMapping("/get-all-mark-for-year")
    public ResponseEntity<Response> getAvgMarkByGradeStudent() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks ", studentRoleService.getAvgMarkByGradeStudent()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Marks not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get attendance by quarter",
            description = "Retrieves attendance for the specified quarter."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendance", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Attendance not found", content = @Content)
    })
    @GetMapping("/get-attendance-quarter/{quarter}")
    public ResponseEntity<Response> getAttendByQuarterGradeStudent(@PathVariable Integer quarter) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendance.", studentRoleService.getAttendByQuarterGradeStudent(quarter)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Attendance not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get attendance by subject",
            description = "Retrieves attendance for the specified subject."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendance", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Attendance not found", content = @Content)
    })
    @GetMapping("/get-attendance-subject/{subjectId}")
    public ResponseEntity<Response> getAttendBySubjectGradeStudent(@PathVariable Long subjectId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendance.", studentRoleService.getAttendBySubjectGradeStudent(subjectId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Attendance not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get attendance for the year",
            description = "Retrieves attendance records for all students for the year."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Attendance", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Attendance not found", content = @Content)
    })
    @GetMapping("/get-attendance-for-year")
    public ResponseEntity<Response> getAttendByGradeStudent() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendance.", studentRoleService.getAttendByGradeStudent()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Attendance not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Send homework",
            description = "Sends homework details to the specified endpoint."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully sent Homework", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "Homework is not saved", content = @Content)
    })
    @PostMapping(value = "/send-hw")
    public ResponseEntity<Response> sendHomework(@RequestBody HomeworkDtoRequest request) {
        try {
            HomeworkDto homeworkDto = studentRoleService.sendHomework(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully sent Homework.", homeworkDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Homework is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all homework",
            description = "Retrieves all homework records."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all Homeworks", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Homework is not found", content = @Content)
    })
    @GetMapping("/get-all-hw")
    public ResponseEntity<Response> getAllHomework() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Homeworks.", studentRoleService.getAllHomework()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Homework is not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all undone homework",
            description = "Retrieves all undone homework assigned to students."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all undone Homeworks", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Homework is not found", content = @Content)
    })
    @GetMapping("/get-all-undone-hw")
    public ResponseEntity<Response> getAllUndoneHomework() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone Homeworks.", studentRoleService.getAllUndoneHomework()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Homework is not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all homework by subject",
            description = "Retrieves all homework assigned by a specific subject."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all Homeworks by subject", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Homework is not found", content = @Content)
    })
    @GetMapping("/get-all-hw-subject/{subjectId}")
    public ResponseEntity<Response> getAllHomeworkSubject(@PathVariable Long subjectId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Homeworks by subject.", studentRoleService.getAllHomeworkSubject(subjectId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Homework is not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all undone homework by subject",
            description = "Retrieves all undone homework assigned for a specific subject."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got all undone Homeworks by subject", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Homework is not found", content = @Content)
    })
    @GetMapping("/get-all-undone-hw-subject/{subjectId}")
    public ResponseEntity<Response> getAllUndoneHomework(@PathVariable Long subjectId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone Homeworks by subject", studentRoleService.getAllUndoneHomework(subjectId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Homework is not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get student schedule",
            description = "Retrieves the schedule for a specific student."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Schedules", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Schedules not found", content = @Content)
    })
    @GetMapping("/get-student-schedule")
    public ResponseEntity<Response> getStudentSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Schedules.", studentRoleService.getStudentSchedule()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Schedules is not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all lessons by grade",
            description = "Retrieves all lessons assigned to a specific grade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Lessons", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Lessons topic not found", content = @Content)
    })
    @GetMapping("/get-all-lesson-grade")
    public ResponseEntity<Response> getAllLessonByGrade() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Lessons.", studentRoleService.getAllLessonByGrade()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Lessons topic is not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get lessons topics",
            description = "Retrieves all lesson topics available."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Lessons topics", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Lessons topic not found", content = @Content)
    })
    @GetMapping("/get-all-lesson-topic")
    public ResponseEntity<Response> getLessonsTopics() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Lessons topic.", studentRoleService.getLessonsTopics()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Lessons topic is not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get lesson topics by subject",
            description = "Retrieves lesson topics for a specific subject."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got Lesson topics", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Lesson topics not found", content = @Content)
    })
    @GetMapping("/get-all-lesson-topic/{subjectId}")
    public ResponseEntity<Response> getLessonsTopics(@PathVariable Long subjectId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Lesson topics.", studentRoleService.getLessonsTopicsBySubject(subjectId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Lesson topics is not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get student subjects",
            description = "Retrieves the list of subjects for a specific student."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got student subjects", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Subjects not found", content = @Content)
    })
    @GetMapping("/get-student-subject")
    public ResponseEntity<Response> getSubjectList() {
        try {
            return ResponseEntity.ok(new Response("Successfully got student Subjects", studentRoleService.getStudentSubject()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Subjects is not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get classmates",
            description = "Retrieves a list of classmates for a specific student."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got classmates", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Classmates not found", content = @Content)
    })
    @GetMapping("/get-classmates")
    public ResponseEntity<Response> getClassmates() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Classmates.", studentRoleService.getClassmates()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Classmates is not found. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get student duty list",
            description = "Retrieves the list of duties for a specific student."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got student duty list", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Duty list not found", content = @Content)
    })
    @GetMapping("/get-duty-list")
    public ResponseEntity<Response> getStudentDutyList() {
        try {
            return ResponseEntity.ok(new Response("Successfully got student Duty List.", studentRoleService.getStudentDutyList()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Duty list is not found. " + exception.getMessage(), null));
        }
    }
}
