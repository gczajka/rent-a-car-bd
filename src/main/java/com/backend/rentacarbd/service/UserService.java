package com.backend.rentacarbd.service;

import com.backend.rentacarbd.controller.exceptions.UserNotFoundException;
import com.backend.rentacarbd.domain.User;
import com.backend.rentacarbd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(final User user){
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
}
