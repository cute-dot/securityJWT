package com.example.securityJWT.Auth.Repositories;

import com.example.securityJWT.Auth.Entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByRoleName(String RoleName);
}
