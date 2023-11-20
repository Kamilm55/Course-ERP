package com.kamil.courseerpbackend.model.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kamil.courseerpbackend.exception.BaseException;
import com.kamil.courseerpbackend.model.enums.response.ExceptionResponseMessages;
import com.kamil.courseerpbackend.model.enums.response.ResponseMessages;
import com.kamil.courseerpbackend.model.enums.response.SuccessResponseMessages;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import static com.kamil.courseerpbackend.model.enums.response.ExceptionResponseMessages.NOT_FOUND;
import static com.kamil.courseerpbackend.model.enums.response.SuccessResponseMessages.SUCCESS;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Slf4j
public class BaseResponse<T>{

     HttpStatus status;
     Meta meta ;
     T data;

    @Data
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder(access = AccessLevel.PRIVATE)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Meta {
        String key;
        String message;

        // this is just builder
        private static Meta of(String key , String message){
            return Meta.builder()
                    .key(key)
                    .message(message)
                    .build();
        }

        private static Meta of(ResponseMessages responseMessages){
            return Meta.builder()
                    .key(responseMessages.getKey())
                    .message(responseMessages.getMessage())
                    .build();
        }

        private static Meta of(BaseException exception){

            if(exception.getResponseMessages().equals(NOT_FOUND)){
                String formattedKey = String.format(exception.getResponseMessages().getKey(), exception.getNotFoundData().getTarget());
                String formattedMessage = String.format(exception.getResponseMessages().getMessage(),exception.getNotFoundData().getFields());
                System.out.println(formattedKey + "  " + formattedMessage);
                log.info((formattedKey + "  " + formattedMessage));

                return Meta.builder()
                        .key(formattedKey)
                        .message(formattedMessage)
                        .build();
            }

            return of(
                    exception.getResponseMessages().getKey(),
                    exception.getResponseMessages().getMessage()
            );

        }
    }


    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>().<T>builder()
                .status(HttpStatus.OK)
                .meta(Meta.of(SUCCESS))
                .data(data)
                .build();
    }

    //overload without data
    public static <T> BaseResponse<T> success(){
        return  new BaseResponse<>().<T>builder()
                .status(HttpStatus.OK)
                .meta(Meta.of(SUCCESS))
                .data(null)
                .build();
    }

    public static <T> BaseResponse<T> error(BaseException baseException){

        return new BaseResponse<>().<T>builder()
                .status(baseException.getResponseMessages().getStatus())
                .meta(
                        Meta.of(baseException)
                )
                .data(null)
                .build();
    }

//    public static <T> BaseResponse<T> error(BaseException baseException, T data){
//        ResponseMessages resMessages = baseException.getResponseMessages();
//
//        return new BaseResponse<>().<T>builder()
//                .status(resMessages.getStatus())
//                .meta(Meta.of(resMessages.getKey(), resMessages.getMessage()))
//                .data(data)
//                .build();
//    }

}
