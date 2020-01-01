package com.backend.rentacarbd.service;

import com.backend.rentacarbd.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public boolean isLoginRegistered(final String email, final String password) {
        return loginRepository.existsByEmailAndPassword(email, password);
    }
}
