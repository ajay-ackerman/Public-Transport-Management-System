package com.example.transportationManagement.repository;

import com.example.transportationManagement.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface VehicleRespository extends JpaRepository<Vehicle,Long> {
    Optional<Vehicle> findByVehicleNo(String vehicleNo);
}
