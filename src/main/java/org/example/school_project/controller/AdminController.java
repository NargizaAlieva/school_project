package org.example.school_project.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.school_project.dto.*;
import org.example.school_project.service.AdminService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
@AllArgsConstructor
@Slf4j
public class AdminController {
    private final AdminService adminService;
    @PutMapping(value = "/add-role")
    public ResponseEntity<Response> addRoleToUser(@RequestBody RoleDto request) {
        try {
            UserDto addRole = adminService.addRoleToUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Added successfully", addRole));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/remove-role")
    public ResponseEntity<Response> removeRoleFromUser(@RequestBody RoleDto request) {
        try {
            UserDto removeRole = adminService.removeRoleFromUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Removed successfully", removeRole));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-user")
    public ResponseEntity<Response> createUser(@RequestBody UserDtoRequest request) {
        try {
            adminService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("User is not saved " + exception.getMessage(), null));
        }
    }
    @PostMapping(value = "/create-employee")
    public ResponseEntity<Response> createEmployee(@RequestBody EmployeeDroRequest request) {
        try {
            adminService.createEmployee(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved " + exception.getMessage(), null));
        }
    }
    @PostMapping(value = "/create-student")
    public ResponseEntity<Response> createStudent(@RequestBody StudentDtoRequest request) {
        try {
            adminService.createStudent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Student is not saved " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-parent")
    public ResponseEntity<Response> createParent(@RequestBody ParentDtoRequest request) {
        try {
            adminService.createParent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Parent is not saved " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-user")
    public ResponseEntity<Response> updateUser(@RequestBody UserDtoRequest request) {
        try {
            UserDto createUser = adminService.updateUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", createUser));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-employee")
    public ResponseEntity<Response> updateEmployee(@RequestBody EmployeeDroRequest request) {
        try {
            EmployeeDto createEmployee = adminService.updateEmployee(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", createEmployee));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/update-student")
    public ResponseEntity<Response> updateStudent(@RequestBody StudentDtoRequest request) {
        try {
            StudentDto createStudent = adminService.updateStudent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", createStudent));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-parent")
    public ResponseEntity<Response> updateParent(@RequestBody ParentDtoRequest request) {
        try {
            ParentDto createParent = adminService.updateParent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", createParent));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            adminService.deleteUser(id);
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
