package com.zemoso.checkr.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zemoso.checkr.auth.dao.request.SignUpRequest;
import com.zemoso.checkr.auth.dao.request.SigninRequest;
import com.zemoso.checkr.auth.dao.response.JwtAuthenticationResponse;
import com.zemoso.checkr.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    @SecurityRequirements
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }
    @PostMapping("/signin")
    @SecurityRequirements
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody(required = false) SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }

}