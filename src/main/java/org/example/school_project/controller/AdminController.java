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
    @GetMapping("/get-all-user")
    public ResponseEntity<Response> getAllUser() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Users", adminService.getAllUser()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-active-user")
    public ResponseEntity<Response> getAllActiveUser() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Users", adminService.getAllActiveUser()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-user-by-role/{role}")
    public ResponseEntity<Response> getAllUserByRole(@PathVariable String role) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Users with Role" + role, adminService.getAllUserByRole(role)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-employee")
    public ResponseEntity<Response> getAllEmployee() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Employees", adminService.getAllEmployee()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-active-employee")
    public ResponseEntity<Response> getAllActiveEmployee() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Employees", adminService.getAllActiveEmployee()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-parent")
    public ResponseEntity<Response> getAllParent() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Parents", adminService.getAllParent()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-active-parent")
    public ResponseEntity<Response> getAllActiveParent() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Parents", adminService.getAllActiveParent()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-student")
    public ResponseEntity<Response> getAllStudent() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Students", adminService.getAllStudent()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all-active-student")
    public ResponseEntity<Response> getAllActiveStudent() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Students", adminService.getAllActiveStudent()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-user")
    public ResponseEntity<Response> createUser(@RequestBody UserDtoRequest request) {
        try {
            UserDto userDto = adminService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created User", userDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("User is not saved. " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-employee")
    public ResponseEntity<Response> createEmployee(@RequestBody EmployeeDroRequest request) {
        try {
            EmployeeDto employeeDto = adminService.createEmployee(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Employee", employeeDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved. " + exception.getMessage(), null));
        }
    }
    @PostMapping(value = "/create-student")
    public ResponseEntity<Response> createStudent(@RequestBody StudentDtoRequest request) {
        try {
            StudentDto studentDto = adminService.createStudent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Student", studentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Student is not saved " + exception.getMessage(), null));
        }
    }

    @PostMapping(value = "/create-parent")
    public ResponseEntity<Response> createParent(@RequestBody ParentDtoRequest request) {
        try {
            adminService.createParent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added created Parent", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Parent is not saved " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-user")
    public ResponseEntity<Response> updateUser(@RequestBody UserDtoRequest request) {
        try {
            UserDto createUser = adminService.updateUser(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Updated User successfully", createUser));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("User is not updated. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-employee")
    public ResponseEntity<Response> updateEmployee(@RequestBody EmployeeDroRequest request) {
        try {
            EmployeeDto createEmployee = adminService.updateEmployee(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Updated Employee successfully", createEmployee));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not updated. " + exception.getMessage(), null));
        }
    }
    @PutMapping(value = "/update-student")
    public ResponseEntity<Response> updateStudent(@RequestBody StudentDtoRequest request) {
        try {
            StudentDto createStudent = adminService.updateStudent(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Updated Student successfully", createStudent));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Student is not updated. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update-parent")
    public ResponseEntity<Response> updateParent(@RequestBody ParentDtoRequest request) {
        try {
            ParentDto createParent = adminService.updateParent(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Updated Parent successfully", createParent));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Parent is not updated. " + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/restore-user/{id}")
    public ResponseEntity<Response> updateParent(@PathVariable Long id) {
        try {
            UserDto userDto = adminService.restoreUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Restored User successfully", userDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't restore User. " + exception.getMessage(), null));
        }
    }

    @DeleteMapping(value = "/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            adminService.deleteUser(id);
            return ResponseEntity.ok("Deleted User successfully");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PutMapping(value = "/add-role")
    public ResponseEntity<Response> addRoleToUser(@RequestBody RoleDto request) {
        try {
            UserDto addRole = adminService.addRoleToUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Added Role successfully", addRole));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/remove-role")
    public ResponseEntity<Response> removeRoleFromUser(@RequestBody RoleDto request) {
        try {
            UserDto removeRole = adminService.removeRoleFromUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Removed Role successfully", removeRole));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }
}
