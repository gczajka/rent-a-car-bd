package com.backend.rentacarbd.controller;


import com.backend.rentacarbd.facade.RequestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/v1/verification")
public class VerificationController {
    @Autowired
    private RequestFacade requestFacade;

    @GetMapping("/{email}")
    public boolean getUserById(@PathVariable String email) {
        return requestFacade.isEmailValid(email);
    }
}
