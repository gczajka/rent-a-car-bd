package com.backend.rentacarbd.service;

import com.backend.rentacarbd.domain.Car;
import com.backend.rentacarbd.domain.CarDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CarServiceTestSuite {
    @Autowired
    private CarService carService;

    @Test
    public void testSaveCar() {
        // Given
        CarDto carDto = new CarDto(null, "brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = null;

        try {
            //When
            savedCar = carService.saveCar(carDto);

            //Then
            Assert.assertEquals("brand1", savedCar.getBrand());
        } finally {
            //CleanUp
            carService.deleteCar(savedCar.getId());
        }
    }

    @Test
    public void getCars() {
        //Given
        CarDto carDto = new CarDto(null, "brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = carService.saveCar(carDto);

        try {
            //When
            List<CarDto> listOfCars = carService.getCars();
            //Then
            Assert.assertEquals(1, listOfCars.size());
        } finally {
            //CleanUp
            carService.deleteCar(savedCar.getId());
        }
    }

    @Test
    public void testDeleteCar() {
        // Given
        CarDto carDto = new CarDto(null, "brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = carService.saveCar(carDto);

        //When
        carService.deleteCar(savedCar.getId());
        int size = carService.getCars().size();

        //Then
        Assert.assertEquals(0, size);
    }

    @Test
    public void testModifyCar() {
        // Given
        CarDto carDto = new CarDto(null, "brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = carService.saveCar(carDto);
        long id = savedCar.getId();
        carDto.setBrand("modified");
        carDto.setId(id);
        Car modifiedCar = null;

        try {
            //When
            modifiedCar = carService.modifyCar(carDto);

            //Then
            Assert.assertEquals("modified", modifiedCar.getBrand());
            Assert.assertEquals(id, (long)modifiedCar.getId());
        } finally {
            //CleanUp
            carService.deleteCar(modifiedCar.getId());
        }
    }
}
