package com.backend.rentacarbd.facade;

import com.backend.rentacarbd.controller.exceptions.CarNotFoundException;
import com.backend.rentacarbd.controller.exceptions.RentalNotFoundException;
import com.backend.rentacarbd.controller.exceptions.UserNotFoundException;
import com.backend.rentacarbd.domain.*;
import com.backend.rentacarbd.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Car createCar(CarDto carDto) {
        return carService.saveCar(carDto);
    }

    public Car modifyCar(CarDto carDto){
        return carService.modifyCar(carDto);
    }

    public void deleteCar(Long id) {
        carService.deleteCar(id);
    }

    public List<CarDto> getCars() {
        return carService.getCars();
    }


    public Rental createRental(Long userId, Long carId) throws UserNotFoundException, CarNotFoundException {
        return rentalService.createRental(userId, carId);
    }

    public Rental lengthenRental(Long rentalId) throws RentalNotFoundException {
        return rentalService.lengthenRental(rentalId);
    }

    public void endRental(Long id) {
        rentalService.endRental(id);
    }

    public List<RentalDto> getRentals() {
        return (rentalService.getRentals());
    }

    public List<RentalDto> getRentalsByUserId(Long userId) {
        return rentalService.getRentalsByUserId(userId);
    }


    public User createUser(UserDto userDto){
        return userService.saveUser(userDto);
    }

    public User modifyUser(UserDto userDto){
        return userService.modifyUser(userDto);
    }

    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    public UserDto getUserByEmail(String email) throws UserNotFoundException {
        return userService.getUserByEmail(email);
    }

    public boolean isUserRegistered(String email) {
        return userService.isUserRegistered(email);
    }

    public boolean doesUserHaveNoRents(Long id) {
        return userService.doesUserHaveNoRents(id);
    }


    public boolean isLoginRegistered(String email, String password) {
        return loginService.isLoginRegistered(email, password);
    }


    public boolean isEmailValid(String email) {
        return emailValidatorService.isEmailValid(email);
    }
}
