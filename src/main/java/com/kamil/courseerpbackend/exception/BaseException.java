package com.kamil.courseerpbackend.exception;

import com.kamil.courseerpbackend.exception.types.NotFoundExceptionType;
import com.kamil.courseerpbackend.exception.types.UserAlreadyRegisteredWithType;
import com.kamil.courseerpbackend.model.enums.response.ResponseMessages;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

import static com.kamil.courseerpbackend.model.enums.response.ExceptionResponseMessages.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder(access = AccessLevel.PRIVATE)
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseException extends RuntimeException{
    ResponseMessages responseMessages;
    NotFoundExceptionType notFoundData;
    UserAlreadyRegisteredWithType userAlreadyRegisteredWithData;

    @Override
    public String getMessage() {
        return responseMessages.getMessage();
    }
    //
    public static BaseException unexpected(){
        return BaseException.builder()
                .responseMessages(UNEXPECTED)
                .build();
    }

    public static BaseException notFound(String target, String field, Object value){
        return BaseException.builder()
                .responseMessages(NOT_FOUND)
                .notFoundData(
                        NotFoundExceptionType.of(target, Map.of(field,value))
                )
                .build();
    }
    public static BaseException userAlreadyRegistered(String field, Object value){
        return BaseException.builder()
                .responseMessages(USER_ALREADY_REGISTERED)
                .userAlreadyRegisteredWithData(
                        UserAlreadyRegisteredWithType.of(Map.of(field,value))
                )
                .build();
    }
    // Custom exception (generic type)
    public static BaseException of(ResponseMessages responseMessages){
        return BaseException.builder()
                .responseMessages(responseMessages)
                .build();
    }

}
