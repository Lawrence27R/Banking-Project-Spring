package com.techlabs.REPO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlabs.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email); // New method
    boolean existsByUsername(String username);
    boolean existsByEmail(String email); // New method
}
