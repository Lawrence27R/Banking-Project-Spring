package com.techlabs.REPO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(String roleName); // Finds a role by its name
    boolean existsByRoleName(String roleName);      // Checks if a role with the given name exists
}

