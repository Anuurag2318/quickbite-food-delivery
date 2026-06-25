package com.quickbite.service;

import com.quickbite.dto.LoginRequest;
import com.quickbite.dto.LoginResponse;
import com.quickbite.dto.RegisterRequest;
import com.quickbite.dto.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest loginRequest);
}
