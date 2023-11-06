package com.kamil.courseerpbackend.service.base;

public interface TokenGenerator <T> {
    String generate(T obj);
}
