package com.backend.rentacarbd.service;

import com.backend.rentacarbd.controller.exceptions.CarNotFoundException;
import com.backend.rentacarbd.controller.exceptions.RentalNotFoundException;
import com.backend.rentacarbd.controller.exceptions.UserNotFoundException;
import com.backend.rentacarbd.domain.Car;
import com.backend.rentacarbd.domain.Rental;
import com.backend.rentacarbd.domain.User;
import com.backend.rentacarbd.repository.CarRepository;
import com.backend.rentacarbd.repository.RentalRepository;
import com.backend.rentacarbd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;

    public Rental createRental(final Long userId, final Long carId) throws UserNotFoundException, CarNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        Car car = carRepository.findById(carId).orElseThrow(CarNotFoundException::new);
        car.setAvailable(false);
        carRepository.save(car);

        Rental rental = new Rental(user, car);
        return  rentalRepository.save(rental);
    }

    public void endRental(final Long id){
        Rental rental = rentalRepository.findById(id).get();
        rental.getUser().getRentals().remove(rental);

        Car car = rental.getCar();
        car.getRentals().remove(rental);
        car.setAvailable(true);
//        carRepository.save(car);
        rentalRepository.deleteById(id);
    }

    public List<Rental> getRentals(){
        return rentalRepository.findAll();
    }

    public Rental getRentalById(Long id) throws RentalNotFoundException { return rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new);
    }
}