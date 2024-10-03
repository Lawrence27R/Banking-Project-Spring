package com.techlabs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlabs.REPO.ClientRepository;
import com.techlabs.REPO.RoleRepo;
import com.techlabs.REPO.UserRepo;
import com.techlabs.dto.LoginDto;
import com.techlabs.dto.RegisterDto;
import com.techlabs.entity.Client;
import com.techlabs.entity.ClientStatus;
import com.techlabs.entity.Role;
import com.techlabs.entity.User;
import com.techlabs.exceptions.UserApiException;
import com.techlabs.security.JwtTokenProvider;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;
    
    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    public User register(RegisterDto registerDto) {
        if (userRepo.existsByUsername(registerDto.getUsername()) || userRepo.existsByEmail(registerDto.getEmail())) {
            throw new UserApiException(HttpStatus.BAD_REQUEST, "User with this username or email already exists");
        }

        // Create User
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        // Set default role to ROLE_CLIENT
        Role clientRole = roleRepo.findByRoleName("ROLE_CLIENT").orElseThrow(() -> 
            new RuntimeException("Default role ROLE_CLIENT not found"));
        user.setRole(clientRole);

        // Save User
        user = userRepo.save(user);

        // Create and save associated Client
        Client client = new Client();
        client.setRegistrationNumber(registerDto.getRegistrationNumber()); // Add this field to RegisterDto
        client.setFirstname(""); // Set default values or allow these to be set during registration
        client.setLastname("");
        client.setKycStatus(ClientStatus.PENDING);
        client.setUser(user); // Set the back reference

        clientRepo.save(client); // Save the Client

        return user; // Return the saved User
    }

    @Override
    public String login(LoginDto loginDto) {
        User user = userRepo.findByUsernameOrEmail(loginDto.getUsername(), loginDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.generateToken(authentication);
    }
}

