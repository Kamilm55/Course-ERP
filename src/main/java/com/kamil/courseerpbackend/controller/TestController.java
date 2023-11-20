package com.kamil.courseerpbackend.controller;

import com.kamil.courseerpbackend.exception.BaseException;
import com.kamil.courseerpbackend.model.base.BaseResponse;
import com.kamil.courseerpbackend.model.entity.User;
import com.kamil.courseerpbackend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private  final UserService userService;

    @GetMapping("/auth")
    public BaseResponse<String> auth() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return BaseResponse.success(userDetails.getUsername());
    }
    
    @GetMapping("/no-auth")
    public BaseResponse<String> noAuth() {
        // refactorThis: fix this , it is just logging for exception handling
//    throw BaseException.unexpected();

        User user = userService.getUserByEmail("KAmdasil@gmail.com");


        return BaseResponse.success("Works NO - AUTH");
    }
}
