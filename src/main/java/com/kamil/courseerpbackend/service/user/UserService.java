package com.kamil.courseerpbackend.service.user;

import com.kamil.courseerpbackend.model.entity.User;

import java.util.Optional;

public interface UserService {
    User getUserByEmail(String email);
    void insertUser(User user);
    boolean existsUserByEmail(String email);
}
