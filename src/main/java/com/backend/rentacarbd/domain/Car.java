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

    @NotNull
    private boolean available;

    public Car(String brand, String model, String colour, String engineType, Integer engineCapacity, Integer productionYear, Double costPerDay, boolean available) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.engineType = engineType;
        this.engineCapacity = engineCapacity;
        this.productionYear = productionYear;
        this.costPerDay = costPerDay;
        this.available = available;
    }

    @OneToMany(targetEntity = Rental.class,
            mappedBy = "car",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Rental> rentals = new ArrayList<>();

}
