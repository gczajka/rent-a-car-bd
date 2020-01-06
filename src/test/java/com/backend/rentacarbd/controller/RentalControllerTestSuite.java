package com.backend.rentacarbd.controller;

import com.backend.rentacarbd.domain.RentalDto;
import com.backend.rentacarbd.facade.RequestFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(RentalController.class)
public class RentalControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RequestFacade requestFacade;

    @Test
    public void shouldFetchRentals() throws Exception {
        //Given
        List<RentalDto> rentals = new ArrayList<>();
        when(requestFacade.getRentals()).thenReturn(rentals);
        //When & Then
        mockMvc.perform(get("/v1/rentals").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
