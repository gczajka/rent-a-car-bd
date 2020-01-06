package com.backend.rentacarbd.service;

import com.backend.rentacarbd.domain.Car;
import com.backend.rentacarbd.domain.CarDto;
import com.backend.rentacarbd.mapper.CarMapper;
import com.backend.rentacarbd.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarMapper carMapper;

    public Car saveCar(final CarDto carDto){
        return carRepository.save(carMapper.mapToCar(carDto));
    }

    public Car modifyCar(final CarDto carDto){
        return carRepository.save(carMapper.mapToCar(carDto));
    }

    public void deleteCar(Long id){
        carRepository.deleteById(id);
    }

    public List<CarDto> getCars(){
        return carMapper.mapToCarDtoList(carRepository.findAll());
    }
}
