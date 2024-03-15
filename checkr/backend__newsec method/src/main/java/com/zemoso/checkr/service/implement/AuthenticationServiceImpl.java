package com.zemoso.checkr.service.implement;

import com.zemoso.checkr.entity.User;
import com.zemoso.checkr.repository.UserRepository;
import com.zemoso.checkr.service.AuthenticationService;
import com.zemoso.checkr.service.JwtService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zemoso.checkr.auth.dao.request.SignUpRequest;
import com.zemoso.checkr.auth.dao.request.SigninRequest;
import com.zemoso.checkr.auth.dao.response.JwtAuthenticationResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
//        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
//                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
//                .role("USER").build();
//        userRepository.save(user);
        var jwt = jwtService.generateToken(new User());
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
       // authenticationManager.authenticate(
           //     new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        //var user = userRepository.findByEmail(request.getEmail())
        //        .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(new User());
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}