package com.kamil.courseerpbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class testController {
    @GetMapping
    public String helloWorld() {
        return "Salam";
    }

    @GetMapping("/test")
    public String test() {
        return "TEST";
    }
}
