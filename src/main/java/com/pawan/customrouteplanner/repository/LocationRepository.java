package com.pawan.customrouteplanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pawan.customrouteplanner.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	boolean existsByName(String name);
}
