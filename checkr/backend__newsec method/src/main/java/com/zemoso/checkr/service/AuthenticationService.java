package com.zemoso.checkr.service;


import com.zemoso.checkr.auth.dao.request.SignUpRequest;
import com.zemoso.checkr.auth.dao.request.SigninRequest;
import com.zemoso.checkr.auth.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}