package org.example.school_project.controller;

import lombok.AllArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.service.DeanService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/dean")
@AllArgsConstructor
public class DeanController {
    private final DeanService deanService;

    @PostMapping(value = "/create-schedule")
    public ResponseEntity<Response> createSchedule(@RequestBody ScheduleDtoRequest request) {
        try {
            ScheduleDto scheduleDto = deanService.createSchedule(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Schedule", scheduleDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Schedule is not saved. " + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/update-schedule")
    public ResponseEntity<Response> updateSchedule(@RequestBody ScheduleDtoRequest request) {
        try {
            ScheduleDto scheduleDto = deanService.updateSchedule(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Updated Schedule successfully", scheduleDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(exception.getMessage(), null));
        }
    }
    @DeleteMapping("/delete-schedule/{id}")
    public ResponseEntity<Response> deleteSchedule(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully deleted Schedule with id", deanService.deleteSchedule(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @PutMapping("/restore-schedule/{id}")
    public ResponseEntity<Response> restoreSchedule(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully restored Schedule with id", deanService.restoreSchedule(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-schedule")
    public ResponseEntity<Response> getAllSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Schedules", deanService.getAllSchedule()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-active-schedule")
    public ResponseEntity<Response> getAllActiveSchedule() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Schedules", deanService.getAllActiveSchedule()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
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
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Updated Grade successfully", gradeDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(exception.getMessage(), null));
        }
    }
    @DeleteMapping(value = "/delete-grade/{id}")
    public ResponseEntity<Response> deleteGrade(@PathVariable Long id) {
        try {
            GradeDto gradeDto = deanService.deleteGrade(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Deleted Grade successfully", gradeDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/restore-grade/{id}")
    public ResponseEntity<Response> restoreGrade(@PathVariable Long id) {
        try {
            GradeDto gradeDto = deanService.restoreGrade(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Deleted Grade successfully", gradeDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-active-grade")
    public ResponseEntity<Response> getAllActiveGrade() {
        try {
            List<GradeDto> gradeDtoList = deanService.getAllActiveGrade();
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Got all active Grade successfully", gradeDtoList));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-grade")
    public ResponseEntity<Response> getAllGrade() {
        try {
            List<GradeDto> gradeDtoList = deanService.getAllGrade();
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Got all Grade successfully", gradeDtoList));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-charter")
    public ResponseEntity<Response> createCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto charterDto = deanService.createCharter(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully Charter created", charterDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not saved. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-charter")
    public ResponseEntity<Response> updateCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto createCharter = deanService.updateCharter(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated Charter successfully", createCharter));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @DeleteMapping(value = "/delete-charter/{id}")
    public ResponseEntity<Response> deleteCharter(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Deleted Charter successfully", deanService.deleteCharter(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/restore-charter/{id}")
    public ResponseEntity<Response> restoreCharter(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Restored Charter successfully", deanService.restoreCharter(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-charter")
    public ResponseEntity<Response> getAllCharter() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all Charter", deanService.getAllCharter()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping(value = "/get-all-self-charter")
    public ResponseEntity<Response> getAllCharterByAuthor() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all self Charter", deanService.getAllCharterByAuthor()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-student/{id}")
    public ResponseEntity<Response> getStudentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got Student", deanService.getStudentById(id)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/update-student")
    public ResponseEntity<Response> updateStudent(@RequestBody StudentDtoRequest request) {
        try {
            StudentDto studentDto = deanService.updateStudent(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Updated Student successfully", studentDto));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(exception.getMessage(), null));
        }
    }
    @DeleteMapping(value = "/delete-student/{id}")
    public ResponseEntity<Response> excludeStudent(@PathVariable Long id) {
        try {
            StudentDto studentDto = deanService.excludeStudent(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Deleted Student successfully", studentDto));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-active-student")
    public ResponseEntity<Response> getAllActiveStudent() {
        try {
            List<StudentDto> studentDtoList = deanService.getAllActiveStudent();
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Got all active Student successfully", studentDtoList));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-student")
    public ResponseEntity<Response> getAllStudent() {
        try {
            List<StudentDto> studentDtoList = deanService.getAllStudent();
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Got all Student successfully", studentDtoList));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-assignment")
    public ResponseEntity<Response> createAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto assignmentDto = deanService.createAssignment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added Assignment", assignmentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Assignment is not saved" + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-assignment")
    public ResponseEntity<Response> updateAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto createAssignment = deanService.updateAssignment(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Assignment successfully updated", createAssignment));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Assignment is not updated. " + exception.getMessage(), null));
        }
    }
    @DeleteMapping(value = "/delete-assignment/{id}")
    public ResponseEntity<Response> deleteAssignment(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Deleted Assignment successfully", deanService.deleteAssignment(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/restore-assignment/{id}")
    public ResponseEntity<Response> restoreAssignment(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Restored Assignment successfully", deanService.restoreAssignment(id)));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-assignment")
    public ResponseEntity<Response> getAllAssignmentByAuthor() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all Assignment", deanService.getAllAssignmentByAuthor()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping(value = "/get-all-undone-assignment")
    public ResponseEntity<Response> getAllUndoneAssigment() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Successfully got all undone Assignment", deanService.getAllUndoneAssigment()));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
}
