package com.kamil.courseerpbackend.service.user;

import com.kamil.courseerpbackend.model.entity.User;
import com.kamil.courseerpbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow( () -> new RuntimeException("Not Found User with this email"));
    }

    @Override
    public void insertUser(User user) {
        userRepository.save(user);
    }
}
