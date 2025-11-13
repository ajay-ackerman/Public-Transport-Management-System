package com.example.transportationManagement.controller;

import com.example.transportationManagement.dto.RouteResponseDto;
import com.example.transportationManagement.dto.RouteStopResponseDto;
import com.example.transportationManagement.entity.Route;
import com.example.transportationManagement.entity.RouteStop;
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
    public ResponseEntity<RouteResponseDto> createOrUpdateRoute(@RequestBody Route route) {
        return ResponseEntity.ok(routeService.saveOrUpdateRoute(route));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteResponseDto> getRouteById(@PathVariable Long id) {
        return ResponseEntity.ok(routeService.getRouteById(id));
    }

    @GetMapping
    public ResponseEntity<List<RouteResponseDto>> getAllRoutes() {
        return ResponseEntity.ok(routeService.getAllRoutes());
    }

    @GetMapping("/mode/{mode}")
    public ResponseEntity<List<RouteResponseDto>> getRoutesByMode(@PathVariable String mode) {
        return ResponseEntity.ok(routeService.getRoutesByTransportMode(mode));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/stops")
    public ResponseEntity<RouteStopResponseDto> addStopToRoute(@PathVariable Long id,@RequestBody RouteStopResponseDto stop){

        return ResponseEntity.ok(routeService.addStopToRoute(id, stop)) ;
    }
}
