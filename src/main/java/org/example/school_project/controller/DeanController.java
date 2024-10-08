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
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created", scheduleDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved" + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/update-schedule")
    public ResponseEntity<Response> updateSchedule(@RequestBody ScheduleDtoRequest request) {
        try {
            ScheduleDto scheduleDto = deanService.updateSchedule(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", scheduleDto));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @DeleteMapping(value = "/delete-schedule/{id}")
    public ResponseEntity<Response> deleteSchedule(@PathVariable Long id) {
        try {
            ScheduleDto scheduleDto = deanService.deleteSchedule(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Deleted successfully", scheduleDto));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-grade/{id}")
    public ResponseEntity<Response> getGradeById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got ", deanService.getGradeById(id)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @PostMapping(value = "/create-grade")
    public ResponseEntity<Response> createGrade(@RequestBody GradeDtoRequest request) {
        try {
            GradeDto gradeDto = deanService.createGrade(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created", gradeDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved" + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/update-grade")
    public ResponseEntity<Response> updateGrade(@RequestBody GradeDtoRequest request) {
        try {
            GradeDto gradeDto = deanService.updateGrade(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", gradeDto));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @DeleteMapping(value = "/delete-grade/{id}")
    public ResponseEntity<Response> deleteGrade(@PathVariable Long id) {
        try {
            GradeDto gradeDto = deanService.deleteGrade(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Deleted successfully", gradeDto));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-active-grade")
    public ResponseEntity<Response> getAllActiveGrade() {
        try {
            List<GradeDto> gradeDtoList = deanService.getAllActiveGrade();
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Got all active grade successfully", gradeDtoList));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-grade")
    public ResponseEntity<Response> getAllGrade() {
        try {
            List<GradeDto> gradeDtoList = deanService.getAllGrade();
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Got all active grade successfully", gradeDtoList));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-charter")
    public ResponseEntity<Response> createCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto charterDto = deanService.createCharter(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created", charterDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved" + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/update-charter")
    public ResponseEntity<Response> updateCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto charterDto = deanService.updateCharter(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", charterDto));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-student/{id}")
    public ResponseEntity<Response> getStudentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got ", deanService.getStudentById(id)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/update-student")
    public ResponseEntity<Response> updateStudent(@RequestBody StudentDtoRequest request) {
        try {
            StudentDto studentDto = deanService.updateStudent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", studentDto));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @DeleteMapping(value = "/delete-student/{id}")
    public ResponseEntity<Response> excludeStudent(@PathVariable Long id) {
        try {
            StudentDto studentDto = deanService.excludeStudent(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Deleted successfully", studentDto));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-active-student")
    public ResponseEntity<Response> getAllActiveStudent() {
        try {
            List<StudentDto> studentDtoList = deanService.getAllActiveStudent();
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Got all active grade successfully", studentDtoList));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-student")
    public ResponseEntity<Response> getAllStudent() {
        try {
            List<StudentDto> studentDtoList = deanService.getAllStudent();
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Got all active grade successfully", studentDtoList));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-assignment")
    public ResponseEntity<Response> createAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            deanService.createAssignment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not saved" + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-assignment")
    public ResponseEntity<Response> updateAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto createAssignment = deanService.updateAssignment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", createAssignment));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
}
