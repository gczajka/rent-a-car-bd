package com.backend.rentacarbd.controller;

import com.backend.rentacarbd.controller.exceptions.UserNotFoundException;
import com.backend.rentacarbd.domain.UserDto;
import com.backend.rentacarbd.mapper.UserMapper;
import com.backend.rentacarbd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto){
        userService.saveUser(userMapper.mapToUser(userDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void modifyUser(@RequestBody UserDto userDto){
        userService.modifyUser(userMapper.mapToUser(userDto));
    }

    @DeleteMapping
    public void deleteUser(@RequestParam Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(userService.getUsers());
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUserById(id));
    }

    @GetMapping("/alreadyRegistered/{email}")
    public boolean isUserRegistered(@PathVariable String email) {
        return userService.isUserRegistered(email);
    }
}
