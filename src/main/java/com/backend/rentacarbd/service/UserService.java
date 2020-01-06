package com.backend.rentacarbd.service;

import com.backend.rentacarbd.controller.exceptions.UserNotFoundException;
import com.backend.rentacarbd.domain.Login;
import com.backend.rentacarbd.domain.User;
import com.backend.rentacarbd.domain.UserDto;
import com.backend.rentacarbd.mapper.UserMapper;
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
    private UserMapper userMapper;
    @Autowired
    private LoginRepository loginRepository;

    public User saveUser(final UserDto userdto){
        loginRepository.save(new Login(userdto.getEmail(), userdto.getPassword()));
        return userRepository.save(userMapper.mapToUser(userdto));
    }

    public User modifyUser(final UserDto userDto){
        User oldUser = (userRepository.findById(userDto.getId())).get();
        Login oldLogin = (loginRepository.findByEmailAndPassword(oldUser.getEmail(), oldUser.getPassword())).get();
        Login newLogin = new Login(oldLogin.getId(), userDto.getEmail(), userDto.getPassword());
        loginRepository.save(newLogin);
        return userRepository.save(userMapper.mapToUser(userDto));
    }

    public void deleteUser(Long id){
        User user = (userRepository.findById(id).orElse(new User()));
        Login login = (loginRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).get();
        userRepository.deleteById(id);
        loginRepository.delete(login);
    }

    public UserDto getUserByEmail(String email) throws UserNotFoundException {
        return userMapper.mapToUserDto(userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new));
    }

    public List<UserDto> getUsers(){
        return userMapper.mapToUserDtoList(userRepository.findAll());
    }

    public boolean isUserRegistered(final String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean doesUserHaveNoRents(final Long id) { return userRepository.findById(id).get().getRentals().isEmpty();
    }
}
