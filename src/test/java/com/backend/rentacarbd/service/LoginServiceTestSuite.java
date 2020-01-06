package com.backend.rentacarbd.service;

import com.backend.rentacarbd.domain.Login;
import com.backend.rentacarbd.repository.LoginRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginServiceTestSuite {
    @Autowired
    LoginService loginService;
    @Autowired
    LoginRepository loginRepository;

    @Test
    public void testIsLoginRegistered() {
        //Given
        Login login = new Login("email1", "password1");
        Login savedLogin = loginRepository.save(login);
        boolean isRegistered;
        try {
            //When
            isRegistered = loginService.isLoginRegistered(login.getEmail(), login.getPassword());

            //Then
            Assert.assertEquals(true, isRegistered);
        } finally {
            //CleanUp
            loginRepository.deleteById(savedLogin.getId());
        }
    }
}
