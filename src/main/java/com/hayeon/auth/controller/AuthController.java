package com.hayeon.auth.controller;

import com.hayeon.auth.service.AuthService;
import com.hayeon.auth.service.UserService;
import com.hayeon.auth.web.dto.LoginRequestDto;
import com.hayeon.auth.web.dto.LoginResponseDto;
import com.hayeon.auth.web.dto.SignupRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignupRequestDto request) {
        userService.signup(request);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody @Valid LoginRequestDto request) {
        return authService.login(request);
    }
}
