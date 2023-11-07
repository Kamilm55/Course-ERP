package com.kamil.courseerpbackend.service.base;

public interface TokenReader <T>{
    T read(String token);
}
