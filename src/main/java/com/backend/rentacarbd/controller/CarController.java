package com.backend.rentacarbd.controller;

import com.backend.rentacarbd.controller.exceptions.CarNotFoundException;
import com.backend.rentacarbd.domain.CarDto;
import com.backend.rentacarbd.facade.RequestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/v1/cars")
public class CarController {
    @Autowired
    private RequestFacade requestFacade;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCar(@RequestBody CarDto carDto){
        requestFacade.createCar(carDto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void modifyCar(@RequestBody CarDto carDto){
        requestFacade.modifyCar(carDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        requestFacade.deleteCar(id);
    }

    @GetMapping
    public List<CarDto> getCars() {
        return requestFacade.getCars();
    }

    @GetMapping("/{id}")
    public CarDto getCarById(@PathVariable Long id) throws CarNotFoundException {
        return requestFacade.getCarById(id);
    }
}
