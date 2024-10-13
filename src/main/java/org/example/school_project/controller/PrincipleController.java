package org.example.school_project.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.example.school_project.dto.*;
import org.example.school_project.service.PrincipleService;

@RestController
@RequestMapping(value = "/principle")
@AllArgsConstructor
public class PrincipleController {
    private PrincipleService principleService;

    @GetMapping("/get-schedule/{scheduleId}")
    public ResponseEntity<Response> getScheduleById(@PathVariable Long scheduleId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Schedule with id", principleService.getScheduleById(scheduleId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
    @PutMapping("/approve-schedule/{scheduleId}")
    public ResponseEntity<Response> approveSchedule(@PathVariable Long scheduleId) {
        try {
            return ResponseEntity.ok(new Response("Successfully approved Schedule with id", principleService.approveSchedule(scheduleId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to approve. " + exception.getMessage(), null));
        }
    }
    @PutMapping("/disapprove-schedule/{scheduleId}")
    public ResponseEntity<Response> disapproveSchedule(@PathVariable Long scheduleId) {
        try {
            return ResponseEntity.ok(new Response("Successfully disapproved Schedule with id", principleService.disapproveSchedule(scheduleId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to disapprove. " + exception.getMessage(), null));
        }
    }
    @DeleteMapping("/delete-schedule/{scheduleId}")
    public ResponseEntity<Response> deleteSchedule(@PathVariable Long scheduleId) {
        try {
            return ResponseEntity.ok(new Response("Successfully deleted Schedule with id", principleService.deleteSchedule(scheduleId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }
    @PutMapping("/restore-schedule/{scheduleId}")
    public ResponseEntity<Response> restoreSchedule(@PathVariable Long scheduleId) {
        try {
            return ResponseEntity.ok(new Response("Successfully restored Schedule with id", principleService.restoreSchedule(scheduleId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-schedule")
    public ResponseEntity<Response> getAllSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Schedules", principleService.getAllSchedule()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-active-schedule")
    public ResponseEntity<Response> getAllActiveSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Schedules", principleService.getAllActiveSchedule()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-active-schedule-by-year/{year}")
    public ResponseEntity<Response> getAllActiveScheduleByYear(@PathVariable String year) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Schedules by " + year, principleService.getAllActiveScheduleByYear(year)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-unapproved-schedule")
    public ResponseEntity<Response> getAllUnapprovedSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all unapproved Schedules", principleService.getAllUnapprovedSchedule()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
    @PostMapping(value = "/create-schedule")
    public ResponseEntity<Response> createSchedule(@RequestBody ScheduleDtoRequest request) {
        try {
            ScheduleDto scheduleDto = principleService.createSchedule(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Schedule.", scheduleDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Schedule is not saved. " + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/update-schedule")
    public ResponseEntity<Response> updateSchedule(@RequestBody ScheduleDtoRequest request) {
        try {
            ScheduleDto scheduleDto = principleService.updateSchedule(request);
            return ResponseEntity.ok(new Response("Updated Schedule successfully.", scheduleDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Schedule is not updated. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/hire-employee")
    public ResponseEntity<Response> createEmployee(@RequestBody EmployeeDroRequest request) {
        try {
            principleService.hireEmployee(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added Employee", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-employee")
    public ResponseEntity<Response> updateEmployee(@RequestBody EmployeeDroRequest request) {
        try {
            EmployeeDto createEmployee = principleService.updateEmployee(request);
            return ResponseEntity.ok(new Response("Updated Employee successfully.", createEmployee));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not updated. " + exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-employee")
    public ResponseEntity<Response> getAllEmployee() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Employee.", principleService.getAllEmployee()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-active-employee")
    public ResponseEntity<Response> getAllActiveEmployee() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Employee.", principleService.getAllActiveSubject()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @DeleteMapping(value = "/fire-employee/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId) {
        try {
            principleService.fireEmployee(employeeId);
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to fire. " + exception.getMessage());
        }
    }

    @PostMapping(value = "/create-subject")
    public ResponseEntity<Response> createSubject(@RequestBody SubjectDtoRequest request) {
        try {
            principleService.addSubject(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added Subject.", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Subject is not saved. " + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/update-subject")
    public ResponseEntity<Response> updateSubject(@RequestBody SubjectDtoRequest request) {
        try {
            SubjectDto createSubject = principleService.updateSubject(request);
            return ResponseEntity.ok(new Response("Updated Subject successfully.", createSubject));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Subject is not updated." + exception.getMessage(), null));
        }
    }
    @DeleteMapping(value = "/delete-subject/{id}")
    public ResponseEntity<Response> deleteSubject(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Deleted Subject successfully.", principleService.deleteSubject(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/restore-subject/{subjectId}")
    public ResponseEntity<Response> restoreSubject(@PathVariable Long subjectId) {
        try {
            return ResponseEntity.ok(new Response("Restored Subject successfully.", principleService.restoreSubject(subjectId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-subject")
    public ResponseEntity<Response> getAllSubject() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Subject.", principleService.getAllSubject()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
    @GetMapping(value = "/get-all-active-subject")
    public ResponseEntity<Response> getAllActiveSubject() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Subject.", principleService.getAllActiveSubject()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-charter")
    public ResponseEntity<Response> createCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto charterDto = principleService.createCharter(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully Charter created.", charterDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-charter")
    public ResponseEntity<Response> updateCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto createCharter = principleService.updateCharter(request);
            return ResponseEntity.ok(new Response("Updated Charter successfully.", createCharter));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not updated. " + exception.getMessage(), null));
        }
    }

    @DeleteMapping(value = "/delete-charter/{charterId}")
    public ResponseEntity<Response> deleteCharter(@PathVariable Long charterId) {
        try {
            return ResponseEntity.ok(new Response("Deleted Charter successfully", principleService.deleteCharter(charterId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/restore-charter/{charterId}")
    public ResponseEntity<Response> restoreCharter(@PathVariable Long charterId) {
        try {
            return ResponseEntity.ok(new Response("Restored Charter successfully.", principleService.restoreCharter(charterId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-charter")
    public ResponseEntity<Response> getAllCharter() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Charter.", principleService.getAllCharter()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
    @GetMapping(value = "/get-all-self-charter")
    public ResponseEntity<Response> getAllCharterByAuthor() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all self Charter.", principleService.getAllCharterByAuthor()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }


    @PostMapping(value = "/create-assignment")
    public ResponseEntity<Response> createAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto assignmentDto = principleService.createAssignment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added Assignment.", assignmentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Assignment is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-assignment")
    public ResponseEntity<Response> updateAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto createAssignment = principleService.updateAssignment(request);
            return ResponseEntity.ok(new Response("Assignment successfully updated.", createAssignment));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Assignment is not updated. " + exception.getMessage(), null));
        }
    }
    @DeleteMapping(value = "/delete-assignment/{assignmentId}")
    public ResponseEntity<Response> deleteAssignment(@PathVariable Long assignmentId) {
        try {
            return ResponseEntity.ok(new Response("Deleted Assignment successfully.", principleService.deleteAssignment(assignmentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/restore-assignment/{assignmentId}")
    public ResponseEntity<Response> restoreAssignment(@PathVariable Long assignmentId) {
        try {
            return ResponseEntity.ok(new Response("Restored Assignment successfully.", principleService.restoreAssignment(assignmentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-assignment")
    public ResponseEntity<Response> getAllAssignmentByAuthor() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Assignment.", principleService.getAllAssignmentByAuthor()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-undone-assignment")
    public ResponseEntity<Response> getAllUndoneAssigment() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all undone Assignment.", principleService.getAllUndoneAssigment()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-quarter-student-mark/{quarter}/{studentId}")
    public ResponseEntity<Response> getAvgMarkByGradeStudentQuarter(@PathVariable Integer quarter,
                                                                    @PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Mark.", principleService.getAvgMarkByGradeStudentQuarter(quarter, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-subject-student-mark/{subjectId}/{studentId}")
    public ResponseEntity<Response> getAvgMarkBySubjectGradeStudent(@PathVariable Long subjectId,
                                                                    @PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Mark.", principleService.getAvgMarkBySubjectGradeStudent(subjectId, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-student-mark/{studentId}")
    public ResponseEntity<Response> getAvgMarkByGradeStudent(@PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Mark.", principleService.getAvgMarkByGradeStudent(studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-quarter-grade-mark/{quarter}/{gradeId}")
    public ResponseEntity<Response> getAvgMarkByGradeQuarter(@PathVariable Integer quarter,
                                                             @PathVariable Long gradeId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Mark.", principleService.getAvgMarkByGradeQuarter(quarter, gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-grade-mark/{gradeId}")
    public ResponseEntity<Response> getAvgMarkByGrade(@PathVariable Long gradeId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Mark.", principleService.getAvgMarkByGrade(gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-grades-mark")
    public ResponseEntity<Response> getAvgMarks() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Marks.", principleService.getAvgMarks()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-school-avg-mark")
    public ResponseEntity<Response> getAvgSchoolMark() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Mark.", principleService.getAvgSchoolMark()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-quarter-grade-student-attend/{quarter}/{gradeId}/{studentId}")
    public ResponseEntity<Response> getAttendByQuarterGradeStudent(@PathVariable Integer quarter,
                                                                   @PathVariable Long gradeId,
                                                                   @PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendances.", principleService.getAttendByQuarterGradeStudent(quarter, gradeId, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-subject-grade-student-attend/{subjectId}/{gradeId}/{studentId}")
    public ResponseEntity<Response> getAttendByQuarterGradeStudent(@PathVariable Long subjectId,
                                                                   @PathVariable Long gradeId,
                                                                   @PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendances.", principleService.getAttendBySubjectGradeStudent(subjectId, gradeId, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-grade-student-attend/{gradeId}/{studentId}")
    public ResponseEntity<Response> getAttendByGradeStudent(@PathVariable Long gradeId,
                                                            @PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendances.", principleService.getAttendByGradeStudent(gradeId, studentId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-quarter-grade-attend/{quarter}/{gradeId}")
    public ResponseEntity<Response> getAttendByQuarterGrade(@PathVariable Integer quarter,
                                                            @PathVariable Long gradeId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendances.", principleService.getAttendByQuarterGrade(quarter, gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-grade-attend/{gradeId}")
    public ResponseEntity<Response> getAttendByQuarterGrade(@PathVariable Long gradeId) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendances.", principleService.getAttendByGrade(gradeId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-grades-attend")
    public ResponseEntity<Response> getAttendGrades() {
        try {
            return ResponseEntity.ok(new Response("Successfully got Attendances.", principleService.getAttendGrades()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't found. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-announcement")
    public ResponseEntity<Response> createAnnouncement(@RequestBody AnnouncementDtoRequest request) {
        try {
            AnnouncementDto announcementDto = principleService.createAnnouncement(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Announcement.", announcementDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Announcement is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-announcement")
    public ResponseEntity<Response> updateAnnouncement(@RequestBody AnnouncementDtoRequest request) {
        try {
            AnnouncementDto announcementDto = principleService.updateAnnouncement(request);
            return ResponseEntity.ok(new Response("Announcement Schedule successfully.", announcementDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Announcement not updated. " + exception.getMessage(), null));
        }
    }

    @DeleteMapping("/delete-announcement/{announcementId}")
    public ResponseEntity<Response> deleteAnnouncement(@PathVariable Long announcementId) {
        try {
            return ResponseEntity.ok(new Response("Successfully deleted Announcement with id.", principleService.deleteAnnouncement(announcementId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to delete. " + exception.getMessage(), null));
        }
    }
    @PutMapping("/restore-announcement/{announcementId}")
    public ResponseEntity<Response> restoreAnnouncement(@PathVariable Long announcementId) {
        try {
            return ResponseEntity.ok(new Response("Successfully restored Announcement with id.", principleService.restoreAnnouncement(announcementId)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to restore. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-active-announcement")
    public ResponseEntity<Response> getAllActiveAnnouncement() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Announcement.", principleService.getAllActiveAnnouncement()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-self-announcement")
    public ResponseEntity<Response> getAllSelfAnnouncement() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all self Announcement.", principleService.getAllSelfAnnouncement()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }
}
