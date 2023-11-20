package com.kamil.courseerpbackend.exception;

import com.kamil.courseerpbackend.model.base.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<?> handleBaseException(BaseException exception){

        HttpStatus status = exception.getResponseMessages().getStatus();

        return new ResponseEntity<>(
                BaseResponse.error(exception),
                status
        );
    }

}
