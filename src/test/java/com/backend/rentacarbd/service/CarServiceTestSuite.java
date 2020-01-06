package com.backend.rentacarbd.service;

import com.backend.rentacarbd.domain.Car;
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
        Car car = new Car("brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = null;

        try {
            //When
            savedCar = carService.saveCar(car);

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
        Car car = new Car("brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = carService.saveCar(car);

        try {
            //When
            List<Car> listOfCars = carService.getCars();
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
        Car car = new Car("brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = carService.saveCar(car);

        //When
        carService.deleteCar(savedCar.getId());
        int size = carService.getCars().size();

        //Then
        Assert.assertEquals(0, size);
    }

    @Test
    public void testModifyCar() {
        // Given
        Car car = new Car("brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car savedCar = carService.saveCar(car);
        long id = savedCar.getId();
        savedCar.setBrand("modified");
        Car modifiedCar = null;

        try {
            //When
            modifiedCar = carService.modifyCar(savedCar);

            //Then
            Assert.assertEquals("modified", modifiedCar.getBrand());
            Assert.assertEquals(id, (long)modifiedCar.getId());
        } finally {
            //CleanUp
            carService.deleteCar(modifiedCar.getId());
        }
    }
}
