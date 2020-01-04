package com.backend.rentacarbd.controller;

import com.backend.rentacarbd.controller.exceptions.CarNotFoundException;
import com.backend.rentacarbd.controller.exceptions.RentalNotFoundException;
import com.backend.rentacarbd.controller.exceptions.UserNotFoundException;
import com.backend.rentacarbd.domain.RentalDto;
import com.backend.rentacarbd.domain.RentalVesselDto;
import com.backend.rentacarbd.mapper.RentalMapper;
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

    @PostMapping
    public void createRental(@RequestBody RentalVesselDto vessel) throws UserNotFoundException, CarNotFoundException {
        rentalService.createRental(vessel.getUserId(), vessel.getCarId());
    }
    @PutMapping
    public void modifyRental(@RequestBody Long rentalId) throws RentalNotFoundException {
        rentalService.lengthenRental(rentalId);
    }

    @DeleteMapping("/{id}")
    public void endRental(@PathVariable Long id) {
        rentalService.endRental(id);
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
