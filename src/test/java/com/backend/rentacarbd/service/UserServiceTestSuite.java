package com.backend.rentacarbd.service;

import com.backend.rentacarbd.controller.exceptions.UserNotFoundException;
import com.backend.rentacarbd.domain.User;
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
        User user = new User("name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = null;
        try {
            //When
            savedUser = userService.saveUser(user);

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
        User user = new User("name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = userService.saveUser(user);
        try {
            //When
            List<User> listOfUsers = userService.getUsers();

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
        User user = new User("name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = userService.saveUser(user);

        //When
        userService.deleteUser(savedUser.getId());
        int size = userService.getUsers().size();

        //Then
        Assert.assertEquals(0, size);
    }

    @Test
    public void testModifyUser() {
        // Given
        User user = new User("name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = userService.saveUser(user);
        long id = savedUser.getId();
        savedUser.setName("modified");
        User modifiedUser = null;
        try {
            //When
            modifiedUser = userService.modifyUser(savedUser);

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
        User user = new User("name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = userService.saveUser(user);
        User foundUser = null;
        try {
            //When
            foundUser = userService.getUserByEmail(savedUser.getEmail());

            //Then
            Assert.assertEquals(user.getName(), foundUser.getName());
        } catch (UserNotFoundException e) {

        } finally {
            //CleanUp
            userService.deleteUser(savedUser.getId());
        }
    }

    @Test
    public void testIsUserRegistered() {
        //Given
        User user = new User("name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = userService.saveUser(user);
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
        User user = new User("name1", "surname1","email1", "phoneNumber1", "password1");
        User savedUser = userService.saveUser(user);
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
