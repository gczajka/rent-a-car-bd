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
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(unique = true)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String email;

    @NotNull
    private Integer phoneNumber;

    @NotNull
    private String password;

    public User(String name, String surname, String email, Integer phoneNumber, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    @OneToMany(targetEntity = Rental.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Rental> rentals = new ArrayList<>();
}
