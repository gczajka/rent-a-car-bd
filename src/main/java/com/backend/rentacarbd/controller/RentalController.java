package com.backend.rentacarbd.controller;

import com.backend.rentacarbd.controller.exceptions.CarNotFoundException;
import com.backend.rentacarbd.controller.exceptions.RentalNotFoundException;
import com.backend.rentacarbd.controller.exceptions.UserNotFoundException;
import com.backend.rentacarbd.domain.RentalDto;
import com.backend.rentacarbd.domain.RentalVesselDto;
import com.backend.rentacarbd.facade.RequestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/v1/rentals")
public class RentalController {
    @Autowired
    private RequestFacade requestFacade;

    @PostMapping
    public void createRental(@RequestBody RentalVesselDto vessel) throws UserNotFoundException, CarNotFoundException {
        requestFacade.createRental(vessel.getUserId(), vessel.getCarId());
    }

    @PutMapping
    public void modifyRental(@RequestBody Long rentalId) throws RentalNotFoundException {
        requestFacade.lengthenRental(rentalId);
    }

    @DeleteMapping("/{id}")
    public void endRental(@PathVariable Long id) {
        requestFacade.endRental(id);
    }

    @GetMapping
    public List<RentalDto> getRentals() {
        return requestFacade.getRentals();
    }

    @GetMapping("/byUserId/{userId}")
    public List<RentalDto> getRentalsByUserId(@PathVariable Long userId) {
        return requestFacade.getRentalsByUserId(userId);
    }
}
