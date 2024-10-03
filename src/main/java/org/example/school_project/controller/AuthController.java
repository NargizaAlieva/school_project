package org.example.school_project.controller;

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

    @PostMapping(value = "/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        log.info("[#signUp] is calling");
        return authenticationService.signUp(request);
    }

    @PostMapping(value = "/sign-in")
    public JwtAuthenticationResponse singIn(@RequestBody @Valid SignInRequest request) {
        log.info("[#signIn] is calling");
        return authenticationService.signIn(request);
    }

}

