package com.example.securityJWT.Auth.Services;

import com.example.securityJWT.Auth.Entities.Role;
import com.example.securityJWT.Auth.Repositories.RoleRepository;
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
