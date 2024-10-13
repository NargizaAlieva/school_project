package org.example.school_project.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.example.school_project.dto.*;
import org.example.school_project.service.ClassTeacherService;

@RestController
@RequestMapping(value = "/class-teacher")
@AllArgsConstructor
public class ClassTeacherController {
    private final ClassTeacherService classTeacherService;

    @PostMapping(value = "/create-assignment")
    public ResponseEntity<Response> createAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto assignmentDto = classTeacherService.createAssignment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added Assignment.", assignmentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Assignment is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-assignment")
    public ResponseEntity<Response> updateAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto createAssignment = classTeacherService.updateAssignment(request);
            return ResponseEntity.ok(new Response("Successfully updated Assignment.", createAssignment));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Assignment is not updated. " + exception.getMessage(), null));
        }
    }

    @DeleteMapping(value = "/delete-assignment/{assignmentId}")
    public ResponseEntity<Response> deleteAssignment(@PathVariable Long assignmentId) {
        try {
            return ResponseEntity.ok(new Response("Deleted Assignment successfully", classTeacherService.deleteAssignment(assignmentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/restore-assignment/{assignmentId}")
    public ResponseEntity<Response> restoreAssignment(@PathVariable Long assignmentId) {
        try {
            return ResponseEntity.ok(new Response("Restored Assignment successfully", classTeacherService.restoreAssignment(assignmentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-assignment-to-class-represent")
    public ResponseEntity<Response> getAllAssignmentByAuthor() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Assignment for class representative. ",
                    classTeacherService.getAllAssignmentFromClassRepresent()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-undone-assignment-to-class-represent")
    public ResponseEntity<Response> getAllUndoneAssigment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone Assignment for class representative. ",
                    classTeacherService.getAllUndoneAssFromClassRepresent()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-done-assignment-to-class-represent")
    public ResponseEntity<Response> getAllDoneAssignmentFrom() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all done Assignment for class representative. ",
                    classTeacherService.getAllDoneAssFromClassRepresent()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/mark-done/{assignmentId}")
    public ResponseEntity<Response> markAsDone(@PathVariable Long assignmentId) {
        try {
            AssignmentDto updateAssignment = classTeacherService.markAsDone(assignmentId);
            return ResponseEntity.ok(new Response("Successfully mark as done.", updateAssignment));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to mark as done. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-assignment-from-dean")
    public ResponseEntity<Response> getAllAssignment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Assignment from dean.", classTeacherService.getAllAssFromDean()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-undone-assignment-from-dean")
    public ResponseEntity<Response> getAllUndoneAssignment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone Assignment from dean.", classTeacherService.getAllUndoneAssFromDean()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/choose-grade-represent/{id}")
    public ResponseEntity<Response> updateGrade(@PathVariable Long id) {
        try {
            StudentDto studentDto = classTeacherService.chooseClassRepresentative(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Chosen successfully. ", studentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to choose. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-class-student")
    public ResponseEntity<Response> studentsFromClass() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all class Student from home grades.", classTeacherService.getAllStudentsFromClass()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-review-student/{studentId}")
    public ResponseEntity<Response> getReviewByStudentId(@PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Reviews by Student", classTeacherService.getReviewByStudentId(studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-review-author/{studentId}")
    public ResponseEntity<Response> getReviewByAuthorId(@PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Reviews by author", classTeacherService.getReviewByAuthorId(studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-review-class-represent")
    public ResponseEntity<Response> getReviewByClassRepresent() {
        try {
            classTeacherService.getReviewByClassRepresent();
            return ResponseEntity.ok(new Response("Successfully got all class representatives's Reviews.", classTeacherService.getReviewByClassRepresent()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-review")
    public ResponseEntity<Response> createGrade(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = classTeacherService.createReview(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Review", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Review is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-review")
    public ResponseEntity<Response> updateReview(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = classTeacherService.updateReview(request);
            return ResponseEntity.ok(new Response("Successfully Review updated", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Review is not updated. " + exception.getMessage(), null));
        }
    }

    @DeleteMapping(value = "/delete-review/{reviewId}")
    public ResponseEntity<Response> deleteReview(@PathVariable Long reviewId) {
        try {
            return ResponseEntity.ok(new Response("Deleted Review successfully", classTeacherService.deleteReview(reviewId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/restore-review/{reviewId}")
    public ResponseEntity<Response> restoreReview(@PathVariable Long reviewId) {
        try {
            return ResponseEntity.ok(new Response("Restored Review successfully", classTeacherService.restoreReview(reviewId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-mark")
    public ResponseEntity<Response> createMark(@RequestBody MarkDtoRequest request) {
        try {
            MarkDto markDto = classTeacherService.createMark(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Mark.", markDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Mark is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-mark")
    public ResponseEntity<Response> updateMark(@RequestBody MarkDtoRequest request) {
        try {
            MarkDto markDto = classTeacherService.updateMark(request);
            return ResponseEntity.ok(new Response("Successfully updated Mark.", markDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Mark is not saved. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-quarter-student-mark/{quarter}/{studentId}")
    public ResponseEntity<Response> getAvgMarkByGradeStudentQuarter(@PathVariable Integer quarter,
                                                                    @PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", classTeacherService.getAvgMarkByGradeStudentQuarter(quarter, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-subject-student-mark/{subjectId}/{studentId}")
    public ResponseEntity<Response> getAvgMarkBySubjectGradeStudent(@PathVariable Long subjectId,
                                                                    @PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", classTeacherService.getAvgMarkBySubjectGradeStudent(subjectId, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-student-mark/{studentId}")
    public ResponseEntity<Response> getAvgMarkByGradeStudent(@PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", classTeacherService.getAvgMarkByGradeStudent(studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-quarter-grade-mark/{quarter}/{gradeId}")
    public ResponseEntity<Response> getAvgMarkByGradeQuarter(@PathVariable Integer quarter,
                                                             @PathVariable Long gradeId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", classTeacherService.getAvgMarkByGradeQuarter(quarter, gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-grade-mark/{gradeId}")
    public ResponseEntity<Response> getAvgMarkByGrade(@PathVariable Long gradeId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", classTeacherService.getAvgMarkByGrade(gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-grades-mark")
    public ResponseEntity<Response> getAvgMarks() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", classTeacherService.getAvgMarks()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-attendance")
    public ResponseEntity<Response> createAttendance(@RequestBody AttendanceDtoRequest request) {
        try {
            AttendanceDto attendanceDto = classTeacherService.createAttendance(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Attendance.", attendanceDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Attendance is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-attendance")
    public ResponseEntity<Response> updateAttendance(@RequestBody AttendanceDtoRequest request) {
        try {
            AttendanceDto attendanceDto = classTeacherService.updateAttendance(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully updated Attendance.", attendanceDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Attendance is not updated. " + exception.getMessage(), null));
        }
    }

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

    @GetMapping("/get-grade-attend/{gradeId}")
    public ResponseEntity<Response> getAttendByQuarterGrade(@PathVariable Long gradeId) {
        try {
            classTeacherService.getAttendByGrade(gradeId);
            return ResponseEntity.ok(new Response("Successfully got Attendance.", classTeacherService.getAttendByGrade(gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-grades-attend")
    public ResponseEntity<Response> getAttendGrades() {
        try {
            classTeacherService.getAttendGrades();
            return ResponseEntity.ok(new Response("Successfully got Attendance.", classTeacherService.getAttendGrades()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

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

    @PostMapping(value = "/send-message-to-home-grade-students")
    public ResponseEntity<Response> sendMessageForGradeStudents(@RequestBody MessageDtoRequest request) {
        try {
            classTeacherService.sendMessageForGradeStudents(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Sent successfully. ", null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Failed to sent. " + exception.getMessage(), null));
        }
    }

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
