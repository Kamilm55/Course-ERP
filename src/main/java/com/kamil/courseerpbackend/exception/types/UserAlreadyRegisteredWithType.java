package com.kamil.courseerpbackend.exception.types;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@Builder(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAlreadyRegisteredWithType {

    Map<String , Object> fields;

    public static UserAlreadyRegisteredWithType of(Map<String , Object> fields){
        return UserAlreadyRegisteredWithType.builder()
                .fields(fields)
                .build();
    }

}
