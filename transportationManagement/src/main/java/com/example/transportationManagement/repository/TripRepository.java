package com.example.transportationManagement.repository;

import com.example.transportationManagement.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip,Long> {
    List<Trip> findByVehicleId(Long vehicleId);
    List<Trip> findByRouteId(Long routeId);
    List<Trip> findByDriverId(Long driverId);
}
