package com.backend.rentacarbd.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private long duration;
    private Double cost;
    private String carModel;
    private String userSurname;
}
