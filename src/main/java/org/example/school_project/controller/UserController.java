package org.example.school_project.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.school_project.dto.Response;
import org.example.school_project.dto.UserDto;
import org.example.school_project.entity.User;
import org.example.school_project.service.UserService;
import org.example.school_project.util.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<Response> createUser(@RequestBody User request) {
        try {
            userService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Created successfully", request));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Task is not created" + exception.getMessage(), null));
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Response> updateUser(@RequestBody User request) {
        try {
            UserDto createUser = userService.updateUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Updated successfully", createUser));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(exception.getMessage(), null));
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new Response("Successfully got task with id", userService.getById(id)));
        } catch (ObjectNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), null));
        }
    }

    @GetMapping("/get-all")
    public List<UserDto> getAllUser() {
        return userService.getAllUser();
    }
}
