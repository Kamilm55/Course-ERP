package com.kamil.courseerpbackend.service.user;

import com.kamil.courseerpbackend.exception.BaseException;
import com.kamil.courseerpbackend.model.entity.User;
import com.kamil.courseerpbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(
                () -> BaseException.notFound(User.class.getSimpleName() , "email" , email)
        );
    }

    @Override
    public void insertUser(User user) {

        // todo: Refactor password encoder
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //Learn:
        // BadCredentialsException throw for this
        // i forget to Refactor password encoder

        user.setPassword(user.getPassword());
        userRepository.save(user);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public boolean existsUserByPhoneNumber(String phoneNumber) {
        return userRepository.existsUserByPhoneNumber(phoneNumber);
    }
}
