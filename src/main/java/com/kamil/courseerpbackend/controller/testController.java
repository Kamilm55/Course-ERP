package com.kamil.courseerpbackend.controller;

import com.kamil.courseerpbackend.model.base.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class testController {
    @GetMapping
    public BaseResponse<String> helloWorld() {
        return BaseResponse.success("Works 2");
    }


}
