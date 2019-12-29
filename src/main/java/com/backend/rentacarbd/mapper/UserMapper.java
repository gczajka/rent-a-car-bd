package com.backend.rentacarbd.mapper;

import com.backend.rentacarbd.domain.User;
import com.backend.rentacarbd.domain.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        User user = new User(
                userDto.getName(),
                userDto.getSurname(),
                userDto.getEmail(),
                userDto.getPhoneNumber(),
                userDto.getPassword());
        user.setId(userDto.getId());
        return user;
    }

    public UserDto mapToUserDto(final User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getPassword());
        userDto.setId(user.getId());
        return userDto;
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getName(),
                        user.getSurname(),
                        user.getEmail(),
                        user.getPhoneNumber(),
                        user.getPassword()))
                .collect(Collectors.toList());
    }
}
