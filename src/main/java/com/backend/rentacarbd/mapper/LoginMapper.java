package com.backend.rentacarbd.mapper;

import com.backend.rentacarbd.domain.Login;
import com.backend.rentacarbd.domain.LoginDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoginMapper {
    public Login mapToLogin(final LoginDto loginDto) {
        Login login = new Login(
                loginDto.getEmail(),
                loginDto.getPassword());
        login.setId(loginDto.getId());
        return login;
    }

    public LoginDto mapToLoginDto(final Login login) {
        LoginDto loginDto = new LoginDto(
                login.getId(),
                login.getEmail(),
                login.getPassword());
        loginDto.setId(login.getId());
        return loginDto;
    }

    public List<LoginDto> mapToLoginDtoList(final List<Login> loginList) {
        return loginList.stream()
                .map(login -> new LoginDto(
                        login.getId(),
                        login.getEmail(),
                        login.getPassword()))
                .collect(Collectors.toList());
    }
}
