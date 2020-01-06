package com.backend.rentacarbd.facade;

import com.backend.rentacarbd.controller.exceptions.CarNotFoundException;
import com.backend.rentacarbd.controller.exceptions.RentalNotFoundException;
import com.backend.rentacarbd.controller.exceptions.UserNotFoundException;
import com.backend.rentacarbd.domain.*;
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
public class FacadeTestSuite {
    @Autowired
    private RequestFacade requestFacade;

    @Test
    public void testCreateCar() {
        // Given
        CarDto carDto = new CarDto(null,"brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = null;

        try {
            //When
            savedCar = requestFacade.createCar(carDto);

            //Then
            Assert.assertEquals("brand1", savedCar.getBrand());
        } finally {
            //CleanUp
            requestFacade.deleteCar(savedCar.getId());
        }
    }

    @Test
    public void testGetCars() {
        //Given
        CarDto carDto = new CarDto(null,"brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = requestFacade.createCar(carDto);

        try {
            //When
            List<CarDto> listOfCars = requestFacade.getCars();
            //Then
            Assert.assertEquals(1, listOfCars.size());
        } finally {
            //CleanUp
            requestFacade.deleteCar(savedCar.getId());
        }
    }

    @Test
    public void testDeleteCar() {
        // Given
        CarDto carDto = new CarDto(null,"brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = requestFacade.createCar(carDto);

        //When
        requestFacade.deleteCar(savedCar.getId());
        int size = requestFacade.getCars().size();

        //Then
        Assert.assertEquals(0, size);
    }

    @Test
    public void testModifyCar() {
        // Given
        CarDto carDto = new CarDto(null,"brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = requestFacade.createCar(carDto);
        long id = savedCar.getId();
        carDto.setId(id);
        carDto.setBrand("modified");
        Car modifiedCar = null;

        try {
            //When
            modifiedCar = requestFacade.modifyCar(carDto);

            //Then
            Assert.assertEquals("modified", modifiedCar.getBrand());
            Assert.assertEquals(id, (long)modifiedCar.getId());
        } finally {
            //CleanUp
            requestFacade.deleteCar(modifiedCar.getId());
        }
    }

    @Test
    public void testCreateUser() {
        // Given
        UserDto userDto = new UserDto(null, "name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = null;
        try {
            //When
            savedUser = requestFacade.createUser(userDto);

            //Then
            Assert.assertEquals("name1", savedUser.getName());
        } finally {
            //CleanUp
            requestFacade.deleteUser(savedUser.getId());
        }
    }

    @Test
    public void testGetUsers() {
        //Given
        UserDto userDto = new UserDto(null, "name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = requestFacade.createUser(userDto);

        try {
            //When
            List<UserDto> listOfUsers = requestFacade.getUsers();

            //Then
            Assert.assertEquals(1, listOfUsers.size());
        } finally {
            //CleanUp
            requestFacade.deleteUser(savedUser.getId());
        }
    }

    @Test
    public void testDeleteUser() {
        // Given
        UserDto userDto = new UserDto(null, "name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = requestFacade.createUser(userDto);

        //When
        requestFacade.deleteUser(savedUser.getId());
        int size = requestFacade.getUsers().size();

        //Then
        Assert.assertEquals(0, size);
    }

    @Test
    public void testModifyUser() {
        // Given
        UserDto userDto = new UserDto(null, "name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = requestFacade.createUser(userDto);
        long id = savedUser.getId();
        userDto.setName("modified");
        userDto.setId(id);
        User modifiedUser = null;

        try {
            //When
            modifiedUser = requestFacade.modifyUser(userDto);

            //Then
            Assert.assertEquals("modified", modifiedUser.getName());
            Assert.assertEquals(id, (long)modifiedUser.getId());
        } finally {
            //CleanUp
            requestFacade.deleteUser(modifiedUser.getId());
        }
    }

    @Test
    public void testGetUserByEmail() {
        //Given
        UserDto userDto = new UserDto(null, "name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = requestFacade.createUser(userDto);
        UserDto foundUser = null;

        try {
            //When
            foundUser = requestFacade.getUserByEmail(userDto.getEmail());

            //Then
            Assert.assertEquals(userDto.getName(), foundUser.getName());
        } catch (UserNotFoundException e) {

        } finally {
            //CleanUp
            requestFacade.deleteUser(savedUser.getId());
        }
    }

    @Test
    public void testIsUserRegistered() {
        //Given
        UserDto userDto = new UserDto(null, "name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = requestFacade.createUser(userDto);
        boolean isRegistered;

        try {
            //When
            isRegistered = requestFacade.isUserRegistered(userDto.getEmail());

            //Then
            Assert.assertEquals(true, isRegistered);
        } finally {
            //CleanUp
            requestFacade.deleteUser(savedUser.getId());
        }
    }

    @Test
    public void testDoesUserHaveNoRents() {
        //Given
        UserDto userDto = new UserDto(null, "name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = requestFacade.createUser(userDto);
        boolean hasNoRents;

        try {
            //When
            hasNoRents = requestFacade.doesUserHaveNoRents(savedUser.getId());

            //Then
            Assert.assertEquals(true, hasNoRents);
        } finally {
            //CleanUp
            requestFacade.deleteUser(savedUser.getId());
        }
    }

    @Test
    @Transactional
    public void testCreateRental() {
        // Given
        UserDto userDto = new UserDto(null, "name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = requestFacade.createUser(userDto);
        CarDto carDto = new CarDto(null,"brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = requestFacade.createCar(carDto);
        Rental createdRental = null;
        try {
            //When
            createdRental = requestFacade.createRental(savedUser.getId(), savedCar.getId());

            //Then
            Assert.assertEquals("name1", createdRental.getUser().getName());
            Assert.assertEquals("brand1", createdRental.getCar().getBrand());
        } catch (UserNotFoundException e) {

        } catch (CarNotFoundException e) {

        } finally {
            //CleanUp
            requestFacade.endRental(createdRental.getId());
            requestFacade.deleteUser(savedUser.getId());
            requestFacade.deleteCar(savedCar.getId());
        }
    }

    @Test
    @Transactional
    public void testLengthenRental() {
        // Given
        UserDto userDto = new UserDto(null, "name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = requestFacade.createUser(userDto);
        CarDto carDto = new CarDto(null,"brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = requestFacade.createCar(carDto);
        Rental createdRental = null;
        Rental lengthenedRental;
        try {
            createdRental = requestFacade.createRental(savedUser.getId(), savedCar.getId());
        } catch (UserNotFoundException e) {
        } catch (CarNotFoundException e) {}
        try {
            //When
            lengthenedRental = requestFacade.lengthenRental(createdRental.getId());

            //Then
            Assert.assertEquals(2, lengthenedRental.getDuration());
            Assert.assertEquals(10, lengthenedRental.getCost());
        } catch (RentalNotFoundException e) {

        } finally {
            //CleanUp
            requestFacade.endRental(createdRental.getId());
            requestFacade.deleteUser(savedUser.getId());
            requestFacade.deleteCar(savedCar.getId());
        }
    }

    @Test
    @Transactional
    public void testEndRental() {
        // Given
        UserDto userDto = new UserDto(null, "name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = requestFacade.createUser(userDto);
        CarDto carDto = new CarDto(null,"brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = requestFacade.createCar(carDto);
        Rental createdRental = null;
        try {
            createdRental = requestFacade.createRental(savedUser.getId(), savedCar.getId());
        } catch (UserNotFoundException e) {
        } catch (CarNotFoundException e) {}
        try {
            //When
            requestFacade.endRental(createdRental.getId());
            int size = requestFacade.getRentals().size();

            //Then
            Assert.assertEquals(0, size);
        } finally {
            //CleanUp
            requestFacade.deleteUser(savedUser.getId());
            requestFacade.deleteCar(savedCar.getId());
        }
    }

    @Test
    @Transactional
    public void testGetRentals() {
        // Given
        UserDto userDto = new UserDto(null, "name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = requestFacade.createUser(userDto);
        CarDto carDto = new CarDto(null,"brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = requestFacade.createCar(carDto);
        Rental createdRental = null;
        try {
            createdRental = requestFacade.createRental(savedUser.getId(), savedCar.getId());
        } catch (UserNotFoundException e) {
        } catch (CarNotFoundException e) {}
        try {
            //When
            List<RentalDto> listOfRentals = requestFacade.getRentals();

            //Then
            Assert.assertEquals(1, listOfRentals.size());
        } finally {
            //CleanUp
            requestFacade.endRental(createdRental.getId());
            requestFacade.deleteUser(savedUser.getId());
            requestFacade.deleteCar(savedCar.getId());
        }
    }

    @Test
    @Transactional
    public void testGetRentalsByUserId() {
        // Given
        UserDto userDto = new UserDto(null, "name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = requestFacade.createUser(userDto);
        CarDto carDto = new CarDto(null,"brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = requestFacade.createCar(carDto);
        Rental createdRental = null;
        try {
            createdRental = requestFacade.createRental(savedUser.getId(), savedCar.getId());
        } catch (UserNotFoundException e) {
        } catch (CarNotFoundException e) {}
        try {
            //When
            List<RentalDto> listOfRentals = requestFacade.getRentalsByUserId(savedUser.getId());

            //Then
            Assert.assertEquals(1, listOfRentals.size());
        } finally {
            //CleanUp
            requestFacade.endRental(createdRental.getId());
            requestFacade.deleteUser(savedUser.getId());
            requestFacade.deleteCar(savedCar.getId());
        }
    }
}
