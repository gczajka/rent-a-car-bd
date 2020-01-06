package com.backend.rentacarbd.service;

import com.backend.rentacarbd.domain.Car;
import com.backend.rentacarbd.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public Car saveCar(final Car car){
        return carRepository.save(car);
    }

    public Car modifyCar(final Car car){
        return carRepository.save(car);
    }

    public void deleteCar(Long id){
        carRepository.deleteById(id);
    }

    public List<Car> getCars(){
        return carRepository.findAll();
    }
}
