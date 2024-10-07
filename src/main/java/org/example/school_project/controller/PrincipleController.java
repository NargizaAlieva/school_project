package org.example.school_project.controller;

import lombok.AllArgsConstructor;
import org.example.school_project.dto.EmployeeDto;
import org.example.school_project.dto.Response;
import org.example.school_project.entity.*;
import org.example.school_project.service.PrincipleService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/principle")
@AllArgsConstructor
public class PrincipleController {
    private PrincipleService principleService;

    @GetMapping("/get-schedule/{id}")
    public ResponseEntity<Response> getScheduleById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got schedule with id", principleService.getScheduleById(id)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/approve-schedule/{id}")
    public ResponseEntity<Response> approveSchedule(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully approved schedule with id", principleService.approveSchedule(id)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-schedule")
    public List<Schedule> getAllSchedule() {
        return principleService.getAllSchedule();
    }
    @GetMapping("/get-all-unapproved-schedule")
    public List<Schedule> getAllUnapprovedSchedule() {
        return principleService.getAllUnApprovedSchedule();
    }

    @PostMapping(value = "/hire-employee")
    public ResponseEntity<Response> createEmployee(@RequestBody Employee request) {
        try {
            principleService.hireEmployee(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved" + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-employee")
    public ResponseEntity<Response> updateEmployee(@RequestBody Employee request) {
        try {
            EmployeeDto createEmployee = principleService.updateEmployee(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", createEmployee));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @GetMapping("/get-all-employee")
    public List<EmployeeDto> getAllEmployee() {
        return principleService.getAllEmployee();
    }

    @DeleteMapping(value = "/fire-employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        try {
            principleService.fireEmployee(id);
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping(value = "/create-subject")
    public ResponseEntity<Response> createSubject(@RequestBody Subject request) {
        try {
            principleService.addSubject(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Subject is not saved" + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/update-subject")
    public ResponseEntity<Response> updateSubject(@RequestBody Subject request) {
        try {
            Subject createSubject = principleService.updateSubject(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", createSubject));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @DeleteMapping(value = "/delete-subject/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable Long id) {
        try {
            principleService.deleteSubject(id);
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PostMapping(value = "/create-charter")
    public ResponseEntity<Response> createSubject(@RequestBody Charter request) {
        try {
            principleService.createCharter(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Charter is not saved" + exception.getMessage(), null));
        }
    }
}
