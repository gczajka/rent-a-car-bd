package com.backend.rentacarbd.controller;

import com.backend.rentacarbd.controller.exceptions.CarNotFoundException;
import com.backend.rentacarbd.controller.exceptions.RentalNotFoundException;
import com.backend.rentacarbd.controller.exceptions.UserNotFoundException;
import com.backend.rentacarbd.domain.Rental;
import com.backend.rentacarbd.domain.RentalDto;
import com.backend.rentacarbd.domain.UserDto;
import com.backend.rentacarbd.mapper.RentalMapper;
import com.backend.rentacarbd.mapper.UserMapper;
import com.backend.rentacarbd.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/v1/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;
    @Autowired
    private RentalMapper rentalMapper;

    @PostMapping("/{userId}&{carId}")
    public void createRental(@PathVariable Long userId, @PathVariable Long carId) throws UserNotFoundException, CarNotFoundException {
        rentalService.createRental(userId, carId);
    }
    @PutMapping("/{rentalId}")
    public void modifyRental(@PathVariable Long rentalId) throws RentalNotFoundException {
        rentalService.lengthenRental(rentalId);
    }

    @DeleteMapping
    public void endRental(@RequestParam Long rentalId) {
        rentalService.endRental(rentalId);
    }

    @GetMapping
    public List<RentalDto> getRentals() {
        return rentalMapper.mapToRentalDtoList(rentalService.getRentals());
    }

    @GetMapping("/byUserId/{userId}")
    public List<RentalDto> getRentalsByUserId(@PathVariable Long userId) {
        return rentalMapper.mapToRentalDtoList(rentalService.getRentalsByUserId(userId));
    }

    @GetMapping("/{id}")
    public RentalDto getRentalById(@PathVariable Long id) throws RentalNotFoundException {
        return rentalMapper.mapToRentalDto(rentalService.getRentalById(id));
    }
}
