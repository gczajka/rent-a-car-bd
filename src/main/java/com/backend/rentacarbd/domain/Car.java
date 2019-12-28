package com.backend.rentacarbd.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CARS")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(unique = true)
    private Long id;

    @NotNull
    private String brand;

    @NotNull
    private String model;

    @NotNull
    private String colour;

    @NotNull
    private String engineType;

    @NotNull
    private Integer engineCapacity;

    @NotNull
    private Integer productionYear;

    public Car(String brand, String model, String colour, String engineType, Integer engineCapacity, Integer productionYear) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.engineType = engineType;
        this.engineCapacity = engineCapacity;
        this.productionYear = productionYear;
    }
}
