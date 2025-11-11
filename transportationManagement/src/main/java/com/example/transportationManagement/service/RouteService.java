package com.example.transportationManagement.service;
import com.example.transportationManagement.entity.Route;
import com.example.transportationManagement.entity.RouteStop;
import com.example.transportationManagement.entity.Schedule;
import com.example.transportationManagement.repository.RouteRepository;
import com.example.transportationManagement.repository.RouteStopRepository;
import com.example.transportationManagement.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RouteService {

    private final RouteRepository routeRepository;
    private final RouteStopRepository routeStopRepository;
    private final ScheduleRepository scheduleRepository;

    // Create / Update Route
    public Route saveOrUpdateRoute(Route route) {
        return routeRepository.save(route);
    }

    // Delete Route
    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }

    // Get all Routes
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    // Get all Active Routes
    public List<Route> getActiveRoutes() {
        return routeRepository.findByActiveTrue();
    }

    // Get Routes by Transport Type
    public List<Route> getRoutesByTransportMode(String mode) {
        return routeRepository.findByTransportMode(mode.toUpperCase());
    }

    // Add Stop to Route
    public RouteStop addStopToRoute(Long routeId, RouteStop stop) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new IllegalArgumentException("Route not found"));
        stop.setRoute(route);
        return routeStopRepository.save(stop);
    }

    // Add Schedule to Route
    public Schedule addScheduleToRoute(Long routeId, Schedule schedule) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new IllegalArgumentException("Route not found"));
        schedule.setRoute(route);
        return scheduleRepository.save(schedule);
    }
}
