package com.example.transportationManagement.controller;

import com.example.transportationManagement.entity.Route;
import com.example.transportationManagement.entity.RouteStop;
import com.example.transportationManagement.entity.Schedule;
import com.example.transportationManagement.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;


    @PostMapping
    public ResponseEntity<Route> createOrUpdateRoute(@RequestBody Route route) {
        return ResponseEntity.ok(routeService.saveOrUpdateRoute(route));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.ok("Route deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        return ResponseEntity.ok(routeService.getAllRoutes());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Route>> getActiveRoutes() {
        return ResponseEntity.ok(routeService.getActiveRoutes());
    }

    @GetMapping("/mode/{mode}")
    public ResponseEntity<List<Route>> getRoutesByTransportMode(@PathVariable String mode) {
        return ResponseEntity.ok(routeService.getRoutesByTransportMode(mode));
    }

    @PostMapping("/{routeId}/stops")
    public ResponseEntity<RouteStop> addStop(@PathVariable Long routeId, @RequestBody RouteStop stop) {
        return ResponseEntity.ok(routeService.addStopToRoute(routeId, stop));
    }

    @PostMapping("/{routeId}/schedules")
    public ResponseEntity<Schedule> addSchedule(@PathVariable Long routeId, @RequestBody Schedule schedule) {
        return ResponseEntity.ok(routeService.addScheduleToRoute(routeId, schedule));
    }
}
