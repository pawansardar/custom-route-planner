package com.pawan.customrouteplanner.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawan.customrouteplanner.dto.request.LoginRequest;
import com.pawan.customrouteplanner.entity.User;
import com.pawan.customrouteplanner.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authManager;
	
	public AuthController(UserRepository userRepo, PasswordEncoder passwordEncoder, AuthenticationManager authManager) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.authManager = authManager;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		if (userRepo.findByUsername(user.getUsername()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if (user.getRoles() == null || user.getRoles().isBlank()) {
			user.setRoles("ROLE_USER");
		}
		userRepo.save(user);
		return ResponseEntity.ok("User registered successfully");
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest request) {
		try {
			Authentication auth = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
					);
			SecurityContextHolder.getContext().setAuthentication(auth);
			return ResponseEntity.ok("Login Successful");
		}
		catch (AuthenticationException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}
}
