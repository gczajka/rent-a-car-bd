package com.backend.rentacarbd.repository;

import com.backend.rentacarbd.domain.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface LoginRepository extends CrudRepository<Login, Long> {
    @Override
    Login save(Login login);

    boolean existsByEmailAndPassword(String email, String password);
}
