package com.backend.rentacarbd.service;

import com.backend.rentacarbd.controller.exceptions.UserNotFoundException;
import com.backend.rentacarbd.domain.User;
import com.backend.rentacarbd.domain.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTestSuite {
    @Autowired
    private UserService userService;

    @Test
    public void testSaveUser() {
        // Given
        UserDto userDto = new UserDto(1L,"name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = null;
        try {
            //When
            savedUser = userService.saveUser(userDto);

            //Then
            Assert.assertEquals("name1", savedUser.getName());
        } finally {
            //CleanUp
            userService.deleteUser(savedUser.getId());
        }
    }

    @Test
    public void testGetUsers() {
        //Given
        UserDto userDto = new UserDto(1L,"name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = userService.saveUser(userDto);
        try {
            //When
            List<UserDto> listOfUsers = userService.getUsers();

            //Then
            Assert.assertEquals(1, listOfUsers.size());
        } finally {
            //CleanUp
            userService.deleteUser(savedUser.getId());
        }
    }

    @Test
    public void testDeleteUser() {
        // Given
        UserDto userDto = new UserDto(null,"name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = userService.saveUser(userDto);

        //When
        userService.deleteUser(savedUser.getId());
        int size = userService.getUsers().size();

        //Then
        Assert.assertEquals(0, size);
    }

    @Test
    public void testModifyUser() {
        // Given
        UserDto userDto = new UserDto(null,"name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = userService.saveUser(userDto);
        long id = savedUser.getId();
        userDto.setId(id);
        userDto.setName("modified");
        User modifiedUser = null;
        try {
            //When
            modifiedUser = userService.modifyUser(userDto);

            //Then
            Assert.assertEquals("modified", modifiedUser.getName());
            Assert.assertEquals(id, (long)modifiedUser.getId());
        } finally {
            //CleanUp
            userService.deleteUser(modifiedUser.getId());
        }
    }

    @Test
    public void testGetUserByEmail() {
        //Given
        UserDto userDto = new UserDto(1L,"name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = userService.saveUser(userDto);
        UserDto foundUser = null;
        try {
            //When
            foundUser = userService.getUserByEmail(savedUser.getEmail());

            //Then
            Assert.assertEquals(userDto.getName(), foundUser.getName());
        } catch (UserNotFoundException e) {

        } finally {
            //CleanUp
            userService.deleteUser(savedUser.getId());
        }
    }

    @Test
    public void testIsUserRegistered() {
        //Given
        UserDto userDto = new UserDto(null,"name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = userService.saveUser(userDto);
        boolean isRegistered;
        try {
            //When
            isRegistered = userService.isUserRegistered(savedUser.getEmail());

            //Then
            Assert.assertEquals(true, isRegistered);
        } finally {
            //CleanUp
            userService.deleteUser(savedUser.getId());
        }
    }

    @Test
    public void testDoesUserHaveNoRents() {
        //Given
        UserDto userDto = new UserDto(1L,"name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = userService.saveUser(userDto);
        boolean hasNoRents;
        try {
            //When
            hasNoRents = userService.doesUserHaveNoRents(savedUser.getId());

            //Then
            Assert.assertEquals(true, hasNoRents);
        } finally {
            //CleanUp
            userService.deleteUser(savedUser.getId());
        }
    }
}
