package com.backend.rentacarbd.mapper;

import com.backend.rentacarbd.domain.Car;
import com.backend.rentacarbd.domain.CarDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {
    public Car mapToCar(final CarDto carDto) {
        return new Car(
                carDto.getBrand(),
                carDto.getModel(),
                carDto.getColour(),
                carDto.getEngineType(),
                carDto.getEngineCapacity(),
                carDto.getProductionYear(),
                carDto.getCostPerDay(),
                carDto.isAvailable());
    }

    public CarDto mapToCarDto(final Car car) {
        return new CarDto(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getColour(),
                car.getEngineType(),
                car.getEngineCapacity(),
                car.getProductionYear(),
                car.getCostPerDay(),
                car.isAvailable());
    }

    public List<CarDto> mapToCarDtoList(final List<Car> carList) {
        return carList.stream()
                .map(car -> new CarDto(
                        car.getId(),
                        car.getBrand(),
                        car.getModel(),
                        car.getColour(),
                        car.getEngineType(),
                        car.getEngineCapacity(),
                        car.getProductionYear(),
                        car.getCostPerDay(),
                        car.isAvailable()))
                .collect(Collectors.toList());
    }
}
