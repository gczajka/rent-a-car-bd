package com.backend.rentacarbd.service;

import com.backend.rentacarbd.emailValidatorAPI.client.EmailValidatorClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailValidatorService {
    @Autowired
    EmailValidatorClient emailValidatorClient;

    public boolean isEmailValid(String email) {
        return emailValidatorClient.validateEmail(email).isValid();
    }
}
