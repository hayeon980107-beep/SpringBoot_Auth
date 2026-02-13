package com.hayeon.auth.service;

import com.hayeon.auth.domain.User;
import com.hayeon.auth.repository.UserRepository;
import com.hayeon.auth.security.JwtProvider;
import com.hayeon.auth.web.dto.LoginRequestDto;
import com.hayeon.auth.web.dto.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new RuntimeException(("이메일 또는 비밀번호가 올바르지 않습니다.")));

        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        String token = jwtProvider.createAccessToken(user.getEmail(), user.getRole());
        return new LoginResponseDto(token);
    }
}
