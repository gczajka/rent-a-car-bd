package com.backend.rentacarbd.controller;

import com.backend.rentacarbd.mapper.LoginMapper;
import com.backend.rentacarbd.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/v1/logins")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private LoginMapper loginMapper;

    @GetMapping("/alreadyRegistered/{email}&{password}")
    public boolean isLoginRegistered(@PathVariable String email, @PathVariable String password) {
        return loginService.isLoginRegistered(email, password);
    }
}
