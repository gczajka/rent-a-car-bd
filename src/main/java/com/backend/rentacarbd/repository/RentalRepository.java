package com.backend.rentacarbd.repository;

import com.backend.rentacarbd.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {
    @Override
    List<Rental> findAll();

    @Override
    Optional<Rental> findById(Long id);

    @Override
    Rental save(Rental rental);

    @Override
    void delete(Rental rental);

    @Override
    void deleteById(Long id);
}
