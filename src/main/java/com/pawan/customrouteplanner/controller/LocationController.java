package com.pawan.customrouteplanner.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pawan.customrouteplanner.dto.request.LocationRequest;
import com.pawan.customrouteplanner.service.LocationService;

import jakarta.validation.Valid;

@RestController
public class LocationController {
	
	private LocationService locationService;
	
	public LocationController(LocationService locationService) {
		this.locationService = locationService;
	}
	
	@PostMapping("location")
	public ResponseEntity<String> addLocation(@RequestBody @Valid LocationRequest request) throws Exception {
		String result = locationService.addLocation(request);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
}
