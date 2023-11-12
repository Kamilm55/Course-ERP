package com.kamil.courseerpbackend.controller;

import com.kamil.courseerpbackend.model.base.BaseResponse;
import com.kamil.courseerpbackend.model.dto.RefreshTokenDto;
import com.kamil.courseerpbackend.model.entity.User;
import com.kamil.courseerpbackend.model.payload.auth.LoginPayload;
import com.kamil.courseerpbackend.model.response.auth.LoginResponse;
import com.kamil.courseerpbackend.service.security.AccessTokenManager;
import com.kamil.courseerpbackend.service.security.RefreshTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AccessTokenManager accessTokenManager;
    private final RefreshTokenManager refreshTokenManager;

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginPayload payload){


        User user = User.builder()
                .name(payload.getName())
                .email(payload.getEmail())
                .build();

        user.setId(2L);

        return BaseResponse.success(
                LoginResponse.builder()
                        .accessToken(accessTokenManager.generate(user))
                        .refreshToken(refreshTokenManager.generate(
                                RefreshTokenDto.builder().rememberMe(payload.isRememberMe()).user(user).build()
                        ))
//                        .userInfo()
                        .build()
        );
    }

}
