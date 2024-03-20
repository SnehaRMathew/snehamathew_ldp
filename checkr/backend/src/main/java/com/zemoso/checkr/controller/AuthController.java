package com.zemoso.checkr.controller;

import com.zemoso.checkr.exception.InvalidCredentialsException;
import com.zemoso.checkr.model.AuthenticationRequest;
import com.zemoso.checkr.model.AuthenticationResponse;
import com.zemoso.checkr.service.impl.TokenEncodeServiceiImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name="AuthController")
public class AuthController {
    private final TokenEncodeServiceiImp tokenEncodeServiceiImp;
    AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(TokenEncodeServiceiImp tokenEncodeServiceiImp, AuthenticationManager authenticationManager) {
        this.tokenEncodeServiceiImp = tokenEncodeServiceiImp;
        this.authenticationManager = authenticationManager;
    }

    @Operation(summary = "Generate Token", description = "Token generated", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))})
    @PostMapping("/authorize")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationrequest) throws InvalidCredentialsException {
        System.out.println("hi check " + authenticationrequest.getUsername() + "  " + authenticationrequest.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationrequest.getUsername(), authenticationrequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenEncodeServiceiImp.generateToken(authentication);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
            return ResponseEntity.ok().headers(headers).body(new AuthenticationResponse(token));
        }catch (Exception ex){
            throw new InvalidCredentialsException();
        }
    }

    @Operation(summary = "Generate Token", description = "Token generated",responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))) })
    @PostMapping("/token")
    public String token(Authentication authentication){
        return tokenEncodeServiceiImp.generateToken(authentication);
    }
    @PostMapping("/logout")
    public String logout() {
        return "logout";
    }

}