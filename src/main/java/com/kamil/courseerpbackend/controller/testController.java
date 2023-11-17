package com.kamil.courseerpbackend.controller;

import com.kamil.courseerpbackend.model.base.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class testController {
    @GetMapping("/auth")
    public BaseResponse<String> auth() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return BaseResponse.success(userDetails.getUsername());
    }


    @GetMapping("/no-auth")
    public BaseResponse<String> noAuth() {
        return BaseResponse.success("Works NO - AUTH");
    }
}
