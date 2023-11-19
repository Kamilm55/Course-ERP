package com.kamil.courseerpbackend.model.payload.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshTokenPayload {
    String refreshToken;
    boolean rememberMe;
}
