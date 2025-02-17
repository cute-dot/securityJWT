package com.example.securityJWT.auth.repositories;

import com.example.securityJWT.auth.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByUserName(String username);
}
