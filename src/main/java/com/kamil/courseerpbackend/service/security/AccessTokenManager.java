package com.kamil.courseerpbackend.service.security;

import com.kamil.courseerpbackend.model.entity.User;
import com.kamil.courseerpbackend.service.base.TokenGenerator;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccessTokenManager implements TokenGenerator<User> {
    @Override
    public String generate(User obj) {
        return Jwts.builder()
                .setClaims(null)
                .setExpiration(null)
                .setIssuedAt(null)
                .setSubject(null)
//                .signWith()
                .compact();
    }
}
