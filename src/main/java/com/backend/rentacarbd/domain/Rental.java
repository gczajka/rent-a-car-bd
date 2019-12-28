package com.backend.rentacarbd.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Data
@NoArgsConstructor
@Entity
@Table(name = "RENTALS")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(unique = true)
    private Long id;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private long duration;

    @NotNull
    private Double cost;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "car_id")
    private Car car;


    public Rental(User user, Car car) {
        this.startDate = LocalDate.now();
        this.endDate = startDate.plusDays(5);
        this.user = user;
        this.car = car;
        this.duration = DAYS.between(startDate, endDate);
        this.cost = duration * car.getCostPerDay();
    }


}
