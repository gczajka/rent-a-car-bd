package com.backend.rentacarbd.controller;

import com.backend.rentacarbd.domain.UserDto;
import com.backend.rentacarbd.facade.RequestFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RequestFacade requestFacade;

    @Test
    public void shouldFetchUsers() throws Exception {
        //Given
        List<UserDto> users = new ArrayList<>();
        UserDto userDto = new UserDto(1L,"name1", "surname1","email1", "phoneNumber1", "password1");
        users.add(userDto);
        when(requestFacade.getUsers()).thenReturn(users);
        //When & Then
        mockMvc.perform(get("/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("name1")))
                .andExpect(jsonPath("$[0].surname", is("surname1")));
    }

    @Test
    public void shouldCreateUser() throws Exception {
        //Given
        UserDto userDto = new UserDto(1L,"name1", "surname1","email1", "phoneNumber1", "password1");
        when(requestFacade.createUser(any())).thenReturn(null);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);
        //When & Then
        mockMvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
        verify(requestFacade, times(1)).createUser(any());
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        //Given
        doNothing().when(requestFacade).deleteUser(any());
        //When & Then
        mockMvc.perform(delete("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "1"))
                .andExpect(status().isOk());
        verify(requestFacade, times(1)).deleteUser(any());
    }
}
