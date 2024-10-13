package org.example.school_project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.example.school_project.dto.*;
import org.example.school_project.service.AdminService;
import org.example.school_project.service.FileService;
import org.example.school_project.util.exception.ObjectNotFoundException;
@Tag(name = "Admin Management", description = "API for managing users and admin-related operations.")
@RestController
@RequestMapping(value = "/admin")
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final FileService fileService;

    @Operation(
            summary = "Retrieve all users",
            description = "This operation allows admins to retrieve a list of all users registered in the system.",
            tags = { "Admin Management" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all users"),
            @ApiResponse(responseCode = "404", description = "Users not found")
    })
    @GetMapping("/get-all-user")
    public ResponseEntity<Response> getAllUser() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Users.", adminService.getAllUser()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all active users",
            description = "Retrieve a list of all users who are currently active in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all active users"),
            @ApiResponse(responseCode = "404", description = "No active users found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/get-all-active-user")
    public ResponseEntity<Response> getAllActiveUser() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Users.", adminService.getAllActiveUser()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get users by role",
            description = "Retrieve a list of all users who have the specified role."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved users by role"),
            @ApiResponse(responseCode = "404", description = "Users with the specified role not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/get-all-user-by-role/{role}")
    public ResponseEntity<Response> getAllUserByRole(@PathVariable String role) {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Users with Role." + role, adminService.getAllUserByRole(role)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all employees",
            description = "Retrieve a list of all employees registered in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all employees"),
            @ApiResponse(responseCode = "404", description = "No employees found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/get-all-employee")
    public ResponseEntity<Response> getAllEmployee() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Employees.", adminService.getAllEmployee()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all active employees",
            description = "Retrieve a list of all employees who are currently active in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all active employees"),
            @ApiResponse(responseCode = "404", description = "No active employees found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/get-all-active-employee")
    public ResponseEntity<Response> getAllActiveEmployee() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Employees.", adminService.getAllActiveEmployee()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all parents",
            description = "Retrieve a list of all parents registered in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all parents"),
            @ApiResponse(responseCode = "404", description = "No parents found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/get-all-parent")
    public ResponseEntity<Response> getAllParent() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Parents.", adminService.getAllParent()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all active parents",
            description = "Retrieve a list of all parents who are currently active in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all active parents"),
            @ApiResponse(responseCode = "404", description = "No active parents found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/get-all-active-parent")
    public ResponseEntity<Response> getAllActiveParent() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Parents.", adminService.getAllActiveParent()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all students",
            description = "Retrieve a list of all students registered in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all students"),
            @ApiResponse(responseCode = "404", description = "No students found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/get-all-student")
    public ResponseEntity<Response> getAllStudent() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all Students.", adminService.getAllStudent()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get all active students",
            description = "Retrieve a list of all students who are currently active in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all active students"),
            @ApiResponse(responseCode = "404", description = "No active students found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/get-all-active-student")
    public ResponseEntity<Response> getAllActiveStudent() {
        try {
            return ResponseEntity.ok(new Response("Successfully got all active Students.", adminService.getAllActiveStudent()));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't find. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Create a new user",
            description = "Create a new user account in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input, user not created", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping(value = "/create-user")
    public ResponseEntity<Response> createUser(@RequestBody UserDtoRequest request) {
        try {
            UserDto userDto = adminService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created User.", userDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("User is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Create a new employee",
            description = "Create a new employee record in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input, employee not created", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping(value = "/create-employee")
    public ResponseEntity<Response> createEmployee(@RequestBody EmployeeDroRequest request) {
        try {
            EmployeeDto employeeDto = adminService.createEmployee(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Employee.", employeeDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Create a new student",
            description = "Create a new student record in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input, student not created", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping(value = "/create-student")
    public ResponseEntity<Response> createStudent(@RequestBody StudentDtoRequest request) {
        try {
            StudentDto studentDto = adminService.createStudent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully created Student.", studentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Student is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Create a new parent",
            description = "Create a new parent record in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Parent successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input, parent not created", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping(value = "/create-parent")
    public ResponseEntity<Response> createParent(@RequestBody ParentDtoRequest request) {
        try {
            ParentDto parentDto = adminService.createParent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Successfully added created Parent.", parentDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Parent is not saved. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update user details",
            description = "Update an existing user record in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input, user not updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping(value = "/update-user")
    public ResponseEntity<Response> updateUser(@RequestBody UserDtoRequest request) {
        try {
            UserDto createUser = adminService.updateUser(request);
            return ResponseEntity.ok(new Response("Updated User successfully.", createUser));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("User is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update employee details",
            description = "Update an existing employee record in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input, employee not updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping(value = "/update-employee")
    public ResponseEntity<Response> updateEmployee(@RequestBody EmployeeDroRequest request) {
        try {
            EmployeeDto createEmployee = adminService.updateEmployee(request);
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Updated Employee successfully.", createEmployee));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Employee is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update student details",
            description = "Update an existing student record in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input, student not updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping(value = "/update-student")
    public ResponseEntity<Response> updateStudent(@RequestBody StudentDtoRequest request) {
        try {
            StudentDto createStudent = adminService.updateStudent(request);
            return ResponseEntity.ok(new Response("Updated Student successfully.", createStudent));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Student is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Update parent details",
            description = "Update an existing parent record in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parent successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input, parent not updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Parent not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping(value = "/update-parent")
    public ResponseEntity<Response> updateParent(@RequestBody ParentDtoRequest request) {
        try {
            ParentDto createParent = adminService.updateParent(request);
            return ResponseEntity.ok(new Response("Updated Parent successfully.", createParent));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Parent is not updated. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Restore a deleted user",
            description = "Restore a user that was previously deleted from the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully restored"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping(value = "/restore-user/{userId}")
    public ResponseEntity<Response> updateParent(@PathVariable Long userId) {
        try {
            UserDto userDto = adminService.restoreUser(userId);
            return ResponseEntity.ok(new Response("Restored User successfully.", userDto));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't restore User. " + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Delete a user",
            description = "Delete a user from the system by their ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully deleted"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @DeleteMapping(value = "/delete-user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        try {
            adminService.deleteUser(userId);
            return ResponseEntity.ok("Deleted User successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Couldn't delete User. " + exception.getMessage());
        }
    }

    @Operation(
            summary = "Add a role to a user",
            description = "Assign a specific role to a user in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role successfully added to user"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping(value = "/add-role")
    public ResponseEntity<Response> addRoleToUser(@RequestBody RoleDto request) {
        try {
            UserDto addRole = adminService.addRoleToUser(request);
            return ResponseEntity.ok(new Response("Added Role successfully", addRole));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Couldn't add Role" + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Remove a role from a user",
            description = "Remove a specific role from a user in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role successfully removed from user"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PutMapping(value = "/remove-role")
    public ResponseEntity<Response> removeRoleFromUser(@RequestBody RoleDto request) {
        try {
            UserDto removeRole = adminService.removeRoleFromUser(request);
            return ResponseEntity.ok(new Response("Removed Role successfully", removeRole));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Couldn't remove Role" + exception.getMessage(), null));
        }
    }

    @Operation(
            summary = "Get reviews as DOCX file",
            description = "Retrieve reviews in DOCX format."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews retrieved successfully", content = @Content(mediaType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value = "/reviewsAsDOCX")
    public ResponseEntity<byte[]> getReviewsAsDocx() {
        return fileService.getReviewsAsDocx();
    }
}
