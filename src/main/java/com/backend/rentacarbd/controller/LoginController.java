package com.backend.rentacarbd.controller;

import com.backend.rentacarbd.facade.RequestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/v1/logins")
public class LoginController {
    @Autowired
    private RequestFacade requestFacade;

    @GetMapping("/alreadyRegistered/{email}&{password}")
    public boolean isLoginRegistered(@PathVariable String email, @PathVariable String password) {
        return requestFacade.isLoginRegistered(email, password);
    }
}
