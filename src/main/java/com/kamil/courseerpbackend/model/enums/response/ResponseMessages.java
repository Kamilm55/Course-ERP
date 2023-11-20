package com.kamil.courseerpbackend.model.enums.response;


import org.springframework.http.HttpStatus;

public interface ResponseMessages {
    String getKey();
    String getMessage();
    HttpStatus getStatus();
}
