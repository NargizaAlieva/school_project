package org.example.school_project.controller;

import lombok.AllArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.service.TeacherService;
import org.example.school_project.service.impl.TeacherServiceImpl;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/teacher")
@AllArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    //Working
    @GetMapping("/get-schedule")
    public ResponseEntity<Response> getScheduleById() {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", teacherService.getTeacherSchedule()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    //Working
    @PostMapping(value = "/create-review")
    public ResponseEntity<Response> createReview(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = teacherService.createReview(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created ", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved " + exception.getMessage(), null));
        }
    }
    //Working
    @PutMapping(value = "/update-review")
    public ResponseEntity<Response> updateReview(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = teacherService.createReview(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created ", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved " + exception.getMessage(), null));
        }
    }
    //Working
    @PostMapping(value = "/create-lesson")
    public ResponseEntity<Response> createLesson(@RequestBody LessonDtoRequest request) {
        try {
            LessonDto lessonDto = teacherService.createLesson(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created ", lessonDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved " + exception.getMessage(), null));
        }
    }
    //Working
    @PutMapping(value = "/update-lesson")
    public ResponseEntity<Response> updateLesson(@RequestBody LessonDtoRequest request) {
        try {
            LessonDto lessonDto = teacherService.createLesson(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully updated ", lessonDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved " + exception.getMessage(), null));
        }
    }

    //Working
    @PostMapping(value = "/create-mark")
    public ResponseEntity<Response> createMark(@RequestBody MarkDtoRequest request) {
        try {
            MarkDto markDto = teacherService.createMark(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created ", markDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved " + exception.getMessage(), null));
        }
    }
    //Working
    @PutMapping(value = "/update-mark")
    public ResponseEntity<Response> updateMark(@RequestBody MarkDtoRequest request) {
        try {
            MarkDto markDto = teacherService.updateMark(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully updated ", markDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved " + exception.getMessage(), null));
        }
    }

    //Working
    @PostMapping(value = "/create-attendance")
    public ResponseEntity<Response> createAttendance(@RequestBody AttendanceDtoRequest request) {
        try {
            AttendanceDto attendanceDto = teacherService.createAttendance(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created ", attendanceDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved " + exception.getMessage(), null));
        }
    }
    //Working
    @PutMapping(value = "/update-attendance")
    public ResponseEntity<Response> updateAttendance(@RequestBody AttendanceDtoRequest request) {
        try {
            AttendanceDto attendanceDto = teacherService.updateAttendance(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully updated ", attendanceDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved " + exception.getMessage(), null));
        }
    }

    //Working
    @GetMapping("/get-teacher-mark")
    public ResponseEntity<Response> getMarkTeacher() {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", teacherService.getMarkTeacher()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    //Working
    @GetMapping("/get-teacher-mark/{id}")
    public ResponseEntity<Response> getMarkTeacherByGrade(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", teacherService.getMarkTeacherByGrade(id)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    //Working
    @GetMapping("/get-all-grade")
    public ResponseEntity<Response> getAllGrade() {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", teacherService.getAllGrade()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    //Working
    @GetMapping("/get-all-teacher-grade")
    public ResponseEntity<Response> getTeacherGrade() {
        try {
            teacherService.getTeacherGrade();
            return ResponseEntity.ok(new Response("Successfully got schedules ", teacherService.getTeacherGrade()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    //Working
    @GetMapping("/get-all-student-grade/{id}")
    public ResponseEntity<Response> getAllStudentByGrade(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedules ", teacherService.getAllStudentByGrade(id)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    //Working
    @GetMapping("/get-all-hw-grade")
    public ResponseEntity<Response> getAllHwByTeacher() {
        try {
            teacherService.getAllHwByTeacher();
            return ResponseEntity.ok(new Response("Successfully got schedules ", teacherService.getAllHwByTeacher()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    //Working
    @PutMapping(value = "/mark-hw/{hwId}/{mark}/{hwReview}")
    public ResponseEntity<Response> updateAttendance(@PathVariable Long hwId,
                                                     @PathVariable Integer mark,
                                                     @PathVariable String hwReview) {
        try {
            HomeworkDto homeworkDto = teacherService.markHw(hwId, mark, hwReview);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully updated ", homeworkDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved " + exception.getMessage(), null));
        }
    }
}
