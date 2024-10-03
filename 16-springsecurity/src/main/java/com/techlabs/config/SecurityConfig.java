package com.techlabs.config;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.techlabs.security.JwtAuthenticationEntryPoint;
import com.techlabs.security.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final UserDetailsService userDetailsService;
	private final JwtAuthenticationEntryPoint authenticationEntryPoint;
	private final JwtAuthenticationFilter authenticationFilter;

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())
	        .cors(withDefaults())
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(authorizeRequests -> authorizeRequests
	            .requestMatchers("/api/register").permitAll()
	            .requestMatchers("/api/login").permitAll()
	            .requestMatchers(HttpMethod.GET, "/app/**").authenticated()
	            .requestMatchers(HttpMethod.POST, "/app/**").authenticated()
	            .requestMatchers(HttpMethod.PUT, "/app/**").authenticated()
	            .requestMatchers(HttpMethod.DELETE, "/app/**").authenticated()
	            
	            .requestMatchers(HttpMethod.POST, "/app/employees/add").permitAll() 
                .requestMatchers(HttpMethod.GET, "/app/employees/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/app/employees/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/app/employees/**").authenticated()
	            
	            .anyRequest().authenticated())
	        .exceptionHandling(exceptionHandling -> exceptionHandling
	            .authenticationEntryPoint(authenticationEntryPoint))
	        .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}

}