package com.pawan.customrouteplanner.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.pawan.customrouteplanner.dto.request.LocationRequest;
import com.pawan.customrouteplanner.entity.Location;
import com.pawan.customrouteplanner.repository.LocationRepository;

@Service
public class LocationService {
	
	private LocationRepository locationRepo;
	
	public LocationService(LocationRepository locationRepo) {
		this.locationRepo = locationRepo;
	}
	
	@PreAuthorize("hasRole('ADMIN')")   // Alternative: @Secured("ROLE_ADMIN")
	public ResponseEntity<String> addLocation(LocationRequest request) throws Exception {
		try {
			String locationName = request.getName().toLowerCase();
			if (locationRepo.existsByName(locationName)) {
				throw new IllegalArgumentException("Location already exists");
			}
			Location location = new Location(locationName);
			locationRepo.save(location);
			return new ResponseEntity<>("Location saved successfully", HttpStatus.CREATED);
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}
