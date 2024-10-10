package org.example.school_project.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.school_project.dto.*;
import org.example.school_project.service.ClassTeacherService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/class-teacher")
@AllArgsConstructor
@Slf4j
public class ClassTeacherController {
    private final ClassTeacherService classTeacherService;

    @PostMapping(value = "/create-assignment")
    public ResponseEntity<Response> createAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto assignmentDto = classTeacherService.createAssignment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added Assignment", assignmentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Assignment is not saved" + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-assignment")
    public ResponseEntity<Response> updateAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto createAssignment = classTeacherService.updateAssignment(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Assignment successfully updated", createAssignment));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Assignment is not updated. " + exception.getMessage(), null));
        }
    }
    @DeleteMapping(value = "/delete-assignment/{id}")
    public ResponseEntity<Response> deleteAssignment(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Deleted Assignment successfully", classTeacherService.deleteAssignment(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/restore-assignment/{id}")
    public ResponseEntity<Response> restoreAssignment(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Restored Assignment successfully", classTeacherService.restoreAssignment(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-assignment")
    public ResponseEntity<Response> getAllAssignmentByAuthor() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all Assignment", classTeacherService.getAllAssignmentByAuthor()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-undone-assignment")
    public ResponseEntity<Response> getAllUndoneAssigment() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all undone Assignment", classTeacherService.getAllUndoneAssigment()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/mark-done/{id}")
    public ResponseEntity<Response> markAsDone(@PathVariable Long id) {
        try {
            AssignmentDto updateAssignment = classTeacherService.markAsDone(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Mark as done successfully", updateAssignment));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

//    @GetMapping("/get-all-assignment")
//    public ResponseEntity<Response> getAllAssignment() {
//        try {
//            return ResponseEntity.ok(new Response("Successfully got all Assignment", classTeacherService.getAllAssignment()));
//        } catch (Exception exception) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
//        }
//    }
//
//    @GetMapping("/get-all-undone-assignment")
//    public ResponseEntity<Response> getAllUndoneAssignment() {
//        try {
//            return ResponseEntity.ok(new Response("Successfully got all undone Assignment", classTeacherService.getAllUndoneAssignment()));
//        } catch (Exception exception) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
//        }
//    }
//
    @GetMapping("/get-all-done-assignment-from")
    public ResponseEntity<Response> getAllDoneAssignmentFrom() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all done assignment", classTeacherService.getAllDoneAssFromClassRepresent()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/choose-grade-represent/{id}")
    public ResponseEntity<Response> updateGrade(@PathVariable Long id) {
        try {
            StudentDto studentDto = classTeacherService.chooseClassRepresentative(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Chosen successfully", studentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    //Working
    @GetMapping("/get-all-class-student")
    public ResponseEntity<Response> studentsFromClass() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all class student", classTeacherService.studentsFromClass()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    //Working
    @GetMapping("/get-review-student/{id}")
    public ResponseEntity<Response> getReviewByStudentId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Reviews by Student", classTeacherService.getReviewByStudentId(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    //Working
    @GetMapping("/get-review-author/{id}")
    public ResponseEntity<Response> getReviewByAuthorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Reviews by author", classTeacherService.getReviewByAuthorId(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    //Working
    @GetMapping("/get-review-class-represent")
    public ResponseEntity<Response> getReviewByClassRepresent() {
        try {
            classTeacherService.getReviewByClassRepresent();
            return ResponseEntity.ok(new Response("Successfully got all class representatives's Reviews", classTeacherService.getReviewByClassRepresent()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    //Working
    @PostMapping(value = "/create-review")
    public ResponseEntity<Response> createGrade(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = classTeacherService.createReview(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Review", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Review is not saved. " + exception.getMessage(), null));
        }
    }
    //Working
    @PutMapping(value = "/update-review")
    public ResponseEntity<Response> updateReview(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = classTeacherService.updateReview(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully Review updated", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Review is not saved. " + exception.getMessage(), null));
        }
    }
    @DeleteMapping(value = "/delete-review/{id}")
    public ResponseEntity<Response> deleteReview(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Deleted Review successfully", classTeacherService.deleteReview(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/restore-review/{id}")
    public ResponseEntity<Response> restoreReview(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Restored Review successfully", classTeacherService.restoreReview(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-mark")
    public ResponseEntity<Response> createMark(@RequestBody MarkDtoRequest request) {
        try {
            MarkDto markDto = classTeacherService.createMark(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created ", markDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved. " + exception.getMessage(), null));
        }
    }
    //Working
    @PutMapping(value = "/update-mark")
    public ResponseEntity<Response> updateMark(@RequestBody MarkDtoRequest request) {
        try {
            MarkDto markDto = classTeacherService.updateMark(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully updated Mark", markDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Mark is not saved. " + exception.getMessage(), null));
        }
    }

    //Working
    @PostMapping(value = "/create-attendance")
    public ResponseEntity<Response> createAttendance(@RequestBody AttendanceDtoRequest request) {
        try {
            AttendanceDto attendanceDto = classTeacherService.createAttendance(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Attendance", attendanceDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Attendance is not saved. " + exception.getMessage(), null));
        }
    }
    //Working
    @PutMapping(value = "/update-attendance")
    public ResponseEntity<Response> updateAttendance(@RequestBody AttendanceDtoRequest request) {
        try {
            AttendanceDto attendanceDto = classTeacherService.updateAttendance(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully updated Attendance", attendanceDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Attendance is not saved. " + exception.getMessage(), null));
        }
    }
}
