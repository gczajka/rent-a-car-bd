package com.backend.rentacarbd.repository;

import com.backend.rentacarbd.domain.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    @Override
    List<Car> findAll();

    @Override
    Optional<Car> findById(Long id);

    @Override
    Car save(Car car);

    @Override
    void delete(Car car);
}
