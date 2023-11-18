package com.kamil.courseerpbackend.service.auth;

import com.kamil.courseerpbackend.model.payload.auth.LoginPayload;
import com.kamil.courseerpbackend.model.response.auth.LoginResponse;

public interface AuthBusinessService {
    LoginResponse login (LoginPayload payload);
}
