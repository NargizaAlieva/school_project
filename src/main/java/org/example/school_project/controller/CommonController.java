package org.example.school_project.controller;

import lombok.AllArgsConstructor;
import org.example.school_project.dto.*;
import org.example.school_project.service.CommonService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/common")
@AllArgsConstructor
public class CommonController {
    private final CommonService commonService;
    @PostMapping(value = "/create-student")
    public ResponseEntity<Response> createStudent(@RequestBody StudentDtoRequest request) {
        try {
            commonService.createStudent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Student is not saved " + exception.getMessage(), null));
        }
    }

    @GetMapping("/get-schedule/{id}")
    public ResponseEntity<Response> getScheduleById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedule with id", commonService.getScheduleById(id)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-schedule")
    public List<ScheduleDto> getAllSchedule() {
        return commonService.getAllSchedule();
    }
    @GetMapping("/get-all-unapproved-schedule")
    public List<ScheduleDto> getAllUnapprovedSchedule() {
        return commonService.getAllUnApprovedSchedule();
    }

    @PutMapping(value = "/update-employee")
    public ResponseEntity<Response> updateEmployee(@RequestBody EmployeeDroRequest request) {
        try {
            EmployeeDto createEmployee = commonService.updateEmployee(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", createEmployee));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-charter")
    public ResponseEntity<Response> createCharter(@RequestBody CharterDtoRequest request) {
        try {
            commonService.createCharter(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not saved" + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-charter")
    public ResponseEntity<Response> updateCharter(@RequestBody CharterDtoRequest request) {
        try {
            CharterDto createCharter = commonService.updateCharter(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", createCharter));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }


    @PostMapping(value = "/create-assignment")
    public ResponseEntity<Response> createAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            commonService.createAssignment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not saved" + exception.getMessage(), null));
        }
    }

//    @PostMapping(value = "/create-assignment-to-secretary")
//    public ResponseEntity<Response> createAssignmentToSecretary(@RequestBody AssignmentDtoRequest request) {
//        try {
//            principleService.createAssignmentToSecretary(request);
//            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added", request));
//        } catch (Exception exception) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not saved" + exception.getMessage(), null));
//        }
//    }

    @PutMapping(value = "/update-assignment")
    public ResponseEntity<Response> updateAssignment(@RequestBody AssignmentDtoRequest request) {
        try {
            AssignmentDto createAssignment = commonService.updateAssignment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", createAssignment));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
}
