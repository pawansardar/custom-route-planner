package com.pawan.customrouteplanner.dto.request;

import jakarta.validation.constraints.NotBlank;

public class LocationRequest {
	@NotBlank
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
