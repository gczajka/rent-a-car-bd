package com.backend.rentacarbd.mapper;

import com.backend.rentacarbd.domain.Car;
import com.backend.rentacarbd.domain.CarDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CarMapperTestSuite {
    @Autowired
    private CarMapper carMapper;

    @Test
    public void testMapToCar() {
        // Given
        CarDto carDto = new CarDto(1L,"brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);

        // When
        Car car = carMapper.mapToCar(carDto);

        // Then
        Assert.assertEquals(1L, (long)car.getId());
        Assert.assertEquals("brand1", car.getBrand());
        Assert.assertEquals("model1", car.getModel());
        Assert.assertEquals("colour1", car.getColour());
        Assert.assertEquals("engineType1", car.getEngineType());
        Assert.assertEquals(1000, (long)car.getEngineCapacity());
        Assert.assertEquals(2000, (long)car.getProductionYear());
        Assert.assertEquals(5, (long)car.getCostPerDay());
        Assert.assertTrue(car.isAvailable());
    }

    @Test
    public void testMapToCarDto() {
        // Given
        Car car = new Car("brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        car.setId(1L);

        // When
        CarDto carDto = carMapper.mapToCarDto(car);

        // Then
        Assert.assertEquals(1L, (long)carDto.getId());
        Assert.assertEquals("brand1", carDto.getBrand());
        Assert.assertEquals("model1", carDto.getModel());
        Assert.assertEquals("colour1", carDto.getColour());
        Assert.assertEquals("engineType1", carDto.getEngineType());
        Assert.assertEquals(1000, (long)carDto.getEngineCapacity());
        Assert.assertEquals(2000, (long)carDto.getProductionYear());
        Assert.assertEquals(5, (long)carDto.getCostPerDay());
        Assert.assertTrue(carDto.isAvailable());
    }

    @Test
    public void testMapToCarDtoList() {
        // Given
        List<Car> carList = new ArrayList<>();
        Car car1 = new Car("brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car car2 = new Car("brand2", "model2","colour2", "engineType2", 1001, 2001, 6, true);
        car1.setId(1L);
        car2.setId(2L);
        carList.add(car1);
        carList.add(car2);

        // When
        List<CarDto> carDtoList = carMapper.mapToCarDtoList(carList);

        // Then
        Assert.assertEquals(2, carDtoList.size());
        Assert.assertEquals("brand1", carDtoList.get(0).getBrand());
        Assert.assertEquals(2L, (long)carDtoList.get(1).getId());
    }
}
