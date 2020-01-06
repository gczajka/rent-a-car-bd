package com.backend.rentacarbd.controller;

import com.backend.rentacarbd.controller.exceptions.UserNotFoundException;
import com.backend.rentacarbd.domain.UserDto;
import com.backend.rentacarbd.facade.RequestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/v1/users")
public class UserController {
    @Autowired
    private RequestFacade requestFacade;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto){
        requestFacade.createUser(userDto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void modifyUser(@RequestBody UserDto userDto){
        requestFacade.modifyUser(userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
         requestFacade.deleteUser(id);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return requestFacade.getUsers();
    }

    @GetMapping("/byEmail/{email}")
    public UserDto getUserByEmail(@PathVariable String email) throws UserNotFoundException {
        return requestFacade.getUserById(email);
    }

    @GetMapping("/alreadyRegistered/{email}")
    public boolean isUserRegistered(@PathVariable String email) {
        return requestFacade.isUserRegistered(email);
    }

    @GetMapping("/hasNoRents/{id}")
    public boolean doesUserHaveNoRents(@PathVariable Long id) {
        return requestFacade.doesUserHaveNoRents(id);
    }
}
