package com.backend.rentacarbd.repository;

import com.backend.rentacarbd.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long id);

    @Override
    User save(User user);

    @Override
    void deleteById(Long id);

    @Override
    void delete(User user);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    @Override
    long count();
}
