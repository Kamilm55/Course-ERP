package com.kamil.courseerpbackend.model.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse<T>{

    private HttpStatus status;
    private String msg;
    private T data;

    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>().<T>builder()
                .status(HttpStatus.OK)
                .msg("Success")
                .data(data)
                .build();
    }

}
