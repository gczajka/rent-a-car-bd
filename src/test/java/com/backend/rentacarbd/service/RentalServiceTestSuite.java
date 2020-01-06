package com.backend.rentacarbd.service;

import com.backend.rentacarbd.controller.exceptions.CarNotFoundException;
import com.backend.rentacarbd.controller.exceptions.RentalNotFoundException;
import com.backend.rentacarbd.controller.exceptions.UserNotFoundException;
import com.backend.rentacarbd.domain.Car;
import com.backend.rentacarbd.domain.Rental;
import com.backend.rentacarbd.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RentalServiceTestSuite {
    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;
    @Autowired
    private RentalService rentalService;

    @Test
    @Transactional
    public void testCreateRental() {
        // Given
        User user = new User("name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = userService.saveUser(user);
        Car car = new Car("brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = carService.saveCar(car);
        Rental createdRental = null;
        try {
            //When
            createdRental = rentalService.createRental(savedUser.getId(), savedCar.getId());

            //Then
            Assert.assertEquals("name1", createdRental.getUser().getName());
            Assert.assertEquals("brand1", createdRental.getCar().getBrand());
        } catch (UserNotFoundException e) {

        } catch (CarNotFoundException e) {

        } finally {
            //CleanUp
            rentalService.endRental(createdRental.getId());
            userService.deleteUser(savedUser.getId());
            carService.deleteCar(savedCar.getId());
        }
    }

    @Test
    @Transactional
    public void testLengthenRental() {
        // Given
        User user = new User("name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = userService.saveUser(user);
        Car car = new Car("brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = carService.saveCar(car);
        Rental createdRental = null;
        Rental lengthenedRental;
        try {
            createdRental = rentalService.createRental(savedUser.getId(), savedCar.getId());
        } catch (UserNotFoundException e) {
        } catch (CarNotFoundException e) {}
        try {
            //When
            lengthenedRental = rentalService.lengthenRental(createdRental.getId());

            //Then
            Assert.assertEquals(2, lengthenedRental.getDuration());
            Assert.assertEquals(10, lengthenedRental.getCost());
        } catch (RentalNotFoundException e) {

        } finally {
            //CleanUp
            rentalService.endRental(createdRental.getId());
            userService.deleteUser(savedUser.getId());
            carService.deleteCar(savedCar.getId());
        }
    }

    @Test
    @Transactional
    public void testEndRental() {
        // Given
        User user = new User("name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = userService.saveUser(user);
        Car car = new Car("brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = carService.saveCar(car);
        Rental createdRental = null;
        try {
            createdRental = rentalService.createRental(savedUser.getId(), savedCar.getId());
        } catch (UserNotFoundException e) {
        } catch (CarNotFoundException e) {}
        try {
            //When
            rentalService.endRental(createdRental.getId());
            int size = rentalService.getRentals().size();

            //Then
            Assert.assertEquals(0, size);
        } finally {
            //CleanUp
            userService.deleteUser(savedUser.getId());
            carService.deleteCar(savedCar.getId());
        }
    }

    @Test
    @Transactional
    public void testGetRentals() {
        // Given
        User user = new User("name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = userService.saveUser(user);
        Car car = new Car("brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = carService.saveCar(car);
        Rental createdRental = null;
        try {
            createdRental = rentalService.createRental(savedUser.getId(), savedCar.getId());
        } catch (UserNotFoundException e) {
        } catch (CarNotFoundException e) {}
        try {
            //When
            List<Rental> listOfRentals = rentalService.getRentals();

            //Then
            Assert.assertEquals(1, listOfRentals.size());
        } finally {
            //CleanUp
            rentalService.endRental(createdRental.getId());
            userService.deleteUser(savedUser.getId());
            carService.deleteCar(savedCar.getId());
        }
    }

    @Test
    @Transactional
    public void testGetRentalsByUserId() {
        // Given
        User user = new User("name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = userService.saveUser(user);
        Car car = new Car("brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = carService.saveCar(car);
        Rental createdRental = null;
        try {
            createdRental = rentalService.createRental(savedUser.getId(), savedCar.getId());
        } catch (UserNotFoundException e) {
        } catch (CarNotFoundException e) {}
        try {
            //When
            List<Rental> listOfRentals = rentalService.getRentalsByUserId(savedUser.getId());

            //Then
            Assert.assertEquals(1, listOfRentals.size());
        } finally {
            //CleanUp
            rentalService.endRental(createdRental.getId());
            userService.deleteUser(savedUser.getId());
            carService.deleteCar(savedCar.getId());
        }
    }
}
