package com.backend.rentacarbd.mapper;

import com.backend.rentacarbd.domain.Car;
import com.backend.rentacarbd.domain.Rental;
import com.backend.rentacarbd.domain.RentalDto;
import com.backend.rentacarbd.domain.User;
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
public class RentalMapperTestSuite {
    @Autowired
    private RentalMapper rentalMapper;

    @Test
    public void testMapToRentalDtoList() {
        // Given
        List<Rental> rentalList = new ArrayList<>();
        User user1 = new User("name1", "surname1","email1", "phoneNumber1", "password1");
        User user2 = new User("name2", "surname2","email2", "phoneNumber2", "password2");
        Car car1 = new Car("brand1", "model1","colour1", "engineType1", 1000, 2000, 5, true);
        Car car2 = new Car("brand2", "model2","colour2", "engineType2", 1001, 2001, 6, true);
        Rental rental1 = new Rental(user1, car1);
        Rental rental2 = new Rental(user2, car2);
        rental1.setId(1L);
        rental2.setId(2L);
        rentalList.add(rental1);
        rentalList.add(rental2);

        // When
        List<RentalDto> rentalDtoList = rentalMapper.mapToRentalDtoList(rentalList);

        // Then
        Assert.assertEquals(2, rentalDtoList.size());
        Assert.assertEquals("model1", rentalDtoList.get(0).getCarModel());
        Assert.assertEquals(2L, (long)rentalDtoList.get(1).getId());
    }
}
