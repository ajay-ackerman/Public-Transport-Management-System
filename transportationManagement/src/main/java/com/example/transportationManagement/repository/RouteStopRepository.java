package com.example.transportationManagement.repository;

import com.example.transportationManagement.entity.RouteStop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteStopRepository extends JpaRepository<RouteStop, Long> {
}