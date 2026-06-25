package com.quickbite.service;

import com.quickbite.dto.RegisterRequest;
import com.quickbite.dto.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);
}
