package com.backend.rentacarbd.controller;

import com.backend.rentacarbd.controller.exceptions.CarNotFoundException;
import com.backend.rentacarbd.domain.CarDto;
import com.backend.rentacarbd.mapper.CarMapper;
import com.backend.rentacarbd.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/v1/cars")
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private CarMapper carMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCar(@RequestBody CarDto carDto){
        carService.saveCar(carMapper.mapToCar(carDto));
    }

    @DeleteMapping
    public void deleteCar(@RequestParam Long carId) {
        carService.deleteCar(carId);
    }

    @GetMapping
    public List<CarDto> getCars() {
        return carMapper.mapToCarDtoList(carService.getCars());
    }

    @GetMapping("/{id}")
    public CarDto getCarById(@PathVariable Long id) throws CarNotFoundException {
        return carMapper.mapToCarDto(carService.getCarById(id));
    }
}
