package com.backend.rentacarbd.mapper;

import com.backend.rentacarbd.domain.User;
import com.backend.rentacarbd.domain.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTestSuite {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testMapToUser() {
        // Given
        UserDto userDto = new UserDto(1L,"name1", "surname1","email1", "phoneNumber1", "password1");

        // When
        User user = userMapper.mapToUser(userDto);

        // Then
        Assert.assertEquals(1L, (long)user.getId());
        Assert.assertEquals("name1", user.getName());
        Assert.assertEquals("surname1", user.getSurname());
        Assert.assertEquals("email1", user.getEmail());
        Assert.assertEquals("phoneNumber1", user.getPhoneNumber());
        Assert.assertEquals("password1", user.getPassword());
    }

    @Test
    public void testMapToUserDto() {
        // Given
        User user = new User("name1", "surname1","email1", "phoneNumber1", "password1");
        user.setId(1L);

        // When
        UserDto userDto = userMapper.mapToUserDto(user);

        // Then
        Assert.assertEquals(1L, (long)userDto.getId());
        Assert.assertEquals("name1", userDto.getName());
        Assert.assertEquals("surname1", userDto.getSurname());
        Assert.assertEquals("email1", userDto.getEmail());
        Assert.assertEquals("phoneNumber1", userDto.getPhoneNumber());
        Assert.assertEquals("password1", userDto.getPassword());
    }

    @Test
    public void testMapToUserDtoList() {
        // Given
        List<User> userList = new ArrayList<>();
        User user1 = new User("name1", "surname1","email1", "phoneNumber1", "password1");
        User user2 = new User("name2", "surname2","email2", "phoneNumber2", "password2");
        user1.setId(1L);
        user2.setId(2L);
        userList.add(user1);
        userList.add(user2);

        // When
        List<UserDto> userDtoList = userMapper.mapToUserDtoList(userList);

        // Then
        Assert.assertEquals(2, userDtoList.size());
        Assert.assertEquals("surname1", userDtoList.get(0).getSurname());
        Assert.assertEquals(2L, (long)userDtoList.get(1).getId());
    }
}
