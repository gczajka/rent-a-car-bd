package com.backend.rentacarbd.service;

import com.backend.rentacarbd.controller.exceptions.UserNotFoundException;
import com.backend.rentacarbd.domain.Login;
import com.backend.rentacarbd.domain.User;
import com.backend.rentacarbd.repository.LoginRepository;
import com.backend.rentacarbd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginRepository loginRepository;

    public User saveUser(final User user){
        loginRepository.save(new Login(user.getEmail(), user.getPassword()));
        return userRepository.save(user);
    }

    public User modifyUser(final User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public List<User> getUsers(){
       return userRepository.findAll();
    }

    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
    public boolean isUserRegistered(final String email) {
        return userRepository.existsByEmail(email);
    }
}
