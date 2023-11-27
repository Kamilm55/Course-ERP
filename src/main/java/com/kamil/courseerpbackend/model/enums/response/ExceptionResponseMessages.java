package com.kamil.courseerpbackend.model.enums.response;

import com.kamil.courseerpbackend.model.enums.response.ResponseMessages;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;



@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public enum ExceptionResponseMessages implements ResponseMessages {
    UNEXPECTED("unexpected" , "Unexpected error:",HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND("not_found_%s" , "%s cannot find in %s" , HttpStatus.NOT_FOUND),
    USER_ALREADY_REGISTERED("user_already_registered" , "user already registered with this %s : %s" , HttpStatus.CONFLICT)
    ;

    //Learn:
    // after this sign ; we can say it is class
    // in enum we should create constructor for using these fields and using these we can create new enums;
    String key;
    String message;
    HttpStatus status;

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
