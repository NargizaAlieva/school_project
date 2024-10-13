package org.example.school_project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.school_project.dto.JwtAuthenticationResponse;
import org.example.school_project.dto.SignInRequest;
import org.example.school_project.dto.SignUpRequest;
import org.example.school_project.service.impl.AuthenticationServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
@Slf4j
public class AuthController {
    private final AuthenticationServiceImpl authenticationService;

    @Operation(
            summary = "User sign-up",
            description = "Register a new user in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully registered", content = @Content(schema = @Schema(implementation = JwtAuthenticationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })
    @PostMapping(value = "/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        log.info("[#signUp] is calling");
        return authenticationService.signUp(request);
    }

    @Operation(
            summary = "User sign-in",
            description = "Authenticate a user and return a JWT token."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully signed in", content = @Content(schema = @Schema(implementation = JwtAuthenticationResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping(value = "/sign-in")
    public JwtAuthenticationResponse singIn(@RequestBody @Valid SignInRequest request) {
        log.info("[#signIn] is calling");
        return authenticationService.signIn(request);
    }

}

