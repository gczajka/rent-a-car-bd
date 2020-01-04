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
        User oldUser = (userRepository.findById(user.getId())).get();
        Login oldLogin = (loginRepository.findByEmailAndPassword(oldUser.getEmail(), oldUser.getPassword())).get();
        Login newLogin = new Login(oldLogin.getId(), user.getEmail(), user.getPassword());
        loginRepository.save(newLogin);
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        User user = (userRepository.findById(id).orElse(new User()));
        Login login = (loginRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).get();
        userRepository.deleteById(id);
        loginRepository.delete(login);
    }

    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public boolean isUserRegistered(final String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean doesUserHaveNoRents(final Long id) { return userRepository.findById(id).get().getRentals().isEmpty();
    }
}
