package com.backend.rentacarbd.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long duration;
    private Long cost;
    private String carModel;
    private String userSurname;
}
