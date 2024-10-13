package org.example.school_project.controller;

import java.util.List;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.example.school_project.dto.*;
import org.example.school_project.service.DeanService;

@RestController
@RequestMapping(value = "/dean")
@AllArgsConstructor
public class DeanController {
    private final DeanService deanService;

    @PostMapping(value = "/create-schedule")
    public ResponseEntity<Response> createSchedule(@RequestBody ScheduleDtoRequest request) {
        try {
            ScheduleDto scheduleDto = deanService.createSchedule(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Schedule.", scheduleDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Schedule is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-schedule")
    public ResponseEntity<Response> updateSchedule(@RequestBody ScheduleDtoRequest request) {
        try {
            ScheduleDto scheduleDto = deanService.updateSchedule(request);
            return ResponseEntity.ok(new Response("Updated Schedule successfully.", scheduleDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Schedule not updated. " + exception.getMessage(), null));
        }
    }

    @DeleteMapping("/delete-schedule/{scheduleId}")
    public ResponseEntity<Response> deleteSchedule(@PathVariable Long scheduleId) {
        try {
            return ResponseEntity.ok(new Response("Successfully deleted Schedule with id.", deanService.deleteSchedule(scheduleId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }
    @PutMapping("/restore-schedule/{scheduleId}")
    public ResponseEntity<Response> restoreSchedule(@PathVariable Long scheduleId) {
        try {
            return ResponseEntity.ok(new Response("Successfully restored Schedule with id.", deanService.restoreSchedule(scheduleId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/change-teacher-schedule/{teacherId}")
    public ResponseEntity<Response> updateSchedule(@PathVariable Long teacherId) {
        try {
            ScheduleDto scheduleDto = deanService.changeTeacherSchedule(teacherId);
            return ResponseEntity.ok(new Response("Changed Teacher successfully.", scheduleDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Failed to Change Teacher. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-teacher-schedule-count")
    public ResponseEntity<Response> getAllTeacherScheduleNum() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all teachers schedule count.", deanService.getAllTeacherScheduleNum()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-schedule")
    public ResponseEntity<Response> getAllSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Schedules.", deanService.getAllSchedule()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-active-schedule")
    public ResponseEntity<Response> getAllActiveSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Schedules", deanService.getAllActiveSchedule()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-grade")
    public ResponseEntity<Response> createGrade(@RequestBody GradeDtoRequest request) {
        try {
            GradeDto gradeDto = deanService.createGrade(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Grade", gradeDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Grade is not saved" + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/update-grade")
    public ResponseEntity<Response> updateGrade(@RequestBody GradeDtoRequest request) {
        try {
            GradeDto gradeDto = deanService.updateGrade(request);
            return ResponseEntity.ok(new Response("Updated Grade successfully", gradeDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Grade is not updated" + exception.getMessage(), null));
        }
    }
    @DeleteMapping(value = "/delete-grade/{gradeId}")
    public ResponseEntity<Response> deleteGrade(@PathVariable Long gradeId) {
        try {
            GradeDto gradeDto = deanService.deleteGrade(gradeId);
            return ResponseEntity.ok(new Response("Deleted Grade successfully", gradeDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/restore-grade/{gradeId}")
    public ResponseEntity<Response> restoreGrade(@PathVariable Long gradeId) {
        try {
            GradeDto gradeDto = deanService.restoreGrade(gradeId);
            return ResponseEntity.ok(new Response("Restored Grade successfully.", gradeDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-active-grade")
    public ResponseEntity<Response> getAllActiveGrade() {
        try {
            List<GradeDto> gradeDtoList = deanService.getAllActiveGrade();
            return ResponseEntity.ok(new Response("Got all active Grade successfully. ", gradeDtoList));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-grade")
    public ResponseEntity<Response> getAllGrade() {
        try {
            List<GradeDto> gradeDtoList = deanService.getAllGrade();
            return ResponseEntity.ok(new Response("Got all Grade successfully", gradeDtoList));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-charter")
    public ResponseEntity<Response> createCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto charterDto = deanService.createCharter(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Charter.", charterDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-charter")
    public ResponseEntity<Response> updateCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto createCharter = deanService.updateCharter(request);
            return ResponseEntity.ok(new Response("Updated successfully Charter", createCharter));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not updated. " + exception.getMessage(), null));
        }
    }

    @DeleteMapping(value = "/delete-charter/{carterId}")
    public ResponseEntity<Response> deleteCharter(@PathVariable Long carterId) {
        try {
            return ResponseEntity.ok(new Response("Deleted Charter successfully", deanService.deleteCharter(carterId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/restore-charter/{carterId}")
    public ResponseEntity<Response> restoreCharter(@PathVariable Long carterId) {
        try {
            return ResponseEntity.ok(new Response("Restored Charter successfully", deanService.restoreCharter(carterId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-charter")
    public ResponseEntity<Response> getAllCharter() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Charter", deanService.getAllCharter()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
    @GetMapping(value = "/get-all-self-charter")
    public ResponseEntity<Response> getAllCharterByAuthor() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all self Charter.", deanService.getAllCharterByAuthor()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-student/{id}")
    public ResponseEntity<Response> getStudentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Student.", deanService.getStudentById(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/update-student")
    public ResponseEntity<Response> updateStudent(@RequestBody StudentDtoRequest request) {
        try {
            StudentDto studentDto = deanService.updateStudent(request);
            return ResponseEntity.ok(new Response("Updated Student successfully.", studentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
    @DeleteMapping(value = "/exclude-student/{studentId}")
    public ResponseEntity<Response> excludeStudent(@PathVariable Long studentId) {
        try {
            StudentDto studentDto = deanService.excludeStudent(studentId);
            return ResponseEntity.ok(new Response("Excluded Student successfully.", studentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to exclude. " + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/restore-student/{studentId}")
    public ResponseEntity<Response> restoreStudent(@PathVariable Long studentId) {
        try {
            StudentDto studentDto = deanService.restoreStudent(studentId);
            return ResponseEntity.ok(new Response("Restored Student successfully.", studentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-active-student")
    public ResponseEntity<Response> getAllActiveStudent() {
        try {
            List<StudentDto> studentDtoList = deanService.getAllActiveStudent();
            return ResponseEntity.ok(new Response("Got all active Student successfully", studentDtoList));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-student")
    public ResponseEntity<Response> getAllStudent() {
        try {
            List<StudentDto> studentDtoList = deanService.getAllStudent();
            return ResponseEntity.ok(new Response("Got all Student successfully", studentDtoList));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-assignment")
    public ResponseEntity<Response> createAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto assignmentDto = deanService.createAssignment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added Assignment.", assignmentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Assignment is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-assignment")
    public ResponseEntity<Response> updateAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto createAssignment = deanService.updateAssignment(request);
            return ResponseEntity.ok(new Response("Assignment successfully updated.", createAssignment));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Assignment is not updated. " + exception.getMessage(), null));
        }
    }
    @DeleteMapping(value = "/delete-assignment/{assignmentId}")
    public ResponseEntity<Response> deleteAssignment(@PathVariable Long assignmentId) {
        try {
            return ResponseEntity.ok(new Response("Deleted Assignment successfully.", deanService.deleteAssignment(assignmentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/restore-assignment/{id}")
    public ResponseEntity<Response> restoreAssignment(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Restored Assignment successfully.", deanService.restoreAssignment(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-assignment")
    public ResponseEntity<Response> getAllAssignmentByAuthor() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Assignment.", deanService.getAllAssignmentByAuthor()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-undone-assignment")
    public ResponseEntity<Response> getAllUndoneAssigment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone Assignment.", deanService.getAllUndoneAssigment()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-done-assignment")
    public ResponseEntity<Response> getAllDoneAssigment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all done Assignment.", deanService.getAllDoneAssigment()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

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

    @GetMapping("/get-student-mark/{studentId}")
    public ResponseEntity<Response> getAvgMarkByGradeStudent(@PathVariable Long studentId) {
        try {
            deanService.getAvgMarkByGradeStudent(studentId);
            return ResponseEntity.ok(new Response("Successfully got Mark.", deanService.getAvgMarkByGradeStudent(studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

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

    @GetMapping("/get-grade-mark/{gradeId}")
    public ResponseEntity<Response> getAvgMarkByGrade(@PathVariable Long gradeId) {
        try {
            deanService.getAvgMarkByGrade(gradeId);
            return ResponseEntity.ok(new Response("Successfully got Mark.", deanService.getAvgMarkByGrade(gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-grades-mark")
    public ResponseEntity<Response> getAvgMarks() {
        try {
            deanService.getAvgMarks();
            return ResponseEntity.ok(new Response("Successfully got Marks.", deanService.getAvgMarks()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-school-avg-mark")
    public ResponseEntity<Response> getAvgSchoolMark() {
        try {
            deanService.getAvgSchoolMark();
            return ResponseEntity.ok(new Response("Successfully got Mark.", deanService.getAvgSchoolMark()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

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

    @GetMapping("/get-grade-attend/{gradeId}")
    public ResponseEntity<Response> getAttendByQuarterGrade(@PathVariable Long gradeId) {
        try {
            deanService.getAttendByGrade(gradeId);
            return ResponseEntity.ok(new Response("Successfully got Attendances.", deanService.getAttendByGrade(gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-grades-attend")
    public ResponseEntity<Response> getAttendGrades() {
        try {
            deanService.getAttendGrades();
            return ResponseEntity.ok(new Response("Successfully got Attendances.", deanService.getAttendGrades()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-announcement")
    public ResponseEntity<Response> createAnnouncement(@RequestBody AnnouncementDtoRequest request) {
        try {
            AnnouncementDto announcementDto = deanService.createAnnouncement(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Announcement.", announcementDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Announcement is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-announcement")
    public ResponseEntity<Response> updateAnnouncement(@RequestBody AnnouncementDtoRequest request) {
        try {
            AnnouncementDto announcementDto = deanService.updateAnnouncement(request);
            return ResponseEntity.ok(new Response("Announcement Schedule successfully.", announcementDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Announcement not updated. " + exception.getMessage(), null));
        }
    }

    @DeleteMapping("/delete-announcement/{announcementId}")
    public ResponseEntity<Response> deleteAnnouncement(@PathVariable Long announcementId) {
        try {
            return ResponseEntity.ok(new Response("Successfully deleted Announcement with id.", deanService.deleteAnnouncement(announcementId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }
    @PutMapping("/restore-announcement/{announcementId}")
    public ResponseEntity<Response> restoreAnnouncement(@PathVariable Long announcementId) {
        try {
            return ResponseEntity.ok(new Response("Successfully restored Announcement with id.", deanService.restoreAnnouncement(announcementId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-active-announcement")
    public ResponseEntity<Response> getAllActiveAnnouncement() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Announcement.", deanService.getAllActiveAnnouncement()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-self-announcement")
    public ResponseEntity<Response> getAllSelfAnnouncement() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all self Announcement.", deanService.getAllSelfAnnouncement()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
}
