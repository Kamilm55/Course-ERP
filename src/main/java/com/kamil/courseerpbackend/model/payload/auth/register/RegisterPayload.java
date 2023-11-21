package com.kamil.courseerpbackend.model.payload.auth.register;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterPayload {
    String name;
    String surname;
    String email;
    String phoneNumber;
    String password;

    String courseName;
    String address;
}
