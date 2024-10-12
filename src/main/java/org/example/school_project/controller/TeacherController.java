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

    @GetMapping("/get-schedule")
    public ResponseEntity<Response> getTeacherSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Schedules ", teacherService.getTeacherSchedule()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found" + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-review")
    public ResponseEntity<Response> createReview(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = teacherService.createReview(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Review", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Review is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-review")
    public ResponseEntity<Response> updateReview(@RequestBody ReviewDtoRequest request) {
        try {
            ReviewDto reviewDto = teacherService.updateReview(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully updated Review", reviewDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Review is not updated. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-lesson")
    public ResponseEntity<Response> createLesson(@RequestBody LessonDtoRequest request) {
        try {
            LessonDto lessonDto = teacherService.createLesson(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Lesson", lessonDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Lesson is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-lesson")
    public ResponseEntity<Response> updateLesson(@RequestBody LessonDtoRequest request) {
        try {
            LessonDto lessonDto = teacherService.updateLesson(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully updated Lesson", lessonDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Lesson is not updated. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-mark")
    public ResponseEntity<Response> createMark(@RequestBody MarkDtoRequest request) {
        try {
            MarkDto markDto = teacherService.createMark(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Mark", markDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Mark is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-mark")
    public ResponseEntity<Response> updateMark(@RequestBody MarkDtoRequest request) {
        try {
            MarkDto markDto = teacherService.updateMark(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully updated Mark", markDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Mark is not updated. " + exception.getMessage(), null));
        }
    }

    //Working
    @PostMapping(value = "/create-attendance")
    public ResponseEntity<Response> createAttendance(@RequestBody AttendanceDtoRequest request) {
        try {
            AttendanceDto attendanceDto = teacherService.createAttendance(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Attendance", attendanceDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Attendance is not saved. " + exception.getMessage(), null));
        }
    }
    //Working
    @PutMapping(value = "/update-attendance")
    public ResponseEntity<Response> updateAttendance(@RequestBody AttendanceDtoRequest request) {
        try {
            AttendanceDto attendanceDto = teacherService.updateAttendance(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully updated Attendance", attendanceDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Attendance is not updated. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-teacher-mark")
    public ResponseEntity<Response> getMarkTeacher() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks", teacherService.getMarkTeacher()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-teacher-mark/{id}")
    public ResponseEntity<Response> getMarkTeacherByGrade(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Mark ", teacherService.getMarkTeacherByGrade(id)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-teacher-subjects-mark/{gradeId}/{subjectId}/{quarter}")
    public ResponseEntity<Response> getAvgMarkBySubjectGradeQuarter(@PathVariable Long gradeId,
                                                                    @PathVariable Long subjectId,
                                                                    @PathVariable Integer quarter) {
        try {
            teacherService.getAvgMarkBySubjectGradeQuarter(gradeId, subjectId, quarter);
            return ResponseEntity.ok(new Response("Successfully got Mark ", teacherService.getAvgMarkBySubjectGradeQuarter(gradeId, subjectId, quarter)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-teacher-subjects-mark/{gradeId}/{subjectId}")
    public ResponseEntity<Response> getAvgMarkBySubjectGrade(@PathVariable Long gradeId,
                                                             @PathVariable Long subjectId) {
        try {
            teacherService.getAvgMarkBySubjectGrade(gradeId, subjectId);
            return ResponseEntity.ok(new Response("Successfully got Mark ", teacherService.getAvgMarkBySubjectGrade(gradeId, subjectId)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-teacher-subjects-mark/{subjectId}")
    public ResponseEntity<Response> getAvgMarkBySubjectGrade(@PathVariable Long subjectId) {
        try {
            teacherService.getAvgMarkBySubject(subjectId);
            return ResponseEntity.ok(new Response("Successfully got Mark ", teacherService.getAvgMarkBySubject(subjectId)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-grade")
    public ResponseEntity<Response> getAllGrade() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Grades", teacherService.getAllGrade()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-teacher-grade")
    public ResponseEntity<Response> getTeacherGrade() {
        try {
            teacherService.getTeacherGrade();
            return ResponseEntity.ok(new Response("Successfully got Grades", teacherService.getTeacherGrade()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-student-grade/{id}")
    public ResponseEntity<Response> getAllStudentByGrade(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Students from grade ", teacherService.getAllStudentByGrade(id)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-hw-teacher")
    public ResponseEntity<Response> getAllHwByTeacher() {
        try {
            teacherService.getAllHwByTeacher();
            return ResponseEntity.ok(new Response("Successfully got all Homework ", teacherService.getAllHwByTeacher()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-unchecked-hw-teacher")
    public ResponseEntity<Response> getUncheckedHw() {
        try {
            teacherService.getAllHwByTeacher();
            return ResponseEntity.ok(new Response("Successfully got all Homework ", teacherService.getUncheckedHw()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-unchecked-hw-lesson/{lessonId}")
    public ResponseEntity<Response> getAllHwByTeacher(@PathVariable Long lessonId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Homework ", teacherService.getAllUncheckedHwByTeacherLesson(lessonId)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/mark-hw/{hwId}/{mark}/{hwReview}")
    public ResponseEntity<Response> markHw(@PathVariable Long hwId,
                                                     @PathVariable Integer mark,
                                                     @PathVariable String hwReview) {
        try {
            HomeworkDto homeworkDto = teacherService.markHw(hwId, mark, hwReview);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully updated Homework", homeworkDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Homework is not updated. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/send-to-parent-by-student/{id}")
    public ResponseEntity<Response> sendToParentByStudentId(@PathVariable Long id,
                                                            @RequestBody MessageDtoRequest request) {
        try {
            teacherService.sendToParentByStudentId(id, request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Sent successfully", null));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }
}
