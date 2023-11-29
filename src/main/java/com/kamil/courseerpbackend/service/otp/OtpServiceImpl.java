package com.kamil.courseerpbackend.service.otp;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OtpServiceImpl implements OtpService{
    @Override
    public void send() {
        System.out.printf("Otp send: %s%n", UUID.randomUUID().toString().substring(0,4));
    }
}
