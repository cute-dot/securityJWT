package com.example.securityJWT.auth.repositories;

import com.example.securityJWT.auth.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByRoleName(String RoleName);
}
