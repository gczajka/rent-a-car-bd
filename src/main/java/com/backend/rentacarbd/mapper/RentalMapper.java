package com.backend.rentacarbd.mapper;

import com.backend.rentacarbd.domain.Rental;
import com.backend.rentacarbd.domain.RentalDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentalMapper {
    public List<RentalDto> mapToRentalDtoList(final List<Rental> rentalList){
        return rentalList.stream()
                .map(rental -> new RentalDto(
                        rental.getId(),
                        rental.getStartDate(),
                        rental.getEndDate(),
                        rental.getDuration(),
                        rental.getCost(),
                        rental.getCar().getModel(),
                        rental.getUser().getId()))
                .collect(Collectors.toList());
    }
}
