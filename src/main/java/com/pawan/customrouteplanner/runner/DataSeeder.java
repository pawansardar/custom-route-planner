package com.pawan.customrouteplanner.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.pawan.customrouteplanner.entity.User;
import com.pawan.customrouteplanner.repository.UserRepository;

@Component
public class DataSeeder implements CommandLineRunner {
	
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	
	public DataSeeder(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		if (userRepo.findByUsername("admin").isPresent()) {
			return;
		}
		User adminUser = new User();
		adminUser.setUsername("admin");
		adminUser.setPassword(passwordEncoder.encode("admin123"));
		adminUser.setRoles("ROLE_USER, ROLE_ADMIN");
		userRepo.save(adminUser);
		
		if (userRepo.findByUsername("user").isPresent()) {
			return;
		}
		User user = new User();
		user.setUsername("user");
		user.setPassword(passwordEncoder.encode("user123"));
		user.setRoles("ROLE_USER");
		userRepo.save(user);
	}
	
}
