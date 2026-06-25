package com.quickbite.service;

import com.quickbite.dto.LoginRequest;
import com.quickbite.dto.LoginResponse;
import com.quickbite.dto.RegisterRequest;
import com.quickbite.dto.RegisterResponse;
import com.quickbite.entity.Role;
import com.quickbite.entity.User;
import com.quickbite.repository.UserRepository;
import com.quickbite.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl  implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    @Override
    public RegisterResponse register(RegisterRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
        }
        User user=User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER).build();
        User savedUser=userRepository.save(user);
        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole().name()
        );
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user=userRepository.findByEmail(request.getEmail()).orElseThrow(()->new RuntimeException("User Not Found"));
        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid Credentials");
        }
        String token= jwtUtil.generateToken(user.getEmail());
        return new LoginResponse(token);
    }
}
