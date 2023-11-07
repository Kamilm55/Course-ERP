package com.kamil.courseerpbackend.model.dto;

import com.kamil.courseerpbackend.model.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshTokenDto {
    boolean rememberMe;
    User user;
}
