package com.kamil.courseerpbackend.controller;

import com.kamil.courseerpbackend.model.base.BaseResponse;
import com.kamil.courseerpbackend.model.dto.RefreshTokenDto;
import com.kamil.courseerpbackend.model.entity.User;
import com.kamil.courseerpbackend.model.payload.auth.LoginPayload;
import com.kamil.courseerpbackend.model.response.auth.LoginResponse;
import com.kamil.courseerpbackend.service.auth.AuthBusinessService;
import com.kamil.courseerpbackend.service.security.AccessTokenManager;
import com.kamil.courseerpbackend.service.security.RefreshTokenManager;
import com.kamil.courseerpbackend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthBusinessService authBusinessService;

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginPayload payload){
        return BaseResponse.success(authBusinessService.login(payload));
    }


}
