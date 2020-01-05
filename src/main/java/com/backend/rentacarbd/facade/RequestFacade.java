package com.backend.rentacarbd.facade;

import com.backend.rentacarbd.controller.exceptions.CarNotFoundException;
import com.backend.rentacarbd.controller.exceptions.RentalNotFoundException;
import com.backend.rentacarbd.controller.exceptions.UserNotFoundException;
import com.backend.rentacarbd.domain.CarDto;
import com.backend.rentacarbd.domain.RentalDto;
import com.backend.rentacarbd.domain.UserDto;
import com.backend.rentacarbd.mapper.CarMapper;
import com.backend.rentacarbd.mapper.RentalMapper;
import com.backend.rentacarbd.mapper.UserMapper;
import com.backend.rentacarbd.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class RequestFacade {
    @Autowired
    CarService carService;
    @Autowired
    LoginService loginService;
    @Autowired
    RentalService rentalService;
    @Autowired
    UserService userService;
    @Autowired
    EmailValidatorService emailValidatorService;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private RentalMapper rentalMapper;
    @Autowired
    private UserMapper userMapper;


    public void createCar(CarDto carDto) {
        carService.saveCar(carMapper.mapToCar(carDto));
    }

    public void modifyCar(CarDto carDto){
        carService.modifyCar(carMapper.mapToCar(carDto));
    }

    public void deleteCar(Long id) {
        carService.deleteCar(id);
    }

    public List<CarDto> getCars() {
        return carMapper.mapToCarDtoList(carService.getCars());
    }

    public CarDto getCarById(Long id) throws CarNotFoundException {
        return carMapper.mapToCarDto(carService.getCarById(id));
    }



    public void createRental(Long userId, Long carId) throws UserNotFoundException, CarNotFoundException {
        rentalService.createRental(userId, carId);
    }

    public void lengthenRental(Long rentalId) throws RentalNotFoundException {
        rentalService.lengthenRental(rentalId);
    }

    public void endRental(Long id) {
        rentalService.endRental(id);
    }

    public List<RentalDto> getRentals() {
        return rentalMapper.mapToRentalDtoList(rentalService.getRentals());
    }

    public List<RentalDto> getRentalsByUserId(Long userId) {
        return rentalMapper.mapToRentalDtoList(rentalService.getRentalsByUserId(userId));
    }

    public RentalDto getRentalById(Long id) throws RentalNotFoundException {
        return rentalMapper.mapToRentalDto(rentalService.getRentalById(id));
    }


    public void createUser(@RequestBody UserDto userDto){
        userService.saveUser(userMapper.mapToUser(userDto));
    }

    public void modifyUser(@RequestBody UserDto userDto){
        userService.modifyUser(userMapper.mapToUser(userDto));
    }

    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(userService.getUsers());
    }

    public UserDto getUserById(@PathVariable Long id) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUserById(id));
    }

    public UserDto getUserById(@PathVariable String email) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUserByEmail(email));
    }

    public boolean isUserRegistered(@PathVariable String email) {
        return userService.isUserRegistered(email);
    }

    public boolean doesUserHaveNoRents(@PathVariable Long id) {
        return userService.doesUserHaveNoRents(id);
    }


    public boolean isLoginRegistered(String email, String password) {
        return loginService.isLoginRegistered(email, password);
    }

    public boolean isEmailValid(String email) {
        return emailValidatorService.isEmailValid(email);
    }
}
