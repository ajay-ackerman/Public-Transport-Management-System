package com.example.transportationManagement.repository;

import com.example.transportationManagement.entity.Route;
import com.example.transportationManagement.entity.RouteStop;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    List<Route> findByTransportMode(String transportMode);
    List<Route> findByActiveTrue();
}
