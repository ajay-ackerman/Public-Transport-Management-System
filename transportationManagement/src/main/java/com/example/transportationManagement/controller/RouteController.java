package com.example.transportationManagement.controller;

import com.example.transportationManagement.dto.RouteResponseDto;
import com.example.transportationManagement.dto.RouteStopResponseDto;
import com.example.transportationManagement.entity.Route;
import com.example.transportationManagement.entity.RouteStop;
import com.example.transportationManagement.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PreAuthorize("hasAuthority('ROUTE_CREATE')")
    @PostMapping
    public ResponseEntity<RouteResponseDto> createOrUpdateRoute(@RequestBody Route route) {
        return ResponseEntity.ok(routeService.saveOrUpdateRoute(route));
    }

    @PreAuthorize("hasAuthority('ROUTE_VIEW')")
    @GetMapping("/{id}")
    public ResponseEntity<RouteResponseDto> getRouteById(@PathVariable Long id) {
        return ResponseEntity.ok(routeService.getRouteById(id));
    }

    @PreAuthorize("hasAuthority('ROUTE_VIEW')")
    @GetMapping
    public ResponseEntity<List<RouteResponseDto>> getAllRoutes() {
        return ResponseEntity.ok(routeService.getAllRoutes());
    }

    @PreAuthorize("hasAuthority('ROUTE_VIEW')")
    @GetMapping("/mode/{mode}")
    public ResponseEntity<List<RouteResponseDto>> getRoutesByMode(@PathVariable String mode) {
        return ResponseEntity.ok(routeService.getRoutesByTransportMode(mode));
    }

    @PreAuthorize("hasAuthority('ROUTE_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ROUTE_UPDATE')")
    @PostMapping("/{id}/stops")
    public ResponseEntity<RouteStopResponseDto> addStopToRoute(
            @PathVariable Long id,
            @RequestBody RouteStopResponseDto stop) {
        return ResponseEntity.ok(routeService.addStopToRoute(id, stop));
    }
}
