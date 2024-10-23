package com.example.weatherapi.repository;

import com.example.weatherapi.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByPincode(String pincode);
}
