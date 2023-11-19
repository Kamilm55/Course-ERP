package com.kamil.courseerpbackend.model.base;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
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
    }


    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>().<T>builder()
                .status(HttpStatus.OK)
                .meta(Meta.of("success" , "Successful operation"))
                .data(data)
                .build();
    }

    //overload without data
    public static <T> BaseResponse<T> success(){
        return  new BaseResponse<>().<T>builder()
                .status(HttpStatus.OK)
                .meta(Meta.of("success" , "Successful operation"))
                .data(null)
                .build();
    }

}
