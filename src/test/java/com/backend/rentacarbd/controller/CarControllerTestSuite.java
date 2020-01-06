package com.backend.rentacarbd.controller;

import com.backend.rentacarbd.domain.CarDto;
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
@WebMvcTest(CarController.class)
public class CarControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RequestFacade requestFacade;

    @Test
    public void shouldFetchCars() throws Exception {
        //Given
        List<CarDto> cars = new ArrayList<>();
        CarDto carDto = new CarDto(1L,"brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        cars.add(carDto);
        when(requestFacade.getCars()).thenReturn(cars);
        //When & Then
        mockMvc.perform(get("/v1/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].brand", is("brand1")))
                .andExpect(jsonPath("$[0].model", is("model1")));
    }

    @Test
    public void shouldCreateCar() throws Exception {
        //Given
        CarDto carDto = new CarDto(1L,"brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        when(requestFacade.createCar(any())).thenReturn(null);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(carDto);
        //When & Then
        mockMvc.perform(post("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
        verify(requestFacade, times(1)).createCar(any());
    }

    @Test
    public void shouldDeleteCar() throws Exception {
        //Given
        doNothing().when(requestFacade).deleteCar(any());
        //When & Then
        mockMvc.perform(delete("/v1/cars/1")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "1"))
                .andExpect(status().isOk());
        verify(requestFacade, times(1)).deleteCar(any());
    }
}
