package com.backend.rentacarbd.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private String colour;
    private String engineType;
    private Integer engineCapacity;
    private Integer productionYear;
    private Double costPerDay;

    public CarDto(String brand, String model, String colour, String engineType, Integer engineCapacity, Integer productionYear, Double costPerDay) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.engineType = engineType;
        this.engineCapacity = engineCapacity;
        this.productionYear = productionYear;
        this.costPerDay = costPerDay;
    }
}
