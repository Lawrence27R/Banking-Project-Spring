package com.techlabs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.techlabs.REPO.RoleRepo;
import com.techlabs.REPO.UserRepo;
import com.techlabs.entity.Role;
import com.techlabs.entity.User;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create default roles if not exist
        if (!roleRepo.existsByRoleName("ROLE_SUPER_ADMIN")) {
            Role superAdminRole = new Role();
            superAdminRole.setRoleName("ROLE_SUPER_ADMIN");
            roleRepo.save(superAdminRole);
        }

        if (!roleRepo.existsByRoleName("ROLE_CLIENT")) {
            Role clientRole = new Role();
            clientRole.setRoleName("ROLE_CLIENT");
            roleRepo.save(clientRole);
        }

        // Ensure roles are correctly saved before checking for admin
        Role adminRole = roleRepo.findByRoleName("ROLE_SUPER_ADMIN")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Create default admin user if not exist
        if (!userRepo.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("Root#123"));
            admin.setRole(adminRole);
            userRepo.save(admin);
        }
    }
}
