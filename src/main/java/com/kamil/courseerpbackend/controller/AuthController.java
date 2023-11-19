package com.kamil.courseerpbackend.controller;

import com.kamil.courseerpbackend.model.base.BaseResponse;
import com.kamil.courseerpbackend.model.payload.auth.LoginPayload;
import com.kamil.courseerpbackend.model.payload.auth.RefreshTokenPayload;
import com.kamil.courseerpbackend.model.response.auth.LoginResponse;
import com.kamil.courseerpbackend.service.auth.AuthBusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth") // access anonymous users (non-authenticated)
@RequiredArgsConstructor
public class AuthController {

    private final AuthBusinessService authBusinessService;

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginPayload payload){
        return BaseResponse.success(authBusinessService.login(payload));
    }


    @PostMapping("/refresh") //Learn: it is also login , but with access token not credentials
    public BaseResponse<LoginResponse> refresh(@RequestBody RefreshTokenPayload payload){
        return BaseResponse.success(authBusinessService.refresh(payload));
    }
}
