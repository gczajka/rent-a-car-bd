package com.backend.rentacarbd.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

    @NotNull
    private Double costPerDay;

    public Car(String brand, String model, String colour, String engineType, Integer engineCapacity, Integer productionYear, Double costPerDay) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.engineType = engineType;
        this.engineCapacity = engineCapacity;
        this.productionYear = productionYear;
        this.costPerDay = costPerDay;
    }

    @OneToMany(targetEntity = Rental.class,
            mappedBy = "car",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY)
    private List<Rental> rentals = new ArrayList<>();

}
