package com.example.securityJWT.auth.services;

import com.example.securityJWT.auth.entities.Role;
import com.example.securityJWT.auth.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<Role> findByRoleName(String roleName){
        return roleRepository.findByRoleName(roleName);
    }



}
